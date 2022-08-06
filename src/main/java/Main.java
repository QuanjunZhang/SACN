import cInterpreter.CLexer;
import cInterpreter.CParser;
import listener.MethodDeclarationListener;
import listener.MethodNameModifier;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\add.c");
        CLexer cLexer=new CLexer(CharStreams.fromStream(inputStream));
        CParser cParser=new CParser(new CommonTokenStream(cLexer));
        MethodDeclarationListener listener=new MethodDeclarationListener();
        cParser.addParseListener(listener);
        System.out.println(cParser.compilationUnit().toStringTree(cParser));
        cParser.reset();
        cParser.removeParseListener(listener);
        MethodNameModifier listener2=new MethodNameModifier();
        cParser.addParseListener(listener2);
        CParser.CompilationUnitContext context=cParser.compilationUnit();
        System.out.println(toCode(context.children.get(0)));
//        CParser.CompilationUnitContext context=cParser.compilationUnit();

//        ParseTreeWalker walker = new ParseTreeWalker();
//        walker.walk(listener,context);
    }

    public static String toCode(ParseTree parseTree){
        if(parseTree.getChildCount()==0){return parseTree.getText();}
        StringBuilder t= new StringBuilder();
        for(int i=0;i<parseTree.getChildCount();i++){
            String tt=toCode(parseTree.getChild(i));
            if(tt.strip().length()>0){
                if(t.length()>0) t.append(" ");
                t.append(tt.strip());
            }
        }
        return t.toString();
    }
}
