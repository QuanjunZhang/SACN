package org.nju.cabs.test;

import org.nju.cabs.cppInterpreter.CPP14Lexer;
import org.nju.cabs.cppInterpreter.CPP14Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestCppInterpreter {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\cpp\\org.nju.cabs.test.cpp");
        CPP14Lexer cppLexer=new CPP14Lexer(CharStreams.fromStream(inputStream));
        CPP14Parser cppParser=new CPP14Parser(new CommonTokenStream(cppLexer));
        System.out.println(cppParser.translationUnit().toStringTree(cppParser));
    }
}
