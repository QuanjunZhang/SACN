package vocabulary;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Vocabulary {
    public static final String METHOD_PREFIX="Method_";
    public static final String TYPE_PREFIX="Type_";
    public static final String STRUCT_OR_UNION_PREFIX="STRUCT_OR_UNION_";
    public static final String VAR_PREFIX="VAR_";
    public static List<String> methods = new ArrayList<>();
    public static List<String> types = new ArrayList<>();
    public static List<String> structOrUnions = new ArrayList<>();
    public static List<String> variants = new ArrayList<>();
    public static final Set<String> retainWords= Set.of("auto","break","case","char","const","continue","default","do","double","else","enum","extern","float","\n" +
            "for","goto","if","int","long","register","return","short","signed","sizeof","static","struct",
            "switch","typedef","union", "unsigned","void","volatile","while");


    public static boolean isCustomVariant(TerminalNode node){
        String text=node.getText();
        boolean isLegalName = Pattern.matches("[a-zA-Z_]+[a-zA-Z0-9]*", text);
        return isLegalName&&
                !methods.contains(text)&&
                !types.contains(text)&&
                !structOrUnions.contains(text)&&
                !retainWords.contains(text);
    }
}
