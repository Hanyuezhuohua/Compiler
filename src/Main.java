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
        boolean optimize = true;
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
            PrintStream IRFile = new PrintStream( "out.ll");
            new IRPrinter(IRFile).run(irBuilder.getModule());
      //      new Inline(irBuilder.getModule()).run();
            IRFile = new PrintStream( "out1.ll");
            new IRPrinter(IRFile).run(irBuilder.getModule());
      //      new Inline(irBuilder.getModule()).run();
      //      new GlobalToLocal(irBuilder.getModule()).run();
            new Memory_Register().run(irBuilder.getModule());

            new Inline(irBuilder.getModule(), true).run();
            new InstCombination().visit(irBuilder.getModule());
            IRFile = new PrintStream( "out5.ll");
            new IRPrinter(IRFile).run(irBuilder.getModule());
            boolean modified = false;
            int t = 0;
            do{
                modified = false;
                Inline Opt1 = new Inline(irBuilder.getModule(), false);
                Opt1.run();
                modified |= Opt1.Flag();
                InstCombination Opt2 = new InstCombination();
                Opt2.visit(irBuilder.getModule());
                modified |= Opt2.NewInstCombination();
                SCCP Opt6 = new SCCP();
                Opt6.visit(irBuilder.getModule());
                modified |= Opt6.NewSCCP();
                DCE Opt3 = new DCE();
                Opt3.visit(irBuilder.getModule());
                modified |= Opt3.Flag();
                ADCE Opt4 = new ADCE(irBuilder.getModule());
                Opt4.run();
                modified |= Opt4.Flag();
                CSE Opt5 = new CSE();
                Opt5.visit(irBuilder.getModule());
                modified |= Opt5.Flag();
        //        IRFile = new PrintStream( t + "out1.ll");
        //        new IRPrinter(IRFile).run(irBuilder.getModule());
                CFGSimplification Opt7 = new CFGSimplification();
                Opt7.visit(irBuilder.getModule());
        //        IRFile = new PrintStream( t++ + "out2.ll");
        //        new IRPrinter(IRFile).run(irBuilder.getModule());
                modified |= Opt7.Flag();
                LICM Opt8 = new LICM();
       //         Opt8.run(irBuilder.getModule());
                modified |= Opt8.NewLICM();
                new MemoryAccess(irBuilder.getModule()).run();
            }while (modified);
            IRFile = new PrintStream( "out9.ll");
            new IRPrinter(IRFile).run(irBuilder.getModule());
       //     new MemoryAccess(irBuilder.getModule()).run();;
      /*      for(int i = 0; i < 10; ++i){
                modified = false;
                CSE Opt5 = new CSE();
                Opt5.visit(irBuilder.getModule());
                modified |= Opt5.Flag();
                LICM Opt8 = new LICM();
                Opt8.run(irBuilder.getModule());
                modified |= Opt8.NewLICM();
            }*/
          //  CFGSimplification Opt7 = new CFGSimplification();
         //   Opt7.visit(irBuilder.getModule());
         //   new LICM().run(irBuilder.getModule());
         //   CSE Opt5 = new CSE();
         //   Opt5.visit(irBuilder.getModule());
            IRFile = new PrintStream( "out4.ll");
            new IRPrinter(IRFile).run(irBuilder.getModule());
      //      new GlobalToLocal(irBuilder.getModule()).run();
            new TailCall(irBuilder.getModule()).run();
            new PhiResolution().run(irBuilder.getModule());
      //      new MemoryAccess(irBuilder.getModule()).run();;
            IRFile = new PrintStream( "out7.ll");
            new IRPrinter(IRFile).run(irBuilder.getModule());
            ASMBuilder asmBuilder = new ASMBuilder();
            asmBuilder.visit(irBuilder.getModule());
            RISCVModule riscvModule = asmBuilder.getModule();
            PrintStream ASMFile1 = new PrintStream( "output1.s");
            new ASMPrinter(riscvModule, new PrintStream(ASMFile1), false).run();
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
