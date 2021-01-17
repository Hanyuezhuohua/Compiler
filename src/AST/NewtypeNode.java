package AST;

import Util.position;
import java.util.ArrayList;

public class NewtypeNode extends ASTNode{
    public TypeNode basetype;
    public ArrayList<ExprNode> known;
    public NewtypeNode(position pos){
        super(pos);
        basetype = new TypeNode(pos);
        known = new ArrayList<>();
    }
    public NewtypeNode(TypeNode type, position pos){
        super(pos);
        basetype = type;
        known = new ArrayList<>();
    }

    public NewtypeNode(TypeNode type, ArrayList<ExprNode> known, position pos){
        super(pos);
        basetype = type;
        this.known = known;
    }

    public void setBasetype(TypeNode type){
        basetype = type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
