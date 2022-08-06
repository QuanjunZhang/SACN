package org.nju.cabs.utils;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AntlrUtils {
    /**
     * parse tree 转成c代码
     */
    public static String toCode(ParseTree parseTree){
        if(parseTree.getChildCount()==0){return parseTree.getText();}
        StringBuilder t= new StringBuilder();
        for(int i=0;i<parseTree.getChildCount();i++){
            String tt=toCode(parseTree.getChild(i));
            if(tt.length()>0){
                if(t.length()>0) t.append(" ");
                t.append(tt);
            }
        }
        return t.toString();
    }

    /**
     * 是否是基本类型
     */
    public static boolean isBasicType(ParserRuleContext ctx){
        return ctx.children.stream().filter(parseTree -> parseTree instanceof TerminalNode).count()==ctx.children.size();
    }
}
