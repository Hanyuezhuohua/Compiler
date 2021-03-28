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
import Util.error.ErrorMessage;

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
    HashMap<Integer, RISCVRegister> ConstMap;
    RISCVBasicBlock currentBlock;
    RISCVFunction currentFunction;

    public ASMBuilder() {
        module = new RISCVModule();
        functionMap = new LinkedHashMap<>();
        blockMap = new LinkedHashMap<>();
        registerMap = new LinkedHashMap<>();
        ConstMap = new LinkedHashMap<>();
        currentBlock = null;
        currentFunction = null;
    }

    public RISCVModule getModule(){ return module; }

    private RISCVRegister getRegister(IROperand IR) {
        if(ZeroChecker(IR)) return module.getPhysicalRegister("zero");
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
            if(!ConstMap.containsKey(((IRConstInt) IR).getValue())){
                RISCVVirtualRegister reg = new RISCVVirtualRegister(4);
                currentBlock.addInst(new RISCVLi(((IRConstInt) IR).getValue(), reg, currentBlock));
                reg.SetConstValue(((IRConstInt) IR).getValue());
                ConstMap.put(((IRConstInt) IR).getValue(), reg);
            }
            return ConstMap.get(((IRConstInt) IR).getValue());
        }
        else return module.getPhysicalRegister("zero");
    }

    void addMv(IROperand value, RISCVRegister reg){
        if(ZeroChecker(value)) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), reg, currentBlock));
        else if (value instanceof IRConstInt && ImmLengthChecker(((IRConstInt) value).getValue())) currentBlock.addInst(new RISCVLi(((IRConstInt) value).getValue(), reg, currentBlock));
        else if (value instanceof IRConstBool) currentBlock.addInst(new RISCVLi(1, reg, currentBlock));
        else currentBlock.addInst(new RISCVMove(getRegister(value), reg, currentBlock));
    }

    boolean ImmLengthChecker(int x) {
        return -(1 << 11) <= x && x <= (1 << 11) - 1;
    }

    boolean ZeroChecker(IROperand operand) {
        if(operand instanceof IRConstInt){
            return ((IRConstInt) operand).getValue() == 0;
        }
        else if(operand instanceof IRConstBool){
            return !((IRConstBool) operand).getValue();
        }
        else if(operand instanceof IRConstNull) return true;
        else return operand instanceof IRConstVoid;
    }

    boolean checkOperandConst(IROperand operand) {
        return ZeroChecker(operand) ||operand instanceof IRConstInt;
    }

    int getOperandConst(IROperand operand) {
        return ZeroChecker(operand) ? 0 : ((IRConstInt) operand).getValue();
    }

    boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0;
    }

    boolean checkOperandImm(IROperand operand) {
        if (ZeroChecker(operand)) return true;
        else return operand instanceof IRConstInt && ImmLengthChecker(((IRConstInt) operand).getValue());
    }

    boolean checkNegOperandImm(IROperand operand) {
        if (ZeroChecker(operand)) return true;
        else return operand instanceof IRConstInt && ImmLengthChecker(-((IRConstInt) operand).getValue());
    }

    RISCVImmediate getImm(IROperand operand) {
        if (ZeroChecker(operand)) return new RISCVImmediate(0);
        else if (operand instanceof IRConstInt) return new RISCVImmediate(((IRConstInt) operand).getValue());
        else throw new ErrorMessage();
    }
    RISCVImmediate getNegImm(IROperand operand) {
        if (ZeroChecker(operand)) return new RISCVImmediate(0);
        else if (operand instanceof IRConstInt) return new RISCVImmediate(-((IRConstInt) operand).getValue());
        else throw new ErrorMessage();
    }

    void makeBinary(IROperand src1, IROperand src2, String op, RISCVRegister rd) {
        RISCVInstruction.SCategory sop;
        boolean abelian = false, iType = false;
        switch (op) {
            case "+": sop = RISCVInstruction.SCategory.add; abelian = true; iType = true; break;
            case "-": sop = RISCVInstruction.SCategory.sub; iType = true; break;
            case "*": sop = RISCVInstruction.SCategory.mul; break;
            case "/": sop = RISCVInstruction.SCategory.div; break;
            case "%": sop = RISCVInstruction.SCategory.rem; break;
            case "^": sop = RISCVInstruction.SCategory.xor; abelian = true; iType = true; break;
            case "&": sop = RISCVInstruction.SCategory.and; abelian = true; iType = true; break;
            case "|": sop = RISCVInstruction.SCategory.or; abelian = true; iType = true; break;
            case "<<": sop = RISCVInstruction.SCategory.sll; iType = true; break;
            case ">>": sop = RISCVInstruction.SCategory.sra; iType = true; break;
            default: throw new ErrorMessage();
        }
        if (ZeroChecker(src2)) {
            if (sop == RISCVInstruction.SCategory.and || sop == RISCVInstruction.SCategory.mul) {
                currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            } else {
                currentBlock.addInst(new RISCVMove(getRegister(src1), rd, currentBlock));
            }
        } else if (ZeroChecker(src1)) {
            if (sop == RISCVInstruction.SCategory.add || sop == RISCVInstruction.SCategory.sub || sop == RISCVInstruction.SCategory.or || sop == RISCVInstruction.SCategory.xor) {
                currentBlock.addInst(new RegisterBinary(module.getPhysicalRegister("zero"), getRegister(src2), sop, rd, currentBlock));
            } else {
                currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            }
        } else if (iType) {
            if (sop.equals(RISCVInstruction.SCategory.sub) && checkNegOperandImm(src2)) {
                currentBlock.addInst(new ImmediateBinary(getRegister(src1), getNegImm(src2), RISCVInstruction.SCategory.add, rd, currentBlock));
            } else if (!sop.equals(RISCVInstruction.SCategory.sub) && checkOperandImm(src2)) {
                currentBlock.addInst(new ImmediateBinary(getRegister(src1), getImm(src2), sop, rd, currentBlock));
            } else if (abelian && checkOperandImm(src1)) {
                currentBlock.addInst(new ImmediateBinary(getRegister(src2), getImm(src1), sop, rd, currentBlock));
            } else {
                currentBlock.addInst(new RegisterBinary(getRegister(src1), getRegister(src2), sop, rd, currentBlock));
            }
        } else {
 /*           if (sop == RISCVInstruction.SCategory.mul && checkOperandConst(src1) && isPowerOfTwo(getOperandConst(src1))) {
                currentBlock.addInst(new ImmediateBinary(getRegister(src2), new RISCVImmediate(Integer.numberOfTrailingZeros(getOperandConst(src1))), RISCVInstruction.SCategory.sll, rd, currentBlock));
            } else if (sop == RISCVInstruction.SCategory.mul && checkOperandConst(src2) && isPowerOfTwo(getOperandConst(src2))) {
                currentBlock.addInst(new ImmediateBinary(getRegister(src1), new RISCVImmediate(Integer.numberOfTrailingZeros(getOperandConst(src2))), RISCVInstruction.SCategory.sll, rd, currentBlock));
            } else if (sop == RISCVInstruction.SCategory.div && checkOperandConst(src2) && isPowerOfTwo(getOperandConst(src2))) { // wrong when negative, assumed undefined
                currentBlock.addInst(new ImmediateBinary(getRegister(src1), new RISCVImmediate(Integer.numberOfTrailingZeros(getOperandConst(src2))), RISCVInstruction.SCategory.sra, rd, currentBlock));
            } else if (sop == RISCVInstruction.SCategory.rem && checkOperandConst(src2) && isPowerOfTwo(getOperandConst(src2))) { // wrong when negative, assumed undefined
                currentBlock.addInst(new ImmediateBinary(getRegister(src1), new RISCVImmediate((1 << Integer.numberOfTrailingZeros(getOperandConst(src2))) - 1), RISCVInstruction.SCategory.and, rd, currentBlock));
            } else {*/
                currentBlock.addInst(new RegisterBinary(getRegister(src1), getRegister(src2), sop, rd, currentBlock));
           // }
        }
    }
    @Override
    public void visit(Binary inst) {
        Binary.IRBinaryOpType op = inst.getOp();
        IROperand op1 = inst.getOp1();
        IROperand op2 = inst.getOp2();
        RISCVRegister rd = getRegister(inst.getResult());
        if(op == Binary.IRBinaryOpType.add){
            if(ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(getRegister(op1), rd, currentBlock));
            else if(ZeroChecker(op1)) currentBlock.addInst(new RISCVMove(getRegister(op2), rd, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.add, rd, currentBlock));
            else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.add, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.add, rd, currentBlock));
        }
        else if(op == Binary.IRBinaryOpType.sub){
            if(ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(getRegister(op1), rd, currentBlock));
            else if(ZeroChecker(op1)) currentBlock.addInst(new RegisterBinary(module.getPhysicalRegister("zero"), getRegister(op2), RISCVInstruction.SCategory.sub, rd, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getNegImm(op2), RISCVInstruction.SCategory.add, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.sub, rd, currentBlock));
        }
        else if(op == Binary.IRBinaryOpType.mul){
            if(ZeroChecker(op1) || ZeroChecker(op2))currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.mul, rd, currentBlock));
        }
        else if(op == Binary.IRBinaryOpType.sdiv){
            if(ZeroChecker(op1)) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.div, rd, currentBlock));
        }
        else if(op == Binary.IRBinaryOpType.srem){
            if(ZeroChecker(op1)) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.rem, rd, currentBlock));
        }
    }

    @Override
    public void visit(BitwiseBinary inst) {
        BitwiseBinary.IRBitwiseBinaryOpType op = inst.getOp();
        IROperand op1 = inst.getOp1();
        IROperand op2 = inst.getOp2();
        RISCVRegister rd = getRegister(inst.getResult());
        if(op == BitwiseBinary.IRBitwiseBinaryOpType.shl){
            if(ZeroChecker(op1) || ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.sll, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.sll, rd, currentBlock));
        }
        else if(op == BitwiseBinary.IRBitwiseBinaryOpType.ashr){
            if(ZeroChecker(op1) || ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.sra, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.sra, rd, currentBlock));
        }
        else if(op == BitwiseBinary.IRBitwiseBinaryOpType.and){
            if(ZeroChecker(op1) || ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(module.getPhysicalRegister("zero"), rd, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.and, rd, currentBlock));
            else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.and, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.and, rd, currentBlock));
        }
        else if(op == BitwiseBinary.IRBitwiseBinaryOpType.or){
            if(ZeroChecker(op1)) currentBlock.addInst(new RISCVMove(getRegister(op2), rd, currentBlock));
            else if(ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(getRegister(op1), rd, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.or, rd, currentBlock));
            else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.or, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.or, rd, currentBlock));
        }
        else if(op == BitwiseBinary.IRBitwiseBinaryOpType.xor){
            if(ZeroChecker(op1)) currentBlock.addInst(new RISCVMove(getRegister(op2), rd, currentBlock));
            else if(ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(getRegister(op1), rd, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.xor, rd, currentBlock));
            else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.xor, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.xor, rd, currentBlock));
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
                IROperand op2 = new IRConstInt(index * (type.getSize() / 8), IRIntType.IntTypeBytes.Int32);
                if(ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(getRegister(op1), dest, currentBlock));
                else if(ZeroChecker(op1)) currentBlock.addInst(new RISCVMove(getRegister(op2), dest, currentBlock));
                else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.add, dest, currentBlock));
                else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.add, dest, currentBlock));
                else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.add, dest, currentBlock));
            } else {
                RISCVRegister ptr = getRegister(inst.getPtrval());
                if (ptr instanceof RISCVGlobalRegister) {
                    dest = new RISCVVirtualRegister(4);
                    currentBlock.addInst(new RISCVLa((RISCVGlobalRegister) ptr, dest, currentBlock));
                    if (module.getConstStringMap().containsKey(ptr)) {
                    }
                } else {
                    dest = ptr;
                }
            }
        } else {
            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
            dest = new RISCVVirtualRegister(4);
            IROperand op1 = inst.getIndex().get(0);
            IROperand op2 = new IRConstInt(type.getSize() / 8, IRIntType.IntTypeBytes.Int32);
            if(ZeroChecker(op2)) currentBlock.addInst(new RISCVMove(getRegister(op1), tmp, currentBlock));
            else if(ZeroChecker(op1)) currentBlock.addInst(new RISCVMove(getRegister(op2), tmp, currentBlock));
            else if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.add, tmp, currentBlock));
            else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.add, tmp, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.add, dest, currentBlock));
            currentBlock.addInst(new RegisterBinary(getRegister(inst.getPtrval()), tmp, RISCVInstruction.SCategory.add, dest, currentBlock));
        }
        if (!(inst.getIndex().size() == 1 || ((IRConstInt) inst.getIndex().get(1)).getValue() == 0)) {
            int offset = ((IRClassType) ((IRPointerType) inst.getPtrval().getOperandType()).getPointTo()).getOffsets().get(((IRConstInt) inst.getIndex().get(1)).getValue()).getValue() / 8;
            RISCVRegister newDest = new RISCVVirtualRegister(4);
            if (ImmLengthChecker(offset)) {
                currentBlock.addInst(new ImmediateBinary(dest, new RISCVImmediate(offset), RISCVInstruction.SCategory.add, newDest, currentBlock));
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
        Icmp.IRIcmpOpType op = inst.getOp();
        IROperand op1 = inst.getOp1();
        IROperand op2 = inst.getOp2();
        RISCVRegister rd = getRegister(inst.getResult());
        if(op == Icmp.IRIcmpOpType.slt){
            if(checkOperandImm(op2))currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.slt, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.slt, rd, currentBlock));
        }
        else if(op == Icmp.IRIcmpOpType.sgt){
            if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.slt, rd, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op2), getRegister(op1), RISCVInstruction.SCategory.slt, rd, currentBlock));
        }
        else if(op == Icmp.IRIcmpOpType.sle){
            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
            if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.slt, tmp, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op2), getRegister(op1), RISCVInstruction.SCategory.slt, tmp, currentBlock));
            currentBlock.addInst(new ImmediateBinary(tmp, new RISCVImmediate(1), RISCVInstruction.SCategory.xor, rd, currentBlock));
        }
        else if(op == Icmp.IRIcmpOpType.sge){
            RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
            if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.slt, tmp, currentBlock));
            else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.slt, tmp, currentBlock));
            currentBlock.addInst(new ImmediateBinary(tmp, new RISCVImmediate(1), RISCVInstruction.SCategory.xor, rd, currentBlock));
        }
        else if(op == Icmp.IRIcmpOpType.eq){
            if(ZeroChecker(op1)) currentBlock.addInst(new Sz(getRegister(op2), Sz.SzCategory.seqz, rd, currentBlock));
            else if(ZeroChecker(op2)) currentBlock.addInst(new Sz(getRegister(op1), Sz.SzCategory.seqz, rd, currentBlock));
            else{
                RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
                if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                currentBlock.addInst(new Sz(tmp, Sz.SzCategory.seqz, rd, currentBlock));
            }
        }
        else if(op == Icmp.IRIcmpOpType.ne){
            if(ZeroChecker(op1)) currentBlock.addInst(new Sz(getRegister(op2), Sz.SzCategory.snez, rd, currentBlock));
            else if(ZeroChecker(op2)) currentBlock.addInst(new Sz(getRegister(op1), Sz.SzCategory.snez, rd, currentBlock));
            else{
                RISCVVirtualRegister tmp = new RISCVVirtualRegister(4);
                if(checkOperandImm(op2)) currentBlock.addInst(new ImmediateBinary(getRegister(op1), getImm(op2), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                else if(checkOperandImm(op1)) currentBlock.addInst(new ImmediateBinary(getRegister(op2), getImm(op1), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                else currentBlock.addInst(new RegisterBinary(getRegister(op1), getRegister(op2), RISCVInstruction.SCategory.xor, tmp, currentBlock));
                currentBlock.addInst(new Sz(tmp, Sz.SzCategory.snez, rd, currentBlock));
            }
        }
    }

    @Override
    public void visit(IR.IRinstruction.Br inst) {
        if(inst.getCond() == null){
            currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfTrue()), currentBlock));
            return;
        }
        else if (inst.getCond() instanceof IRConstBool){
            if(((IRConstBool) inst.getCond()).getValue()){
                currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfTrue()), currentBlock));
                currentBlock.disconnect(blockMap.get(inst.getIfFalse()));
            }
            else{
                currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfFalse()), currentBlock));
                currentBlock.disconnect(blockMap.get(inst.getIfTrue()));
            }
            return;
        }
        else if (inst.BinaryBr()) {
            Icmp cmp = (Icmp) inst.getPrev();
            Icmp.IRIcmpOpType op = cmp.getOp();
            IROperand op1 = cmp.getOp1();
            IROperand op2 = cmp.getOp2();
            if(op == Icmp.IRIcmpOpType.slt) currentBlock.addInst(new BinaryBranch(getRegister(op1), getRegister(op2), BinaryBranch.BCategory.lt, blockMap.get(inst.getIfTrue()), currentBlock));
            else if(op == Icmp.IRIcmpOpType.sgt) currentBlock.addInst(new BinaryBranch(getRegister(op1), getRegister(op2), BinaryBranch.BCategory.gt, blockMap.get(inst.getIfTrue()), currentBlock));
            else if(op == Icmp.IRIcmpOpType.sle) currentBlock.addInst(new BinaryBranch(getRegister(op1), getRegister(op2), BinaryBranch.BCategory.le, blockMap.get(inst.getIfTrue()), currentBlock));
            else if(op == Icmp.IRIcmpOpType.sge) currentBlock.addInst(new BinaryBranch(getRegister(op1), getRegister(op2), BinaryBranch.BCategory.ge, blockMap.get(inst.getIfTrue()), currentBlock));
            else if(op == Icmp.IRIcmpOpType.eq) currentBlock.addInst(new BinaryBranch(getRegister(op1), getRegister(op2), BinaryBranch.BCategory.eq, blockMap.get(inst.getIfTrue()), currentBlock));
            else if(op == Icmp.IRIcmpOpType.ne) currentBlock.addInst(new BinaryBranch(getRegister(op1), getRegister(op2), BinaryBranch.BCategory.ne, blockMap.get(inst.getIfTrue()), currentBlock));
        }
        else currentBlock.addInst(new BinaryBranch(getRegister(inst.getCond()), module.getPhysicalRegister("zero"), BinaryBranch.BCategory.ne, blockMap.get(inst.getIfTrue()), currentBlock));
        currentBlock.addInst(new UnaryBranch(blockMap.get(inst.getIfFalse()), currentBlock));
    }

    @Override
    public void visit(IR.IRinstruction.Call inst) {
        for (int i = 0; i < min(8, inst.getFunctionArgs().size()); i++) {
            RISCVRegister reg = getRegister(inst.getFunctionArgs().get(i));
            if (reg instanceof RISCVGlobalRegister) {
                currentBlock.addInst(new RISCVLa((RISCVGlobalRegister) reg, module.getFuncArgs().get(i), currentBlock));
            } else {
                currentBlock.addInst(new RISCVMove(reg, module.getFuncArgs().get(i), currentBlock));
            }
        }
        int paramInStackOffset = 0;
        for (int i = 8; i < inst.getFunctionArgs().size(); i++) {
            IROperand param = inst.getFunctionArgs().get(i);
            currentBlock.addInst(new RISCVStore(module.getPhysicalRegister("sp"), new RISCVImmediate(paramInStackOffset), getRegister(param), param.getOperandType().getSize() / 8, currentBlock));
            paramInStackOffset += 4;
        }
        currentFunction.setOffset(max(currentFunction.getOffset(), paramInStackOffset));
        currentBlock.addInst(new RISCVCall(module, functionMap.get(inst.getFnptrval()), currentBlock));
        if (!(inst.getResult().getOperandType() instanceof IRVoidType)) {
            currentBlock.addInst(new RISCVMove(module.getFuncArgs().get(0), getRegister(inst.getResult()), currentBlock));
        }
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
        RISCVFunction function = functionMap.get(func);
        currentFunction = function;
        RISCVStackOffset stackFrame = new RISCVStackOffset(0, true);
        function.setTailCall(function.getEntry());
        function.setEntry(new RISCVBasicBlock("." + func.getIdentifier() + "_entry"));
        function.getEntry().addInst(new ImmediateBinary(module.getPhysicalRegister("sp"), stackFrame, RISCVInstruction.SCategory.add, module.getPhysicalRegister("sp"), function.getEntry()));
        ArrayList<RISCVVirtualRegister> calleeVRegs = new ArrayList<>();
        module.getCalleeSavedRegs().forEach(pReg -> {
            RISCVVirtualRegister reg = new RISCVVirtualRegister(4);
            function.getEntry().addInst(new RISCVMove(pReg, reg, function.getEntry()));
            calleeVRegs.add(reg);
        });
        RISCVVirtualRegister vRa = new RISCVVirtualRegister(4);
        function.getEntry().addInst(new RISCVMove(module.getPhysicalRegister("ra"), vRa, function.getEntry()));
        ArrayList<IROperand> parameters = new ArrayList<>();
        if(func.getClassPtr() != null) parameters.add(func.getClassPtr());
        parameters.addAll(func.getParameters());
        for (int i = 0; i < min(8, parameters.size()); i++) function.getEntry().addInst(new RISCVMove(module.getFuncArgs().get(i), function.getParameters().get(i), function.getEntry()));
        int paramInStackOffset = 0;
        for (int i = 8; i < parameters.size(); i++) {
            function.getEntry().addInst(new RISCVLoad(module.getPhysicalRegister("sp"), new RISCVStackOffset(paramInStackOffset, false), function.getParameters().get(i), max(1, parameters.get(i).getOperandType().getSize() / 8), function.getEntry()));
            paramInStackOffset += 4;
        }
        function.getEntry().addInst(new UnaryBranch(function.getTailCall(), function.getEntry()));
        function.getEntry().addNext(function.getTailCall());
        function.getTailCall().addPrev(function.getEntry());
        function.addBlock(function.getEntry());
        func.getBlockContain().forEach(this::visit);
        for (int i = 0; i < calleeVRegs.size(); i++) function.getExit().addInst(new RISCVMove(calleeVRegs.get(i), module.getCalleeSavedRegs().get(i), function.getExit()));
        function.getExit().addInst(new RISCVMove(vRa, module.getPhysicalRegister("ra"), function.getExit()));
        function.getExit().addInst(new ImmediateBinary(module.getPhysicalRegister("sp"), new RISCVStackOffset(0, false), RISCVInstruction.SCategory.add, module.getPhysicalRegister("sp"), function.getExit()));
        function.getExit().addInst(new RISCVReturn(module, function.getExit()));
    }

    @Override
    public void visit(IRModule Module) {
        Module.getInternalFunctionMap().forEach(((id, function) -> {
            RISCVFunction func = new RISCVFunction(module, id);
            module.addInternalFunctionSet(func);
            functionMap.put(function, func);
        }));
        Module.getExternalFunctionMap().forEach((s, function) -> {
            RISCVFunction RISCVFunction = new RISCVFunction(module, s);
            functionMap.put(function, RISCVFunction);
            for (IRBasicBlock block : function.getBlockContain()) {
                RISCVBasicBlock RISCVBasicBlock = new RISCVBasicBlock("." + function.getIdentifier() + "_" + block.getIdentifier());
                blockMap.put(block, RISCVBasicBlock);
                RISCVFunction.addBlock(RISCVBasicBlock);
            }
            RISCVFunction.setEntry(blockMap.get(function.getEntry()));
            RISCVFunction.setExit(blockMap.get(function.getExit()));
            if(function.getClassPtr() != null){ RISCVFunction.getParameters().add(getRegister(function.getClassPtr())); }
            function.getParameters().forEach(param -> RISCVFunction.getParameters().add(getRegister(param)));
            module.addExternalFunctionSet(RISCVFunction);
        });
        Module.getExternalFunctionMap().forEach(((s, function) -> visit(function)));
    }

    @Override
    public void visit(IRBasicBlock block) {
        if(currentBlock != blockMap.get(block.getIdom())) ConstMap.clear();
        currentBlock = blockMap.get(block);
        block.getPrev().forEach(prev -> currentBlock.addPrev(blockMap.get(prev)));
        block.getNext().forEach(next -> currentBlock.addNext(blockMap.get(next)));
        for(IRInstruction inst = block.getHead(); inst != null && !(currentBlock.Terminate()); inst = inst.getNext()) inst.accept(this);
    }

    @Override
    public void visit(Store inst) {
        RISCVRegister addr = getRegister(inst.getPointer());
        if (addr instanceof RISCVGlobalRegister) {
            RISCVRegister reg = getRegister(inst.getValue());
            RISCVVirtualRegister ptr = new RISCVVirtualRegister(4);
            currentBlock.addInst(new RISCVLui(new RISCVRelocation((RISCVGlobalRegister) addr, RISCVRelocation.RelocationType.hi), ptr, currentBlock));
            currentBlock.addInst(new RISCVStore(ptr, new RISCVRelocation((RISCVGlobalRegister) addr, RISCVRelocation.RelocationType.lo), reg, inst.getValue().getOperandType().getSize() / 8, currentBlock));
        }
        else currentBlock.addInst(new RISCVStore(addr, new RISCVImmediate(0), getRegister(inst.getValue()), inst.getValue().getOperandType().getSize() / 8, currentBlock));
    }

}

