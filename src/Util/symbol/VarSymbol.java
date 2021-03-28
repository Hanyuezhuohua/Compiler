package Util.symbol;

import AST.VardefNode;
import IR.IRoperand.IRConstInt;
import IR.IRoperand.IROperand;
import IR.IRtype.IRIntType;
import Util.scope.Scope;
import Util.type.Type;
import Util.position;

public class VarSymbol extends Symbol{
    private IROperand operand;
    private IRConstInt index;
    private boolean member = false;
    public VarSymbol(position pos, String identifier, Type type, VardefNode definition){
        super(pos, identifier, type, definition);
    }
    public VarSymbol(position pos, String identifier, Type type){
        super(pos, identifier, type);
    }
    public VarSymbol(position pos, String identifier, Type type, Scope scope){
        super(pos, identifier, type, scope);
    }

    public void setOperand(IROperand operand) {
        this.operand = operand;
    }

    public void setIndex(int index) {
        this.index = new IRConstInt(index, IRIntType.IntTypeBytes.Int32);
    }

    public IROperand getOperand() {
        return operand;
    }

    public IRConstInt getIndex() {
        return index;
    }

    public void setMember(boolean member) {
        this.member = true;
    }

    public boolean isMember() {
        return member;
    }
}
