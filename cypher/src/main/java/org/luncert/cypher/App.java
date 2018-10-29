package org.luncert.cypher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.luncert.cypher.grammar.CypherLexer;
import org.luncert.cypher.grammar.CypherParser;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        File outputFile = new File("output.db");
        PrintWriter pw = new PrintWriter(outputFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("Test.cypher")));
        reader.lines().forEach((line) -> {
            CharStream input = CharStreams.fromString(line);
            CypherLexer lexer = new CypherLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CypherParser parser = new CypherParser(tokens);
            ParseTree tree = parser.cypher();
            pw.append(tree.toStringTree(parser)).append('\n');
        });
        reader.close();
        pw.close();
    }
}
