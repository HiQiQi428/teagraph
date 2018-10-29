cd ..
rm -rd cypher-antlr/v2/target
cd cypher-antlr/v2
antlr4 Cypher.g4 -o target
cd ../..
cp cypher-antlr/v2/target/*.java src/main/java/org/luncert/cypher/grammar/
cd scripts
python javaFileModify.py