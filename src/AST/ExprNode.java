package AST;

import IR.IRoperand.IROperand;
import Util.error.ErrorMessage;
import Util.position;
import Util.type.Type;

public abstract class ExprNode extends ASTNode{
    public enum ExprType {
        LVALUE, RVALUE, CLASS, FUNCTION
    }
    private Type type;
    private ExprType exprType;

    private IROperand result;
    private IROperand basicResult;

    public ExprNode(position pos){
        super(pos);
        type = null;
        exprType = null;
    }

    public Type getType() {
        return type;
    }

    public ExprType getExprType() {
        return exprType;
    }

    public void isValue(){
        if(exprType != ExprType.LVALUE && exprType != ExprType.RVALUE){
            throw new ErrorMessage("ExprNode isValue ERROR", getPos());
        }
        else return;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setExprType(ExprType exprType) {
        this.exprType = exprType;
    }

    public IROperand getResult() {
        return result;
    }

    public void setResult(IROperand result) {
        this.result = result;
    }

    public IROperand getBasicResult() {
        return basicResult;
    }

    public void setBasicResult(IROperand basicResult) {
        this.basicResult = basicResult;
    }

    public boolean isConst() { return false; }
}
