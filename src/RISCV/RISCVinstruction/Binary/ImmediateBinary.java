package RISCV.RISCVinstruction.Binary;

import IR.IRutility.IRVisitor;
import RISCV.RISCVUtility.RISCVVisitor;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVinstruction.RISCVInstruction;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;

import java.util.HashSet;

public class ImmediateBinary extends RISCVInstruction {
    public enum ImmediateBinaryOp{
        addi, slli, srai, andi, ori, xori, slti
    }
    public RISCVRegister rs, rd;
    public RISCVImmediate imm;
    public ImmediateBinaryOp op;
    public ImmediateBinary(RISCVRegister rs, RISCVImmediate imm, ImmediateBinaryOp op, RISCVRegister rd, RISCVBasicBlock RISCVBasicBlock) {
        super(RISCVBasicBlock);
        this.rs = rs;
        this.imm = imm;
        this.op = op;
        this.rd = rd;
    }

    public RISCVRegister getRs() {
        return rs;
    }

    public RISCVRegister getRd() {
        return rd;
    }

    public RISCVImmediate getImm() {
        return imm;
    }

    public ImmediateBinaryOp getOp() {
        return op;
    }

    public boolean mergeInst(){
        RISCVInstruction inst = this.next;
        if(inst != null && inst instanceof ImmediateBinary){
            if(this.op == ImmediateBinaryOp.addi && ((ImmediateBinary) inst).op == ImmediateBinaryOp.addi && this.rd.getColor() == ((ImmediateBinary) inst).getRs().getColor() && this.rd.getColor() == ((ImmediateBinary) inst).getRd().getColor()){
                int value = this.imm.getValue() + ((ImmediateBinary) inst).getImm().getValue();
                if(-(1 << 11) <= value && value <= (1 << 11) - 1){
                    this.rd.setColor(((ImmediateBinary) inst).rd.color);
                    this.imm.setValue(value);
                    inst.remove();
                    return true;
                }
            }
            else if(this.op == ImmediateBinaryOp.slli && ((ImmediateBinary) inst).op == ImmediateBinaryOp.slli && this.rd.getColor() == ((ImmediateBinary) inst).getRs().getColor() && this.rd.getColor() == ((ImmediateBinary) inst).getRd().getColor()){
                int value = this.imm.getValue() + ((ImmediateBinary) inst).getImm().getValue();
                if(-(1 << 11) <= value && value <= (1 << 11) - 1){
                    this.rd.setColor(((ImmediateBinary) inst).rd.color);
                    this.imm.setValue(value);
                    inst.remove();
                    return true;
                }
            }
            else if(this.op == ImmediateBinaryOp.srai && ((ImmediateBinary) inst).op == ImmediateBinaryOp.srai && this.rd.getColor() == ((ImmediateBinary) inst).getRs().getColor() && this.rd.getColor() == ((ImmediateBinary) inst).getRd().getColor()){
                int value = this.imm.getValue() + ((ImmediateBinary) inst).getImm().getValue();
                if(-(1 << 11) <= value && value <= (1 << 11) - 1){
                    this.rd.setColor(((ImmediateBinary) inst).rd.color);
                    this.imm.setValue(value);
                    inst.remove();
                    return true;
                }
            }
            else if(this.op == ImmediateBinaryOp.slli && ((ImmediateBinary) inst).op == ImmediateBinaryOp.srai && this.rd.getColor() == ((ImmediateBinary) inst).getRs().getColor() && this.rd.getColor() == ((ImmediateBinary) inst).getRd().getColor()){
                int value = this.imm.getValue() - ((ImmediateBinary) inst).getImm().getValue();
                if(value >= 0){
                    if(-(1 << 11) <= value && value <= (1 << 11) - 1){
                        this.rd.setColor(((ImmediateBinary) inst).rd.color);
                        this.imm.setValue(value);
                        inst.remove();
                        return true;
                    }
                }
                else if(value < 0){
                    value = -value;
                    if(-(1 << 11) <= value && value <= (1 << 11) - 1){
                        this.rd.setColor(((ImmediateBinary) inst).rd.color);
                        this.imm.setValue(value);
                        this.op = ImmediateBinaryOp.srai;
                        inst.remove();
                        return true;
                    }
                }
            }
            else if(this.op == ImmediateBinaryOp.srai && ((ImmediateBinary) inst).op == ImmediateBinaryOp.slli && this.rd.getColor() == ((ImmediateBinary) inst).getRs().getColor() && this.rd.getColor() == ((ImmediateBinary) inst).getRd().getColor()){
                int value = this.imm.getValue() - ((ImmediateBinary) inst).getImm().getValue();
                if(value >= 0){
                    if(-(1 << 11) <= value && value <= (1 << 11) - 1){
                        this.rd.setColor(((ImmediateBinary) inst).rd.color);
                        this.imm.setValue(value);
                        inst.remove();
                        return true;
                    }
                }
                else if(value < 0){
                    value = -value;
                    if(-(1 << 11) <= value && value <= (1 << 11) - 1){
                        this.rd.setColor(((ImmediateBinary) inst).rd.color);
                        this.imm.setValue(value);
                        this.op = ImmediateBinaryOp.slli;
                        inst.remove();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public HashSet<RISCVRegister> Uses() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rs);
        return res;
    }
    @Override
    public HashSet<RISCVRegister> Defs() {
        HashSet<RISCVRegister> res = new HashSet<>();
        res.add(rd);
        return res;
    }
    @Override
    public void UpdateUse(RISCVRegister Old, RISCVRegister New) {
        if(rs == Old) rs = New;
    }

    @Override
    public void UpdateDef(RISCVRegister Old, RISCVRegister New) {
        if(rd == Old) rd = New;
    }
    @Override
    public void updateOffset(int offset) { imm.updateOffset(offset); }

    @Override
    public RISCVInstruction copy() {
        return new ImmediateBinary(rs, imm, op, rd, instIn);
    }

    @Override
    public String toString() {
        return op.toString() + " " + rd.toString() + ", " + rs.toString() + ", " + imm.getValue();
    }

    @Override
    public void accept(RISCVVisitor visitor) {
        visitor.visit(this);
    }
}
