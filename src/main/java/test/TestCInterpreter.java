package test;

import cInterpreter.CLexer;
import cInterpreter.CParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class TestCInterpreter {
    public static void main(String[] args) throws IOException {
        CLexer cLexer=new CLexer(new ANTLRFileStream("C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\add.c"));
        CParser cParser=new CParser(new CommonTokenStream(cLexer));
        System.out.println(cParser.translationUnit().toStringTree(cParser));
    }
}
