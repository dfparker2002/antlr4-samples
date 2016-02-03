/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
// import ANTLR's runtime libraries
import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import antlr4.ArrayInitLexer;
import antlr4.ArrayInitParser;

public class Test {
    public static void main(String[] args) throws Exception {
        String resource = "/array.data";
        InputStream fileInput = Test.class.getResourceAsStream(resource);
        if (fileInput==null) {
            throw new IllegalStateException("resource file not found:"+resource); 
        };
        ANTLRInputStream input = new ANTLRInputStream(fileInput);

        // create a lexer that feeds off of input CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        ArrayInitParser parser = new ArrayInitParser(tokens);

        ParseTree tree = parser.init(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}
