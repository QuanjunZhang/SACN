package test;

import cppInterpreter.CPP14Lexer;
import cppInterpreter.CPP14Parser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class TestCppInterpreter {
    public static void main(String[] args) throws IOException {
        CPP14Lexer cppLexer=new CPP14Lexer(new ANTLRFileStream("C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\cpp\\test.cpp"));
        CPP14Parser cppParser=new CPP14Parser(new CommonTokenStream(cppLexer));
        System.out.println(cppParser.translationUnit().toStringTree(cppParser));

    }
}
