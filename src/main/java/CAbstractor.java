import cInterpreter.CLexer;
import cInterpreter.CParser;
import listener.AbsDetector;
import listener.AbsModifier;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import utils.AntlrUtils;
import vocabulary.Vocabulary;

import java.io.*;

public class CAbstractor {
    public static void abs(String inputPath,String outputCodePath,String outputMapPath) throws IOException {
        InputStream inputStream = new FileInputStream(inputPath);
        CLexer cLexer=new CLexer(CharStreams.fromStream(inputStream));
        CParser cParser=new CParser(new CommonTokenStream(cLexer));
        AbsDetector listener=new AbsDetector();
        cParser.addParseListener(listener);
        System.out.println("Before abs,ast looks like: "+cParser.compilationUnit().toStringTree(cParser));
        cParser.reset();
        cParser.removeParseListener(listener);
        AbsModifier listener2=new AbsModifier();
        cParser.addParseListener(listener2);
        CParser.CompilationUnitContext context=cParser.compilationUnit();
        String absCode=AntlrUtils.toCode(context.children.get(0));
        System.out.println("After abs,code looks like:"+absCode);

        //输出代码
        if(!outputCodePath.endsWith(".c")){
            outputCodePath+=".c";
        }
        File f=new File(outputCodePath);
        FileWriter writer=new FileWriter(f);
        writer.write(absCode);
        writer.flush();
        writer.close();

        //输出map
        if(!outputMapPath.endsWith(".map")){
            outputMapPath+=".map";
        }
        File ff=new File(outputMapPath);
        writer=new FileWriter(ff);
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
        if(args.length<3){
            System.err.println("not enough params");
            return;
        }
        abs(args[0],args[1],args[2]);
//        abs("C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\test3.c","C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\output3.c","C:\\Users\\tom\\Desktop\\cabs\\src\\main\\resources\\code\\c\\output3.map");
    }




}
