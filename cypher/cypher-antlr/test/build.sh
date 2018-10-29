rm -rd target
antlr4 $1 -o target
javac target/*.java