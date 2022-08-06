package listener;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TerminalNodeProxy implements TerminalNode {
    private final TerminalNode terminalNode;
    private final String text;
    public TerminalNodeProxy(TerminalNode terminalNode, String text) {
        this.terminalNode=terminalNode;
        this.text=text;
    }
    private TerminalNodeProxy(){
        text = null;
        terminalNode = null;
    }

    @Override
    public Token getSymbol() {
        return terminalNode.getSymbol();
    }

    @Override
    public ParseTree getParent() {
        return terminalNode.getParent();
    }

    @Override
    public Object getPayload() {
        return terminalNode.getPayload();
    }

    @Override
    public ParseTree getChild(int i) {
        return terminalNode.getChild(i);
    }

    @Override
    public int getChildCount() {
        return terminalNode.getChildCount();
    }

    @Override
    public String toStringTree() {
        return text;
    }

    @Override
    public void setParent(RuleContext ruleContext) {
        terminalNode.setParent(ruleContext);

    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
        return terminalNode.accept(parseTreeVisitor);
    }

    @Override
    public String getText() {
        return text;
    }

    public String toStringTree(Parser parser) {
        return text;
    }

    public String toString() {
        return text;
    }

    @Override
    public Interval getSourceInterval() {
        return terminalNode.getSourceInterval();
    }
}
