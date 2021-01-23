package IR.IRinstruction;

import IR.IRbasicblock.IRBasicBlock;

public abstract class IRInstruction {
    private IRBasicBlock InstIn;
    private IRInstruction prev;
    private IRInstruction next;
}
