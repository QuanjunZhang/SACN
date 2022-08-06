package org.nju.cabs.test;

import org.nju.cabs.cInterpreter.CLexer;
import org.nju.cabs.cInterpreter.CParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestCInterpreter {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\add.c");
        CLexer cLexer=new CLexer(CharStreams.fromStream(inputStream));
        CParser cParser=new CParser(new CommonTokenStream(cLexer));
        System.out.println(cParser.compilationUnit().toStringTree(cParser));
    }
}
