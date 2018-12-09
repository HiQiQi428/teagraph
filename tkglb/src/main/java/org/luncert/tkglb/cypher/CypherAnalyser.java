package org.luncert.tkglb.cypher;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class CypherAnalyser {

    public static List<CPiece> analyse(String raw) throws Exception {
        CharStream input = CharStreams.fromString(raw);
        CypherLexer lexer = new CypherLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CypherParser parser = new CypherParser(tokens);
        ParseTree tree = parser.cypher();

        AstResolver resolver = new AstResolver();
        return resolver.resolve(tree);
    }

}