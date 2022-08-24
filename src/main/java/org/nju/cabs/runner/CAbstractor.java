package org.nju.cabs.runner;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.nju.cabs.cInterpreter.CLexer;
import org.nju.cabs.cInterpreter.CParser;
import org.nju.cabs.listener.AbsDetector;
import org.nju.cabs.listener.AbsModifier;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.nju.cabs.utils.AntlrUtils;
import org.nju.cabs.vocabulary.Vocabulary;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CAbstractor {
    public static void abs(String inputPath1,String inputPath2,String outputCodePath1,String outputCodePath2,String outputMapPath) throws IOException {
        InputStream inputStream1 = Files.newInputStream(Paths.get(inputPath1));
        CLexer cLexer1=new CLexer(CharStreams.fromStream(inputStream1));
        CParser cParser1=new CParser(new CommonTokenStream(cLexer1));
        AbsDetector listener=new AbsDetector();
        cParser1.addParseListener(listener);
        System.out.println("Before abs,code1 ast looks like: "+cParser1.compilationUnit().toStringTree(cParser1));
        cParser1.reset();

        InputStream inputStream2 = Files.newInputStream(Paths.get(inputPath2));
        CLexer cLexer2=new CLexer(CharStreams.fromStream(inputStream2));
        CParser cParser2=new CParser(new CommonTokenStream(cLexer2));
        cParser2.addParseListener(listener);
        System.out.println("Before abs,code2 ast looks like: "+cParser2.compilationUnit().toStringTree(cParser2));
        cParser2.reset();

        cParser1.removeParseListener(listener);
        cParser2.removeParseListener(listener);

        AbsModifier listener2=new AbsModifier();
        cParser1.addParseListener(listener2);
        CParser.CompilationUnitContext context1=cParser1.compilationUnit();
        cParser2.addParseListener(listener2);
        CParser.CompilationUnitContext context2=cParser2.compilationUnit();

        StringBuilder absCode1= new StringBuilder();
        for(ParseTree parseTree:context1.children){
            if(AntlrUtils.toCode(parseTree).contains("EOF"))continue;

            if(!(parseTree instanceof ErrorNode)){
                absCode1.append(AntlrUtils.toCode(parseTree));
                continue;
            }
            absCode1
                    .append(absCode1.length()==0?"":" ")
                    .append(AntlrUtils.toErrorCode(AntlrUtils.toCode(parseTree)));
        }
        System.out.println("After abs,code1 looks like: "+ absCode1);

        StringBuilder absCode2= new StringBuilder();
        for(ParseTree parseTree:context2.children){
            if(AntlrUtils.toCode(parseTree).contains("EOF"))continue;

            if(!(parseTree instanceof ErrorNode)){
                absCode2.append(AntlrUtils.toCode(parseTree));
                continue;
            }
            absCode2
                    .append(absCode2.length()==0?"":" ")
                    .append(AntlrUtils.toErrorCode(AntlrUtils.toCode(parseTree)));
        }
        System.out.println("After abs,code2 looks like: "+ absCode2);

        //输出代码1
        if(!outputCodePath1.endsWith(".c")){
            outputCodePath1+=".c";
        }
        File f=new File(outputCodePath1);
        FileWriter writer=new FileWriter(f);
        writer.write(absCode1.toString());
        writer.flush();
        writer.close();

        //输出代码2
        if(!outputCodePath2.endsWith(".c")){
            outputCodePath2+=".c";
        }
        File ff=new File(outputCodePath2);
        writer=new FileWriter(ff);
        writer.write(absCode2.toString());
        writer.flush();
        writer.close();

        //输出map
        if(!outputMapPath.endsWith(".map")){
            outputMapPath+=".map";
        }
        File fff=new File(outputMapPath);
        writer=new FileWriter(fff);
        writer.write("Method:");
        writer.write("\n");
        for(int i=0;i< Vocabulary.methods.size();i++){
            writer.write(Vocabulary.methods.get(i) +" ");
        }
        writer.write("\n");
        for(int i=0;i< Vocabulary.methods.size();i++){
            writer.write(Vocabulary.METHOD_PREFIX+i +" ");
        }
        writer.write("\n\n");
        writer.write("Types:");
        writer.write("\n");
        for(int i=0;i< Vocabulary.types.size();i++){
            writer.write(Vocabulary.types.get(i) +" ");
        }
        writer.write("\n");
        for(int i=0;i< Vocabulary.types.size();i++){
            writer.write(Vocabulary.TYPE_PREFIX+i +" ");
        }
        writer.write("\n\n");
        writer.write("Struct Or Union:");
        writer.write("\n");
        for(int i=0;i< Vocabulary.structOrUnions.size();i++){
            writer.write(Vocabulary.structOrUnions.get(i) +" ");
        }
        writer.write("\n");
        for(int i=0;i< Vocabulary.structOrUnions.size();i++){
            writer.write(Vocabulary.STRUCT_OR_UNION_PREFIX+i +" ");
        }
        writer.write("\n\n");
        writer.write("VAR:");
        writer.write("\n");
        for(int i=0;i< Vocabulary.variants.size();i++){
            writer.write(Vocabulary.variants.get(i) +" ");
        }
        writer.write("\n");
        for(int i=0;i< Vocabulary.variants.size();i++){
            writer.write(Vocabulary.VAR_PREFIX+i +" ");
        }
        writer.write("\n\n");
        writer.flush();
        writer.close();
    }


    public static void main(String[] args) throws IOException {
        if(args.length<5){
            System.err.println("not enough params");
            return;
        }
        abs(args[0],args[1],args[2],args[3],args[4]);
//        abs("C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\test3.c","C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\output3.c","C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\output3.map");
    }




}
