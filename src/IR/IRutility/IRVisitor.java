package IR.IRutility;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRmodule.IRModule;

public interface IRVisitor {
    void visit(IRModule module);
    void visit(IRFunction func);
    void visit(IRBasicBlock block);
    void visit(Alloca inst);
    void visit(Binary inst);
    void visit(BitCast inst);
    void visit(BitwiseBinary inst);
    void visit(Br inst);
    void visit(Call inst);
    void visit(GetElementPtr inst);
    void visit(Icmp inst);
    void visit(Load inst);
    void visit(Move inst);
    void visit(Phi inst);
    void visit(Ret inst);
    void visit(Store inst);
    void visit(Trunc inst);
    void visit(Zext inst);
}
