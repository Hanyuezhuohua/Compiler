package Optimization.IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;
import IR.IRoperand.*;
import IR.IRtype.IRIntType;
import IR.IRutility.FuncBlockCollection;
import IR.IRutility.IRVisitor;
import IR.IRutility.UseClear;
import IR.IRutility.UseCollection;
import backend.AST_IR.DominatorTree;

public class SCCP implements IRVisitor {
    private boolean newSCCP;

    public SCCP(){
        newSCCP = false;
    }

    @Override
    public void visit(IRModule module) {
        module.getExternalFunctionMap().forEach((id, func) -> func.accept(this));
        module.getExternalFunctionMap().forEach((id, func) -> {
            new FuncBlockCollection().BlockCollecting(func);
            new DominatorTree(func).Lengauer_Tarjan();
            new UseClear().visit(func);
            new UseCollection().visit(func);
        });
    }

    @Override
    public void visit(IRFunction func) {
        do{
            newSCCP = false;
            new FuncBlockCollection().BlockCollecting(func);
            new UseClear().visit(func);
            new UseCollection().visit(func);
            func.getBlockContain().forEach(block -> block.accept(this));
        } while (newSCCP);
    }

    @Override
    public void visit(IRBasicBlock block) {
        for (IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
            inst.accept(this);
        }
    }

    @Override
    public void visit(Alloca inst) {

    }

    @Override
    public void visit(Binary inst) {
        if(isConst(inst.getOp1()) && isConst(inst.getOp2())){
            IROperand result;
            if(inst.getOp() == Binary.IRBinaryOpType.add){
                result = new IRConstInt(IntConst(inst.getOp1()) + IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.sub){
                result = new IRConstInt(IntConst(inst.getOp1()) - IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.mul){
                result = new IRConstInt(IntConst(inst.getOp1()) * IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.sdiv){
                if(inst.getOp2().isZero()) result = new IRConstInt(0, IRIntType.IntTypeBytes.Int32);
                else result = new IRConstInt(IntConst(inst.getOp1()) / IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.srem){
                if(inst.getOp2().isZero()) result = new IRConstInt(0, IRIntType.IntTypeBytes.Int32);
                result = new IRConstInt(IntConst(inst.getOp1()) % IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
        }
        else if(isConst(inst.getOp1())){
            if(inst.getOp() == Binary.IRBinaryOpType.add && inst.getOp1().isZero()){
                ((IRLocalRegister) inst.getResult()).update(inst.getOp2());
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.mul && inst.getOp1().isZero()){
                ((IRLocalRegister) inst.getResult()).update(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.mul && inst.getOp1() instanceof IRConstInt && ((IRConstInt) inst.getOp1()).getValue() == 1){
                ((IRLocalRegister) inst.getResult()).update(inst.getOp2());
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.sdiv && inst.getOp1().isZero()){
                ((IRLocalRegister) inst.getResult()).update(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.srem && inst.getOp1().isZero()){
                ((IRLocalRegister) inst.getResult()).update(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                newSCCP = true;
            }
        }
        else if(isConst(inst.getOp2())){
            if(inst.getOp() == Binary.IRBinaryOpType.add && inst.getOp2().isZero()){
                ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.sub && inst.getOp2().isZero()){
                ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.mul && inst.getOp2() instanceof IRConstInt && ((IRConstInt) inst.getOp2()).getValue() == 1){
                ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                newSCCP = true;
            }
            else if(inst.getOp() == Binary.IRBinaryOpType.sdiv && inst.getOp2() instanceof IRConstInt && ((IRConstInt) inst.getOp2()).getValue() == 1){
                ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                newSCCP = true;
            }
        }
    }

    @Override
    public void visit(BitCast inst) {

    }

    @Override
    public void visit(BitwiseBinary inst) {
        if(isConst(inst.getOp1()) && isConst(inst.getOp2())){
            IROperand result;
            if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.and){
                if(inst.getOp1() instanceof IRConstBool && inst.getOp2() instanceof IRConstBool){
                    result = new IRConstBool(BoolConst(inst.getOp1()) & BoolConst(inst.getOp2()));
                }
                else result = new IRConstInt(IntConst(inst.getOp1()) & IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.or){
                if(inst.getOp1() instanceof IRConstBool && inst.getOp2() instanceof IRConstBool){
                    result = new IRConstBool(BoolConst(inst.getOp1()) | BoolConst(inst.getOp2()));
                }
                else result = new IRConstInt(IntConst(inst.getOp1()) | IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.xor){
                if(inst.getOp1() instanceof IRConstBool && inst.getOp2() instanceof IRConstBool){
                    result = new IRConstBool(BoolConst(inst.getOp1()) ^ BoolConst(inst.getOp2()));
                }
                else result = new IRConstInt(IntConst(inst.getOp1()) ^ IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.shl){
                result = new IRConstInt(IntConst(inst.getOp1()) << IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.ashr){
                result = new IRConstInt(IntConst(inst.getOp1()) >> IntConst(inst.getOp2()), IRIntType.IntTypeBytes.Int32);
                ((IRLocalRegister) inst.getResult()).update(result);
            }
            newSCCP = true;
        }
        else if(isConst(inst.getOp1())){
            if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.and){
                if(inst.getOp1() instanceof IRConstBool && inst.getOp1().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstBool(false));
                    newSCCP = true;
                }
                else if(inst.getOp1() instanceof IRConstInt && inst.getOp1().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                    newSCCP = true;
                }
                else if(inst.getOp1() instanceof IRConstBool && ((IRConstBool) inst.getOp1()).getValue()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp2());
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.or){
                if(inst.getOp1() instanceof IRConstBool && ((IRConstBool) inst.getOp1()).getValue()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstBool(true));
                    newSCCP = true;
                }
                else if(inst.getOp1().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp2());
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.xor){
                if(inst.getOp1() instanceof IRConstBool && inst.getOp1().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp2());
                    newSCCP = true;
                }
                else if(inst.getOp1() instanceof IRConstInt && inst.getOp1().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp2());
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.shl){
                if(inst.getOp1().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.ashr){
                if(inst.getOp1().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                    newSCCP = true;
                }
            }
        }
        else if(isConst(inst.getOp2())){
            if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.and){
                if(inst.getOp2() instanceof IRConstBool && inst.getOp2().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstBool(false));
                    newSCCP = true;
                }
                else if(inst.getOp2() instanceof IRConstInt && inst.getOp2().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstInt(0, IRIntType.IntTypeBytes.Int32));
                    newSCCP = true;
                }
                else if(inst.getOp2() instanceof IRConstBool && ((IRConstBool) inst.getOp2()).getValue()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.or){
                if(inst.getOp2() instanceof IRConstBool && ((IRConstBool) inst.getOp2()).getValue()){
                    ((IRLocalRegister) inst.getResult()).update(new IRConstBool(true));
                    newSCCP = true;
                }
                else if(inst.getOp2().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.xor){
                if(inst.getOp2() instanceof IRConstBool && inst.getOp2().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                    newSCCP = true;
                }
                else if(inst.getOp2() instanceof IRConstInt && inst.getOp2().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.shl){
                if(inst.getOp2().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == BitwiseBinary.IRBitwiseBinaryOpType.ashr){
                if(inst.getOp2().isZero()){
                    ((IRLocalRegister) inst.getResult()).update(inst.getOp1());
                    newSCCP = true;
                }
            }
        }
    }

    @Override
    public void visit(Br inst) {
        if(isConst(inst.getCond())){
            if(((IRConstBool)inst.getCond()).getValue()){
                inst.Remove();
                inst.getInstIn().addInst(new Br(inst.getInstIn(), null, inst.getIfTrue(), null));
                newSCCP = true;
            }
            else if(!((IRConstBool)inst.getCond()).getValue()){
                inst.Remove();
                inst.getInstIn().addInst(new Br(inst.getInstIn(), null, inst.getIfFalse(), null));
                newSCCP = true;
            }
        }
    }

    @Override
    public void visit(Call inst) {

    }

    @Override
    public void visit(GetElementPtr inst) {

    }

    @Override
    public void visit(Icmp inst) {
        if(isConst(inst.getOp1()) && isConst(inst.getOp2())){
            IROperand result;
            if(inst.getOp() == Icmp.IRIcmpOpType.slt){
                result = new IRConstBool(IntConst(inst.getOp1()) < IntConst(inst.getOp2()));
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Icmp.IRIcmpOpType.sgt){
                result = new IRConstBool(IntConst(inst.getOp1()) > IntConst(inst.getOp2()));
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Icmp.IRIcmpOpType.sle){
                result = new IRConstBool(IntConst(inst.getOp1()) <= IntConst(inst.getOp2()));
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Icmp.IRIcmpOpType.sge){
                result = new IRConstBool(IntConst(inst.getOp1()) >= IntConst(inst.getOp2()));
                ((IRLocalRegister) inst.getResult()).update(result);
                newSCCP = true;
            }
            else if(inst.getOp() == Icmp.IRIcmpOpType.eq){
                if(inst.getOp1() instanceof IRConstInt && inst.getOp2() instanceof  IRConstInt){
                    result = new IRConstBool(IntConst(inst.getOp1()) == IntConst(inst.getOp2()));
                    ((IRLocalRegister) inst.getResult()).update(result);
                    newSCCP = true;
                }
                else if(inst.getOp1() instanceof IRConstBool && inst.getOp2() instanceof  IRConstBool){
                    result = new IRConstBool(BoolConst(inst.getOp1()) == BoolConst(inst.getOp2()));
                    ((IRLocalRegister) inst.getResult()).update(result);
                    newSCCP = true;
                }
                else{
                    ((IRLocalRegister) inst.getResult()).update(new IRConstBool(true));
                    newSCCP = true;
                }
            }
            else if(inst.getOp() == Icmp.IRIcmpOpType.ne){
                if(inst.getOp1() instanceof IRConstInt && inst.getOp2() instanceof  IRConstInt){
                    result = new IRConstBool(IntConst(inst.getOp1()) != IntConst(inst.getOp2()));
                    ((IRLocalRegister) inst.getResult()).update(result);
                    newSCCP = true;
                }
                else if(inst.getOp1() instanceof IRConstBool && inst.getOp2() instanceof  IRConstBool){
                    result = new IRConstBool(BoolConst(inst.getOp1()) != BoolConst(inst.getOp2()));
                    ((IRLocalRegister) inst.getResult()).update(result);
                    newSCCP = true;
                }
                else{
                    ((IRLocalRegister) inst.getResult()).update(new IRConstBool(false));
                    newSCCP = true;
                }
            }
        }
    }

    @Override
    public void visit(Load inst) {

    }

    @Override
    public void visit(Move inst) {

    }

    @Override
    public void visit(Phi inst) {
        if(isConst(inst.getValues().get(0))){
            if(inst.getValues().get(0) instanceof IRConstInt){
                boolean flag = true;
                for(int i = 1; i < inst.getValues().size(); ++i){
                    if(!(inst.getValues().get(i) instanceof IRConstInt && ((IRConstInt) inst.getValues().get(i)).getValue() == ((IRConstInt) inst.getValues().get(0)).getValue())){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    ((IRLocalRegister) inst.getResult()).update(inst.getValues().get(0));
                    newSCCP = true;
                }
            }
            else if(inst.getValues().get(0) instanceof IRConstBool){
                boolean flag = true;
                for(int i = 1; i < inst.getValues().size(); ++i){
                    if(!(inst.getValues().get(i) instanceof IRConstBool && ((IRConstBool) inst.getValues().get(i)).getValue() == ((IRConstBool) inst.getValues().get(0)).getValue())){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    ((IRLocalRegister) inst.getResult()).update(inst.getValues().get(0));
                    newSCCP = true;
                }
            }
        }
    }

    @Override
    public void visit(Ret inst) {

    }

    @Override
    public void visit(Store inst) {

    }

    @Override
    public void visit(Trunc inst) {
        if(isConst(inst.getValue())){
            assert inst.getValue() instanceof IRConstInt;
            ((IRLocalRegister) inst.getResult()).update(new IRConstBool(((IRConstInt) inst.getValue()).getValue() == 1 ? true : false));
            newSCCP = true;
        }
    }

    @Override
    public void visit(Zext inst) {

    }

    public boolean isConst(IROperand operand){
        return operand instanceof IRConstInt || operand instanceof IRConstBool || operand instanceof IRConstNull;
    }

    public int IntConst(IROperand operand){
        if(operand instanceof IRConstInt) return ((IRConstInt) operand).getValue();
        else return 0;
    }

    public boolean BoolConst(IROperand operand){
        if(operand instanceof IRConstBool) return ((IRConstBool) operand).getValue();
        else return false;
    }
}
