package AST;

import Util.position;

import java.util.ArrayList;
import java.util.List;

public class VardefListNode extends ASTNode{
    private List<VardefNode> varList;

    public VardefListNode(position pos){
        super(pos);
        varList = new ArrayList<>();
    }

    public VardefListNode(List<VardefNode> varList, position pos){
        super(pos);
        this.varList = varList;
    }

    public List<VardefNode> getVarList() {
        return varList;
    }

    public void setType(TypeNode type){
        for(VardefNode var: varList){
            var.setType(type);
        }
    }

    public void addVar(VardefNode v){
        varList.add(v);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
