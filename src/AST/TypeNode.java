package AST;

import Util.position;

public class TypeNode extends ASTNode{
    public String type;
    public int dim;
    public TypeNode(position pos){
        super(pos);
        type = null;
        dim = 0;
    }
    public TypeNode(position pos, String type, int dim){
        super(pos);
        this.type = type;
        this.dim = dim;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setDim(int dim){
        this.dim = dim;
    }

    public String getType() {
        return type;
    }

    public int getDim() {
        return dim;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
