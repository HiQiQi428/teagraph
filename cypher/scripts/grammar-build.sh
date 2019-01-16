# 清理cypher-antlr/v2/target目录
cd ..
rm -rd cypher-antlr/v2/target

# 从g4语法生成java代码到cypher-antlr/v2/target目录
cd cypher-antlr/v2
antlr4 Cypher.g4 -o target

# 将生成的java代码拷贝到src/main/java/org/luncert/cypher/grammar目录下
cd ../..
cp cypher-antlr/v2/target/*.java src/main/java/org/luncert/cypher/grammar/

# 运行python脚本，修改java文件package行
cd scripts
python javaFileModify.py