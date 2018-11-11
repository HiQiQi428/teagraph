package org.luncert.teagraph.cypher;

import java.io.FileInputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.luncert.teagraph.cypher.astResolver.AstResolver;

public class CypherAnalyser {

    public static void analyse(String raw) {
        CharStream input = CharStreams.fromString(raw);
        CypherLexer lexer = new CypherLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CypherParser parser = new CypherParser(tokens);
        ParseTree tree = parser.cypher();

        AstResolver resolver = new AstResolver();
        try {
			resolver.init(new FileInputStream("src/main/resource/CypherSolution.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        resolver.resolve(tree);
    }

}