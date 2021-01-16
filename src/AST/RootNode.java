package AST;

import Util.position;
import java.util.ArrayList;

public class RootNode extends ASTNode{
    public ArrayList<ASTNode> definition;
    public RootNode(position pos){
        super(pos);
        definition = new ArrayList<>();
    }
    public RootNode(ArrayList<ASTNode> definition, position pos){
       super(pos);
       this.definition = definition;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
