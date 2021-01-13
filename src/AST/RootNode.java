package AST;

import Util.position;
import java.util.ArrayList;

public class RootNode extends ASTNode{
    public ArrayList<ASTNode> defination;
    public RootNode(position pos){
        super(pos);
        defination = new ArrayList<>();
    }
    public RootNode(ArrayList<ASTNode> defination, position pos){
       super(pos);
       this.defination = defination;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
