package backend.IR_ASM;

import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVinstruction.*;
import RISCV.RISCVinstruction.Binary.ImmediateBinary;
import RISCV.RISCVinstruction.Binary.RegisterBinary;
import RISCV.RISCVinstruction.Branch.UnaryBranch;
import RISCV.RISCVoperand.RISCVregister.RISCVPhysicalRegister;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.ArrayList;
import java.util.HashMap;

public class Peephole {
    static RISCVModule root;
    static void removeIdMove(RISCVFunction function) {
        for (RISCVBasicBlock block : function.getBlockContain()) {
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                if (inst instanceof RISCVMove && ((RISCVMove) inst).rs.getColor().equals(((RISCVMove) inst).rd.getColor())) {
                    inst.remove();
                } else if (inst instanceof ImmediateBinary
                        && (((ImmediateBinary) inst).op == RISCVInstruction.SCategory.add
                        || ((ImmediateBinary) inst).op == RISCVInstruction.SCategory.or
                        || ((ImmediateBinary) inst).op == RISCVInstruction.SCategory.xor
                        || ((ImmediateBinary) inst).op == RISCVInstruction.SCategory.sll
                        || ((ImmediateBinary) inst).op == RISCVInstruction.SCategory.sra
                )
                        && ((ImmediateBinary) inst).rd.getColor().equals(((ImmediateBinary) inst).rs.getColor()) && ((ImmediateBinary) inst).imm.getValue() == 0) {
                    inst.remove();
                } else if (inst instanceof RegisterBinary
                        && (((RegisterBinary) inst).op == RISCVInstruction.SCategory.add
                        || ((RegisterBinary) inst).op == RISCVInstruction.SCategory.or
                        || ((RegisterBinary) inst).op == RISCVInstruction.SCategory.xor
                        || ((RegisterBinary) inst).op == RISCVInstruction.SCategory.sll
                        || ((RegisterBinary) inst).op == RISCVInstruction.SCategory.sra
                )
                        && (
                        ((RegisterBinary) inst).rd.getColor().equals(((RegisterBinary) inst).rs1.getColor()) && ((RegisterBinary) inst).rs2.getColor() == root.getPhysicalRegister(0)
                )) {
                    inst.remove();
                }
            }
        }
    }
    static void redundantElimination(RISCVFunction function) {
        RISCVPhysicalRegister zero = root.getPhysicalRegister(0);
        for (RISCVBasicBlock block : function.getBlockContain()) {
            HashMap<RISCVPhysicalRegister, RISCVInstruction> content = new HashMap<>();
            for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                boolean changed = false;
                if (inst instanceof RISCVLa) {
                    if (content.containsKey(((RISCVLa) inst).rd.getColor())) {
                        RISCVInstruction last = content.get(((RISCVLa) inst).rd.getColor());
                        if (last instanceof RISCVLa && ((RISCVLa) last).src.equals(((RISCVLa) inst).src)) {
                            inst.remove();
                            changed = true;
                        }
                    }
                } else if (inst instanceof RISCVLui) {
                    if (content.containsKey(((RISCVLui) inst).rd.getColor())) {
                        RISCVInstruction last = content.get(((RISCVLui) inst).rd.getColor());
                        if (last instanceof RISCVLui && ((RISCVLui) last).value.equals(((RISCVLui) inst).value)) {
                            inst.remove();
                            changed = true;
                        }
                    }
                } else if (inst instanceof RISCVLi) {
                    if (content.containsKey(((RISCVLi) inst).rd.getColor())) {
                        RISCVInstruction last = content.get(((RISCVLi) inst).rd.getColor());
                        if (last instanceof RISCVLi && ((RISCVLi) last).value == ((RISCVLi) inst).value) {
                            inst.remove();
                            changed = true;
                        }
                    }
                } else if (inst instanceof RISCVMove && ((RISCVMove) inst).rs.getColor().equals(zero)) {
                    if (content.containsKey(((RISCVMove) inst).rd.getColor())) {
                        RISCVInstruction last = content.get(((RISCVMove) inst).rd.getColor());
                        if (last instanceof RISCVMove && ((RISCVMove) last).rs.getColor().equals(zero)) {
                            inst.remove();
                            changed = true;
                        }
                    }
                }
                while (!changed
                        && inst.prev != null
                        && inst.prev.Defs().size() == 1
                        && inst.Defs().size() == 1
                        && inst.prev.Defs().iterator().next().getColor() == inst.Defs().iterator().next().getColor()) {
                    changed = true;
                    for (RISCVRegister i : inst.Uses()) {
                        if (i.getColor() == inst.Defs().iterator().next().getColor()) {
                            changed = false;
                            break;
                        }
                    }
                    if (changed) {
                        inst.prev.remove();
                        changed = false;
                    } else {
                        break;
                    }
                }
                if (!changed) {
                    for (RISCVRegister def : inst.Defs()) {
                        content.put(def.getColor(), inst);
                    }
                }
            }
        }
    }

    static void combineBlocks(RISCVFunction function) {
        // for redundant eliminator
        boolean updated;
        do {
            updated = false;
            for (RISCVBasicBlock block : function.getBlockContain()) {
                if (block.getTail() instanceof UnaryBranch
                        && ((UnaryBranch) block.getTail()).offset.getPrev().size() == 1
                        && !((UnaryBranch) block.getTail()).offset.equals(block)) {
                    RISCVBasicBlock next = ((UnaryBranch) block.getTail()).offset;
                    block.clearNext();
                    next.clearPrev();
                    block.getTail().remove();
                    ArrayList<RISCVInstruction> tmp = new ArrayList<>();
                    for (RISCVInstruction inst = next.getHead(); inst != null; inst = inst.next) {
                        tmp.add(inst);
                    }
                    for (RISCVInstruction inst : tmp) {
                        inst.block = block;
                        block.addInst(inst);
                    }
                    next.getNext().forEach(x -> {
                        x.removePrev(next);
                        x.addPrev(block);
                        block.addNext(x);
                    });
                    function.removeBlock(next);
                    updated = true;
                    break;
                }
            }
        } while (updated);
    }

    static boolean superCopy() {
        HashMap<RISCVBasicBlock, Integer> nInst = new HashMap<>();

        for (RISCVFunction function : root.getExternalFunctionSet()) {
            for (RISCVBasicBlock block : function.getBlockContain()) {
                int cnt = 0;
                for (RISCVInstruction inst = block.getHead(); inst != null; inst = inst.next) {
                    cnt++;
                }
                nInst.put(block, cnt);
            }
        }
        int instLimit = 200000 / nInst.size();
        boolean changed = false;
        for (RISCVFunction function : root.getExternalFunctionSet()) {
            for (RISCVBasicBlock block : function.getBlockContain()) {
                if (block.getTail() instanceof UnaryBranch) {
                    RISCVBasicBlock next = ((UnaryBranch) block.getTail()).offset;
                    if (nInst.get(block) + nInst.get(next) < instLimit) {
                        changed = true;
                        nInst.put(block, nInst.get(block) + nInst.get(next) - 1);
                        if (next == block) {
                            ArrayList <RISCVInstruction> tmp = new ArrayList<>();
                            for (RISCVInstruction inst = next.getHead(); inst != null; inst = inst.next) {
                                tmp.add(inst.copy());
                            }
                            block.getTail().remove();
                            for (RISCVInstruction rvInst : tmp) {
                                block.addInst(rvInst);
                            }
                        } else {
                            block.getTail().remove();
                            block.removeNext(next);
                            block.addNext(next.getNext());
                            next.getNext().forEach(x -> x.addPrev(block));
                            for (RISCVInstruction inst = next.getHead(); inst != null; inst = inst.next) {
                                RISCVInstruction rvInst = inst.copy();
                                rvInst.block = block;
                                block.addInst(rvInst);
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }

    public static void run(RISCVModule root) {
        Peephole.root = root;
        do {
            root.getExternalFunctionSet().forEach(Peephole::removeIdMove);
            root.getExternalFunctionSet().forEach(Peephole::combineBlocks);
            root.getExternalFunctionSet().forEach(Peephole::redundantElimination);
        } while (superCopy());
    }
}
