import AST.RootNode;
import Optimization.ASM.ASMOptimize;
import Optimization.AST.ConstantFolding;
import Optimization.AST.SimpleLoopCSE;
import Optimization.IR.*;
import backend.IR_ASM.ASMPrinter;
import RISCV.RISCVmodule.RISCVModule;
import backend.AST_IR.IRBuilder;
import backend.AST_IR.IRPrinter;
import backend.AST_IR.Memory_Register;
import backend.AST_IR.PhiResolution;
import backend.IR_ASM.ASMBuilder;
import backend.IR_ASM.RegisterAllocation;
import frontend.ASTbuilder;
import frontend.SemanticChecker;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.MymxErrorListener;
import parser.MymxLexer;
import parser.MymxParser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean codegen = true;
        boolean optimize = false;
        if(args.length > 0){
            for (String arg : args){
                switch (arg){
                    case "-semantic" -> codegen = false;
                    case "-codegen" -> codegen = true;
                    case "-optimize" -> optimize = true;
                }
            }
        }
        String fileName = "./testcase/code.mx";
        try {
            InputStream file = System.in;
   //        InputStream file = new FileInputStream(fileName);
            RootNode ast = buildAST(file);
            new SemanticChecker().visit(ast);
            //new ASTOptimize(ast).run();
            new ConstantFolding().visit(ast);
            if(optimize) new SimpleLoopCSE().visit(ast);
            if(!codegen) return;
            IRBuilder irBuilder = new IRBuilder(optimize);
            irBuilder.visit(ast);
    //        PrintStream IRFile = new PrintStream( "out.ll");
    //        new IRPrinter(IRFile).run(irBuilder.getModule());
      //      new Inline(irBuilder.getModule()).run();
     //       IRFile = new PrintStream( "out1.ll");
      //      new IRPrinter(IRFile).run(irBuilder.getModule());
      //      new Inline(irBuilder.getModule()).run();
      //      new GlobalToLocal(irBuilder.getModule()).run();
            new Memory_Register().run(irBuilder.getModule());
      //      IRFile = new PrintStream( "out3.ll");
      //      new IRPrinter(IRFile).run(irBuilder.getModule());
            new Inline(irBuilder.getModule(), true).run();
            new InstCombination().visit(irBuilder.getModule());
            boolean modified = false;
            int i = 0;
            do{
                modified = false;
                Inline Opt1 = new Inline(irBuilder.getModule(), false);
                Opt1.run();
                modified |= Opt1.Flag();
                InstCombination Opt2 = new InstCombination();
                Opt2.visit(irBuilder.getModule());
                modified |= Opt2.NewInstCombination();
 //               IRFile = new PrintStream( "output" + i++ + ".ll");
///                new IRPrinter(IRFile).run(irBuilder.getModule());
            }while (modified);
          //  new Inline(irBuilder.getModule()).run();
          //  new InstCombination().visit(irBuilder.getModule());
          //  new Inline(irBuilder.getModule()).run();
          //  new InstCombination().visit(irBuilder.getModule());
          //  new RemoveLoadStore(irBuilder.getModule()).run();
      //      IRFile = new PrintStream( "out2.ll");
      //      new IRPrinter(IRFile).run(irBuilder.getModule());
            new DCE().visit(irBuilder.getModule());
            new ADCE(irBuilder.getModule()).run();
            new CSE().visit(irBuilder.getModule());
      //      IRFile = new PrintStream( "out4.ll");
      //      new IRPrinter(IRFile).run(irBuilder.getModule());
      //      new GlobalToLocal(irBuilder.getModule()).run();
            new PhiResolution().run(irBuilder.getModule());
            ASMBuilder asmBuilder = new ASMBuilder();
            asmBuilder.visit(irBuilder.getModule());
            RISCVModule riscvModule = asmBuilder.getModule();
       //     PrintStream ASMFile1 = new PrintStream( "output1.s");
      //      new ASMPrinter(riscvModule, new PrintStream(ASMFile1), false).run();
         //   RISCVModule riscvModule = (new ASMBuilder(irBuilder.getModule())).run();
            new RegisterAllocation(riscvModule).run();
            new ASMOptimize(riscvModule).run();
            PrintStream ASMFile = new PrintStream( "output.s");
            new ASMPrinter(riscvModule, new PrintStream(ASMFile), true).run();
        } catch (Exception err) {
            err.printStackTrace();
            System.err.println(err.getMessage());
            throw new RuntimeException();
        }
        System.err.println("[Compile Finished]");
    }

    public static RootNode buildAST(InputStream file) throws Exception {
        MymxLexer lexer = new MymxLexer(CharStreams.fromStream(file));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new MymxErrorListener());
        MymxParser parser = new MymxParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new MymxErrorListener());
        return (RootNode) (new ASTbuilder().visit(parser.complication_code()));
    }
}
