package org.luncert.teagraph.cypher.astResolver;

import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestAstResolver {

    @Test
    public void test() throws Exception {
        AstResolver resolver = new AstResolver();
        resolver.init(new FileInputStream("src/main/resource/CypherSolution.xml"));
    }

}