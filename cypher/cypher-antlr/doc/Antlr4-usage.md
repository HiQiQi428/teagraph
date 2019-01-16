![avatar](pic/1.png)
## 递归下降的语法分析器
## 前向预测解决歧义问题
## TestRig参数
* -tokens 打印出词法符号流
* -tree 以LISP格式打印出语法分析树
* -gui 在对话框中以可视化方式显示语法分析树 eq: grun MapInit init -gui
* -ps file.ps 以PostScript格式生成可视化语法分析树,然后将其存储于file.ps
* -encoding encodingname 若当前的区域设定无法正确读取输入,使用这个选项制定测试组件输入文件的编码
* -trace 打印规则的名字以及进入和离开该规则时的词法符号
* -diagnostics 开启解析过程中的调试信息输出
* -SLL 使用另外一种更快但功能稍弱的解析策略
## 监听器和访问者模式
p89