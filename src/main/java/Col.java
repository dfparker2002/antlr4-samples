/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import java.io.InputStream;
import java.io.PrintStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr4.RowsBaseListener;
import antlr4.RowsLexer;
import antlr4.RowsParser;
import antlr4.RowsParser.RowContext;

public class Col {
    public static void main(String[] args) throws Exception {
        String resource = "/t.rows";
        InputStream fileInput = Col.class.getResourceAsStream(resource);
        if (fileInput==null) {
            throw new IllegalStateException("resource file not found:"+resource); 
        };
        ANTLRInputStream input = new ANTLRInputStream(fileInput);
        RowsLexer lexer = new RowsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        int col = 2;//Integer.valueOf(args[0]);
        RowsParser parser = new RowsParser(tokens, col); // pass column number!
        ParseListener parseListener = new ParseListener();
        parser.setBuildParseTree(false); // don't waste time building a tree
        parser.addParseListener(parseListener);
        parser.file(); // parse
    }
    
    static class ParseListener extends RowsBaseListener {
       static PrintStream out=System.out;

        @Override
        public void exitRow(RowContext ctx) {
            out.println("exitRow: start="+ctx.start.getText()+"; end="+ctx.stop.getText());
        }

        @Override
        public void visitTerminal(TerminalNode node) {
            out.println("visitTerminal: node="+node);
        }
        
    }
}
