import AST.RootNode;
import IR.IRutility.FuncBlockCollection;
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
            //         InputStream file = new FileInputStream(fileName);
            RootNode ast = buildAST(file);
            new SemanticChecker().visit(ast);
            new ConstantFolding().visit(ast);
            if(optimize) new SimpleLoopCSE().visit(ast);
            if(!codegen) return;
            IRBuilder irBuilder = new IRBuilder(optimize);
            irBuilder.visit(ast);
            new Memory_Register().run(irBuilder.getModule());
            new Inline(irBuilder.getModule(), true).run();
            new InstCombination().visit(irBuilder.getModule());
            boolean modified;
            boolean check = false;
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
                modified |= new Peephole(irBuilder.getModule()).run();
                CFGSimplification Opt7 = new CFGSimplification();
                Opt7.visit(irBuilder.getModule());
                modified |= Opt7.Flag();
                TailCheck Check1 = new TailCheck();
                Check1.visit(irBuilder.getModule());
                if(Check1.isCheck()){
                    check = true;
                    break;
                }
                LICM Opt8 = new LICM();
                Opt8.run(irBuilder.getModule(), true);
                modified |= Opt8.NewLICM();
                new MemoryAccess(irBuilder.getModule()).run();
                new StrengthDeduction().visit(irBuilder.getModule());
            }while (modified);
            if(check){
                irBuilder = new IRBuilder(optimize);
                irBuilder.visit(ast);
                new Memory_Register().run(irBuilder.getModule());
                new Inline(irBuilder.getModule(), true).run();
                new InstCombination().visit(irBuilder.getModule());
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
                    CFGSimplification Opt7 = new CFGSimplification();
                    Opt7.visit(irBuilder.getModule());
                    modified |= Opt7.Flag();
                    new MemoryAccess(irBuilder.getModule()).run();
                    new StrengthDeduction().visit(irBuilder.getModule());
                }while (modified);
            }
            new TailCall(irBuilder.getModule()).run();
            CSE Opt5 = new CSE();
            Opt5.visit(irBuilder.getModule());
            PrintStream test = new PrintStream("out.ll");
            new IRPrinter(test).run(irBuilder.getModule());
            new PhiResolution().run(irBuilder.getModule());
            ASMBuilder asmBuilder = new ASMBuilder();
            asmBuilder.visit(irBuilder.getModule());
            RISCVModule riscvModule = asmBuilder.getModule();
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