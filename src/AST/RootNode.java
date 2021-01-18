package AST;

import Util.position;
import java.util.ArrayList;
import java.util.List;

public class RootNode extends ASTNode{
    private ArrayList<ASTNode> definition;
    public RootNode(position pos){
        super(pos);
        definition = new ArrayList<>();
    }
    public RootNode(ArrayList<ASTNode> definition, position pos){
       super(pos);
       this.definition = definition;
    }

    public void AddNode(ASTNode definition){
        this.definition.add(definition);
    }

    public void AddNode(List<VardefNode> definition){
        this.definition.addAll(definition);
    }

    public ArrayList<ASTNode> getDefinition() {
        return definition;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
