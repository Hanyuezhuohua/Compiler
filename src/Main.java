import AST.RootNode;
import frontend.ASTbuilder;
import frontend.ScopeBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.MymxErrorListener;
import parser.MymxLexer;
import parser.MymxParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
//        String fileName = "./testcase/sema/symbol-package/symbol-1.mx";
        try {
            InputStream file = System.in;
//            InputStream file = new FileInputStream(fileName);
            RootNode ast = buildAST(file);
            new ScopeBuilder().visit(ast);
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
