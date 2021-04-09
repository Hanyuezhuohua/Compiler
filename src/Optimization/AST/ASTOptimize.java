package Optimization.AST;

import AST.RootNode;

public class ASTOptimize {
    private RootNode Root;

    public ASTOptimize(RootNode Root){
        this.Root = Root;
    }

    public void run(){
        ConstantFolding Opt1 = new ConstantFolding();
        Opt1.visit(Root);
    }
}
