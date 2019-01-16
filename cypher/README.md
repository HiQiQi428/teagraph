# cypher 语法解析器



## cypher-antlr 目录

### doc 目录

* Antlr4-usage.md：antlr4的命令用法说明
* antlr4_parse_tree.svg：测试生成的AST

### test 目录

包含对```Atom、BooleanLiteral、Digit、DoubleLiteral、Expression、IntegerLiteral、ListLiteral、Literal、MapLiteral、NumberLiteral、Parameter、StringLiteral```的单独测试。

#### 如何使用 shell 脚本测试

首先使用 build.sh 从 g4 文件生成 java 代码，示例：```./build.sh TestDigit.g4```，然后使用 run.sh 测试生成的 java 代码，示例：```./run.sh TestDigit.g4 digit```，然后在接着输入测试语句，以 CTRL + D 结束

##### antlr4 和 grun 脚本配置参考

* 下载 jar 包，我用的是 antlr-4.7.1-complete.jar

* 配置环境变量

  ```shell
  export ANTLR_HOME=/home/luncert/Platform/antlr/antlr-4.7.1-complete.jar
  export CLASSPATH=$ANTLR_HOME:$CLASSPATH
  ```

* antlr4 脚本

  ```shell
  #!/bin/sh
  java org.antlr.v4.Tool "$@"
  ```

* grun 脚本

  ```shell
  #!/bin/sh
  java org.antlr.v4.gui.TestRig "$@"
  ```

### v2 目录

包含最新 g4 文件 Cypher.g4，和编译脚本 build.sh、run.sh（已经用不着了），其中 Cypher-expression.g4 是用来从 Cypher.g4 中抽离出的用于单独测试 expression 的文件。

## scripts 目录

* grammer-build.sh：从```cypher-antlr/v2/Cypher.g4```生成 java 代码到```src/main/java/org/luncert/cypher/grammar/```，因为我是用的 vscode，没有 antlr4 和 java 集成开发的插件，所以使用这种方式。。。
* javaFileModify.py：修改上面生成的 java 代码的 package 行

## src 目录

可运行的 cypher 解析器代码，以及用于测试的 74 条 cypher 语句（```src/main/resources/Test.cypher```），运行主类会解析 Test.cypher，并生成对应的 AST 到```src/output.db```

