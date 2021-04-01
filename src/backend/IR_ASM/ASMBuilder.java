package backend.IR_ASM;


import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.*;
import IR.IRtype.*;
import IR.IRutility.IRVisitor;
import RISCV.RISCVoperand.RISCVimmediate.RISCVImmediate;
import RISCV.RISCVoperand.RISCVimmediate.RISCVRelocation;
import RISCV.RISCVoperand.RISCVimmediate.RISCVStackOffset;
import RISCV.RISCVoperand.RISCVregister.RISCVRegister;
import RISCV.RISCVoperand.RISCVregister.RISCVGlobalRegister;
import RISCV.RISCVoperand.RISCVregister.RISCVVirtualRegister;
import RISCV.RISCVbasicblock.RISCVBasicBlock;
import RISCV.RISCVfunction.RISCVFunction;
import RISCV.RISCVmodule.RISCVModule;
import RISCV.RISCVinstruction.*;
import RISCV.RISCVinstruction.Binary.ImmediateBinary;
import RISCV.RISCVinstruction.Binary.RegisterBinary;
import RISCV.RISCVinstruction.Branch.BinaryBranch;
import RISCV.RISCVinstruction.Branch.UnaryBranch;
import RISCV.RISCVinstruction.RISCVCall;
import RISCV.RISCVinstruction.RISCVReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static java.lang.Integer.max;
import static java.lang.Integer.min;


public class ASMBuilder implements IRVisitor {
    RISCVModule module;
    HashMap<IRFunction, RISCVFunction> functionMap;
    HashMap<IRBasicBlock, RISCVBasicBlock> blockMap;
    HashMap<IROperand, RISCVRegister> registerMap;
    RISCVBasicBlock currentBlock;
    RISCVFunction currentFunction;

    public ASMBuilder() {
        module = new RISCVModule();
        functionMap = new LinkedHashMap<>();
        blockMap = new LinkedHashMap<>();
        registerMap = new LinkedHashMap<>();
        currentBlock = null;
        currentFunction = null;
    }

    public RISCVModule getModule(){ return module; }

    private RISCVRegister getRegister(IROperand IR) {
        if(IR.isZero()) return module.getPhysicalRegister("zero");
        else if(IR instanceof IRGlobalVariable){
            if(!registerMap.containsKey(IR)){
                registerMap.put(IR, new RISCVGlobalRegister(IR.getIdentifier(), ((IRPointerType) IR.getOperandType()).getPointTo().getSize() / 8));
                module.addGlobalVariableSet((RISCVGlobalRegister) registerMap.get(IR));
            }
            return registerMap.get(IR);
        }
        else if(IR instanceof IRLocalRegister){
            if(!registerMap.containsKey(IR)) registerMap.put(IR, new RISCVVirtualRegister(max(1, IR.getOperandType().getSize() / 8)));
            return registerMap.get(IR);
        }
        else if(IR instanceof IRConstString){
            if(!registerMap.containsKey(IR)){
                registerMap.put(IR, new RISCVGlobalRegister(IR.getIdentifier(), ((IRPointerType) IR.getOperandType()).getPointTo().getSize() / 8));
                module.addConstStringMap((RISCVGlobalRegister) registerMap.get(IR), ((IRConstString) IR).getValue());
            }
            return registerMap.get(IR);
        }
        else if(IR instanceof IRConstBool) {
            RISCVVirtualRegister reg = new RISCVVirtualRegister(1);
            currentBlock.addInst(new RISCVLi(1, reg, currentBlock));
            return reg;
        }
        else if(IR instanceof IRConstInt){
            RISCVVirtualRegister reg = new RISCVVirtualRegister(4);
            currentBlock.addInst(new RISCVLi(((IRConstInt) IR).getValue(), reg, currentBlock));
            reg.SetConstValue(((IRConstInt) IR).getValue());
            return reg;
        }
        else return module.getPhysicalRegister("zero");
    }

    void addMv(IROperand value, RISCVRegister reg){
        if(value.isZero()) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), reg, currentBlock));
        else if (value.isImm()) currentBlock.addInst(new RISCVLi(((IRConstInt) value).getValue(), reg, currentBlock));
        else if (value instanceof IRConstBool) currentBlock.addInst(new RISCVLi(1, reg, currentBlock));
        else currentBlock.addInst(new RISCVMove(getRegister(value), reg, currentBlock));
    }
    @Override
    public void visit(Binary inst) {
        if(inst.getOp() == Binary.IRBinaryOpType.add){
            if(inst.getOp2().isZero()) currentBlock.addInst(new RISCVMove(getRegister(inst.getOp1()), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp1().isZero()) currentBlock.addInst(new RISCVMove(getRegister(inst.getOp2()), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.addi, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.addi, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.add, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Binary.IRBinaryOpType.sub){
            if(inst.getOp2().isZero()) currentBlock.addInst(new RISCVMove(getRegister(inst.getOp1()), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp1().isZero()) currentBlock.addInst(new RegisterBinary(module.getPhysicalRegister("zero"), getRegister(inst.getOp2()), RISCVInstruction.SCategory.sub, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(-((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.addi, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.sub, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Binary.IRBinaryOpType.mul){
            if(inst.getOp1().isZero() || inst.getOp2().isZero())currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.mul, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Binary.IRBinaryOpType.sdiv){
            if(inst.getOp1().isZero()) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.div, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Binary.IRBinaryOpType.srem){
            if(inst.getOp1().isZero()) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.rem, getRegister(inst.getResult()), currentBlock));
        }
    }

    @Override
    public void visit(BitwiseBinary inst) {
        if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.shl){
            if(inst.getOp1().isZero() || inst.getOp2().isZero()) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.slli, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.sll, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.ashr){
            if(inst.getOp1().isZero() || inst.getOp2().isZero()) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.srai, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.sra, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.and){
            if(inst.getOp1().isZero() || inst.getOp2().isZero()) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.andi, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.andi, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.and, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.or){
            if(inst.getOp1().isZero()) currentBlock.addInst(new RISCVMove(getRegister(inst.getOp2()), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isZero()) currentBlock.addInst(new RISCVMove(getRegister(inst.getOp1()), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.ori, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.ori, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.or, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.xor){
            if(inst.getOp1().isZero()) currentBlock.addInst(new RISCVMove(getRegister(inst.getOp2()), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isZero()) currentBlock.addInst(new RISCVMove(getRegister(inst.getOp1()), getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.xori, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.xori, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.xor, getRegister(inst.getResult()), currentBlock));
        }
    }

    @Override
    public void visit(GetElementPtr inst) {
        RISCVRegister dest;
        IRType type = ((IRPointerType)inst.getPtrval().getOperandType()).getPointTo();
        if (inst.getIndex().get(0) instanceof IRConstInt) {
            int index = ((IRConstInt) inst.getIndex().get(0)).getValue();
            if (index != 0) {
                dest = new RISCVVirtualRegister(4);
                IROperand op1 = inst.getPtrval();
                IRConstInt op2 = new IRConstInt(index * (type.getSize() / 8), IRIntType.IntTypeBytes.Int32);
                if(op2.isZero()) currentBlock.addInst(new RISCVMove(getRegister(op1), dest, currentBlock));
                else if(op1.isZero()) currentBlock.addInst(new RISCVMove(getRegister(op2), dest, currentBlock));
                else if(op2.isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(op1), new RISCVImmediate(op2.getValue()), ImmediateBinary.ImmediateBinaryOp.addi, dest, currentBlock));
                else if(op1.isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(op2), new RISCVImmediate(((IRConstInt) op1).getValue()), ImmediateBinary.ImmediateBinaryOp.addi, dest, currentBlock));
                else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.add, dest, currentBlock));
            } else {
                RISCVRegister ptr = getRegister(inst.getPtrval());
                if (ptr instanceof RISCVGlobalRegister) {
                    dest = new RISCVVirtualRegister(4);
                    currentBlock.addInst(new RISCVLa((RISCVGlobalRegister) ptr, dest, currentBlock));
                } else {
                    dest = ptr;
                }
            }
        } else {
            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
            dest = new RISCVVirtualRegister(4);
            IROperand op1 = inst.getIndex().get(0);
            IROperand op2 = new IRConstInt(type.getSize() / 8, IRIntType.IntTypeBytes.Int32);
            if(op1.isZero() || op2.isZero())currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), tmp, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.mul, tmp, currentBlock));
            currentBlock.addInst(new RegisterBinary(getRegister(inst.getPtrval()), tmp, RISCVInstruction.SCategory.add, dest, currentBlock));
        }
        if (!(inst.getIndex().size() == 1 || ((IRConstInt) inst.getIndex().get(1)).getValue() == 0)) {
            int offset = ((IRClassType) ((IRPointerType) inst.getPtrval().getOperandType()).getPointTo()).getOffsets().get(((IRConstInt) inst.getIndex().get(1)).getValue()).getValue() / 8;
            RISCVRegister newDest = new RISCVVirtualRegister(4);
            if (-(1 << 11) <= offset && offset <= (1 << 11) - 1) {
                currentBlock.addInst(new ImmediateBinary(dest, new RISCVImmediate(offset), ImmediateBinary.ImmediateBinaryOp.addi, newDest, currentBlock));
            } else {
                currentBlock.addInst(new RegisterBinary(dest, getRegister(new IRConstInt(offset, IRIntType.IntTypeBytes.Int32)), RISCVInstruction.SCategory.add, newDest, currentBlock));
            }
            dest = newDest;
        }
        currentBlock.addInst(new RISCVMove(dest, getRegister(inst.getResult()), currentBlock));
    }

    @Override
    public void visit(Icmp inst) {
        if(inst.getNext() instanceof IR.IRinstruction.Br && ((IR.IRinstruction.Br) inst.getNext()).getCond() == inst.getResult()) return;
        if(inst.getOp() == Icmp.IRIcmpOpType.slt){
            if(inst.getOp2().isZero())currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.slti, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isImm())currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.slti, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.slt, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Icmp.IRIcmpOpType.sgt){
            if(inst.getOp1().isZero()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.slti, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.slti, getRegister(inst.getResult()), currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp2()), getRegister(inst.getOp1()), RISCVInstruction.SCategory.slt, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Icmp.IRIcmpOpType.sle){
            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
            if(inst.getOp1().isZero()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.slti, tmp, currentBlock));
            else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.slti, tmp, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp2()), getRegister(inst.getOp1()), RISCVInstruction.SCategory.slt, tmp, currentBlock));
            currentBlock.addInst(new ImmediateBinary(tmp, new RISCVImmediate(1), ImmediateBinary.ImmediateBinaryOp.xori, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Icmp.IRIcmpOpType.sge){
            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
            if(inst.getOp2().isZero()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.slti, tmp, currentBlock));
            if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.slti, tmp, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.slt, tmp, currentBlock));
            currentBlock.addInst(new ImmediateBinary(tmp, new RISCVImmediate(1), ImmediateBinary.ImmediateBinaryOp.xori, getRegister(inst.getResult()), currentBlock));
        }
        else if(inst.getOp() == Icmp.IRIcmpOpType.eq){
            if(inst.getOp1().isZero()) currentBlock.addInst(new Sz(getRegister(inst.getOp2()), Sz.SzCategory.seqz, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isZero()) currentBlock.addInst(new Sz(getRegister(inst.getOp1()), Sz.SzCategory.seqz, getRegister(inst.getResult()), currentBlock));
            else{
                RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
                if(inst.getOp2().isZero()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                else if(inst.getOp1().isZero()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                else if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                currentBlock.addInst(new Sz(tmp, Sz.SzCategory.seqz, getRegister(inst.getResult()), currentBlock));
            }
        }
        else if(inst.getOp() == Icmp.IRIcmpOpType.ne){
            if(inst.getOp1().isZero()) currentBlock.addInst(new Sz(getRegister(inst.getOp2()), Sz.SzCategory.snez, getRegister(inst.getResult()), currentBlock));
            else if(inst.getOp2().isZero()) currentBlock.addInst(new Sz(getRegister(inst.getOp1()), Sz.SzCategory.snez, getRegister(inst.getResult()), currentBlock));
            else{
                RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
                if(inst.getOp2().isZero()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                else if(inst.getOp1().isZero()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(0), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                if(inst.getOp2().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp1()), new RISCVImmediate(((IRConstInt) inst.getOp2()).getValue()), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                else if(inst.getOp1().isImm()) currentBlock.addInst(new ImmediateBinary(getRegister(inst.getOp2()), new RISCVImmediate(((IRConstInt) inst.getOp1()).getValue()), ImmediateBinary.ImmediateBinaryOp.xori, tmp, currentBlock));
                else currentBlock.addInst(new RegisterBinary(getRegister(inst.getOp1()), getRegister(inst.getOp2()), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                currentBlock.addInst(new Sz(tmp, Sz.SzCategory.snez, getRegister(inst.getResult()), currentBlock));
            }
        }
    }

    @Override
    public void visit(IR.IRinstruction.Br inst) {
        if(inst.getCond() == null) currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfTrue()), currentBlock));
        else if (inst.getCond() instanceof IRConstBool){
            if(((IRConstBool) inst.getCond()).getValue()){
                currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfTrue()), currentBlock));
                currentBlock.disconnect(blockMap.get(inst.getIfFalse()));
            }
            else{
                currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfFalse()), currentBlock));
                currentBlock.disconnect(blockMap.get(inst.getIfTrue()));
            }
        }
        else{
            if (inst.BinaryBr()) {
                if(((Icmp) inst.getPrev()).getOp() == Icmp.IRIcmpOpType.slt) currentBlock.addInst(new BinaryBranch(getRegister(((Icmp) inst.getPrev()).getOp1()), getRegister(((Icmp) inst.getPrev()).getOp2()), BinaryBranch.BCategory.lt, blockMap.get(inst.getIfTrue()), currentBlock));
                else if(((Icmp) inst.getPrev()).getOp() == Icmp.IRIcmpOpType.sgt) currentBlock.addInst(new BinaryBranch(getRegister(((Icmp) inst.getPrev()).getOp1()), getRegister(((Icmp) inst.getPrev()).getOp2()), BinaryBranch.BCategory.gt, blockMap.get(inst.getIfTrue()), currentBlock));
                else if(((Icmp) inst.getPrev()).getOp() == Icmp.IRIcmpOpType.sle) currentBlock.addInst(new BinaryBranch(getRegister(((Icmp) inst.getPrev()).getOp1()), getRegister(((Icmp) inst.getPrev()).getOp2()), BinaryBranch.BCategory.le, blockMap.get(inst.getIfTrue()), currentBlock));
                else if(((Icmp) inst.getPrev()).getOp() == Icmp.IRIcmpOpType.sge) currentBlock.addInst(new BinaryBranch(getRegister(((Icmp) inst.getPrev()).getOp1()), getRegister(((Icmp) inst.getPrev()).getOp2()), BinaryBranch.BCategory.ge, blockMap.get(inst.getIfTrue()), currentBlock));
                else if(((Icmp) inst.getPrev()).getOp() == Icmp.IRIcmpOpType.eq) currentBlock.addInst(new BinaryBranch(getRegister(((Icmp) inst.getPrev()).getOp1()), getRegister(((Icmp) inst.getPrev()).getOp2()), BinaryBranch.BCategory.eq, blockMap.get(inst.getIfTrue()), currentBlock));
                else if(((Icmp) inst.getPrev()).getOp() == Icmp.IRIcmpOpType.ne) currentBlock.addInst(new BinaryBranch(getRegister(((Icmp) inst.getPrev()).getOp1()), getRegister(((Icmp) inst.getPrev()).getOp2()), BinaryBranch.BCategory.ne, blockMap.get(inst.getIfTrue()), currentBlock));
            }
            else currentBlock.addInst(new BinaryBranch(getRegister(inst.getCond()), module.getPhysicalRegister("zero"), BinaryBranch.BCategory.ne, blockMap.get(inst.getIfTrue()), currentBlock));
            currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfFalse()), currentBlock));
        }
    }

    @Override
    public void visit(IR.IRinstruction.Call inst) {
        for (int i = 0; i < min(8, inst.getFunctionArgs().size()); i++) {
            if (getRegister(inst.getFunctionArgs().get(i)) instanceof RISCVGlobalRegister) currentBlock.addInst(new RISCVLa((RISCVGlobalRegister) getRegister(inst.getFunctionArgs().get(i)), module.getFuncArgs().get(i), currentBlock));
            else currentBlock.addInst(new RISCVMove(getRegister(inst.getFunctionArgs().get(i)), module.getFuncArgs().get(i), currentBlock));
        }
        int offset = 0;
        for (int i = 8; i < inst.getFunctionArgs().size(); i++, offset += 4) currentBlock.addInst(new RISCVStore(module.getPhysicalRegister("sp"), new RISCVImmediate(offset), getRegister(inst.getFunctionArgs().get(i)), inst.getFunctionArgs().get(i).getOperandType().getSize() / 8, currentBlock));
        currentFunction.setOffset(max(currentFunction.getOffset(), offset));
        currentBlock.addInst(new RISCVCall(module, functionMap.get(inst.getFnptrval()), currentBlock));
        if (!(inst.getResult().getOperandType() instanceof IRVoidType)) currentBlock.addInst(new RISCVMove(module.getFuncArgs().get(0), getRegister(inst.getResult()), currentBlock));
    }
    @Override
    public void visit(BitCast inst) { addMv(inst.getValue(), getRegister(inst.getResult()));}

    @Override
    public void visit(Trunc inst) { addMv(inst.getValue(), getRegister(inst.getResult())); }

    @Override
    public void visit(Zext inst) { addMv(inst.getValue(), getRegister(inst.getResult())); }

    @Override
    public void visit(Load inst) { currentBlock.addInst(new RISCVLoad(getRegister(inst.getPointer()), new RISCVImmediate(0), getRegister(inst.getResult()), inst.getResult().getOperandType().getSize() / 8, currentBlock)); }

    @Override
    public void visit(IR.IRinstruction.Move inst) { addMv(inst.getValue(), getRegister(inst.getResult())); }

    @Override
    public void visit(IR.IRinstruction.Ret inst) { if (!(inst.getValue().getOperandType() instanceof IRVoidType)) addMv(inst.getValue(), module.getFuncArgs().get(0)); }

    @Override
    public void visit(Phi inst) {}

    @Override
    public void visit(Alloca inst) {}

    @Override
    public void visit(IRFunction func) {
        currentFunction = functionMap.get(func);
        currentFunction.getEntry().addInst(new ImmediateBinary(module.getPhysicalRegister("sp"), new RISCVStackOffset(0, true), ImmediateBinary.ImmediateBinaryOp.addi, module.getPhysicalRegister("sp"), currentFunction.getEntry()));
        ArrayList<RISCVVirtualRegister> callee = new ArrayList<>();
        module.getCalleeSavedRegs().forEach(rs -> {
            RISCVVirtualRegister rd = new RISCVVirtualRegister(4);
            currentFunction.getEntry().addInst(new RISCVMove(rs, rd, currentFunction.getEntry()));
            callee.add(rd);
        });
        RISCVVirtualRegister ra = new RISCVVirtualRegister(4);
        currentFunction.getEntry().addInst(new RISCVMove(module.getPhysicalRegister("ra"), ra, currentFunction.getEntry()));
        ArrayList<IROperand> parameters = new ArrayList<>();
        if(func.getClassPtr() != null) parameters.add(func.getClassPtr());
        parameters.addAll(func.getParameters());
        for (int i = 0; i < min(8, parameters.size()); i++) currentFunction.getEntry().addInst(new RISCVMove(module.getFuncArgs().get(i), currentFunction.getParameters().get(i), currentFunction.getEntry()));
        for (int i = 8; i < parameters.size(); i++) currentFunction.getEntry().addInst(new RISCVLoad(module.getPhysicalRegister("sp"), new RISCVStackOffset((i - 8) * 4, false), currentFunction.getParameters().get(i), max(1, parameters.get(i).getOperandType().getSize() / 8), currentFunction.getEntry()));
        func.getBlockContain().forEach(this::visit);
        for (int i = 0; i < callee.size(); i++) currentFunction.getExit().addInst(new RISCVMove(callee.get(i), module.getCalleeSavedRegs().get(i), currentFunction.getExit()));
        currentFunction.getExit().addInst(new RISCVMove(ra, module.getPhysicalRegister("ra"), currentFunction.getExit()));
        currentFunction.getExit().addInst(new ImmediateBinary(module.getPhysicalRegister("sp"), new RISCVStackOffset(0, false), ImmediateBinary.ImmediateBinaryOp.addi, module.getPhysicalRegister("sp"), currentFunction.getExit()));
        currentFunction.getExit().addInst(new RISCVReturn(module, currentFunction.getExit()));
    }

    @Override
    public void visit(IRModule Module) {
        Module.getInternalFunctionMap().forEach(((id, function) -> {
            RISCVFunction RISCVFunction = new RISCVFunction(module, id);
            module.addInternalFunctionSet(RISCVFunction);
            functionMap.put(function, RISCVFunction);
        }));
        Module.getExternalFunctionMap().forEach((id, function) -> {
            RISCVFunction RISCVFunction = new RISCVFunction(module, id);
            function.getBlockContain().forEach(block -> {
                RISCVBasicBlock RISCVBasicBlock = new RISCVBasicBlock("." + function.getIdentifier() + "_" + block.getIdentifier());
                blockMap.put(block, RISCVBasicBlock);
                RISCVFunction.addBlock(RISCVBasicBlock);
            });
            RISCVFunction.setEntry(blockMap.get(function.getEntry()));
            RISCVFunction.setExit(blockMap.get(function.getExit()));
            if(function.getClassPtr() != null){ RISCVFunction.addParameter(getRegister(function.getClassPtr())); }
            function.getParameters().forEach(parameter -> RISCVFunction.addParameter(getRegister(parameter)));
            module.addExternalFunctionSet(RISCVFunction);
            functionMap.put(function, RISCVFunction);
        });
        Module.getExternalFunctionMap().forEach(((id, function) -> visit(function)));
    }

    @Override
    public void visit(IRBasicBlock block) {
        currentBlock = blockMap.get(block);
        block.getPrev().forEach(prev -> currentBlock.addPrev(blockMap.get(prev)));
        block.getNext().forEach(next -> currentBlock.addNext(blockMap.get(next)));
        for(IRInstruction inst = block.getHead(); inst != null && !(currentBlock.Terminate()); inst = inst.getNext()) inst.accept(this);
    }

    @Override
    public void visit(Store inst) {
        if (getRegister(inst.getPointer()) instanceof RISCVGlobalRegister) {
            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
            currentBlock.addInst(new RISCVLui(new RISCVRelocation((RISCVGlobalRegister) getRegister(inst.getPointer()), RISCVRelocation.RelocationType.hi), tmp, currentBlock));
            currentBlock.addInst(new RISCVStore(tmp, new RISCVRelocation((RISCVGlobalRegister) getRegister(inst.getPointer()), RISCVRelocation.RelocationType.lo),getRegister(inst.getValue()), inst.getValue().getOperandType().getSize() / 8, currentBlock));
        }
        else currentBlock.addInst(new RISCVStore(getRegister(inst.getPointer()), new RISCVImmediate(0), getRegister(inst.getValue()), inst.getValue().getOperandType().getSize() / 8, currentBlock));
    }
}

