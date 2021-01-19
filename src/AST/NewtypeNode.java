package AST;

import Util.position;
import java.util.ArrayList;

public class NewtypeNode extends ASTNode{
    private TypeNode basetype;
    private ArrayList<ExprNode> known;
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

    public ArrayList<ExprNode> getKnown() {
        return known;
    }

    public TypeNode getBasetype() {
        return basetype;
    }

    public void setBasetype(TypeNode type){
        basetype = type;
    }

    public void add_known(ExprNode expression){
        known.add(expression);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
