# 问题

* tkglb和tkgdb只是完成了通信，没有测试基于readTime的负载均衡是否有效
* 没有实现结果集合并，有一点令我疑惑的是，一次cypher请求更可能是MultipartQuery还是SingleQuery呢，因为一个SingleQuery的读只能在一台机器上运行，如果大部分请求都是SingleQuery的话，读写分离似乎作用不大
* tkglb和tkgdb的通信是使用json进行传输的，而没有用Marshelling直接编码对象，因为直接用会报错而我还没有解决

# 将来的目标

* 分析neo4j工作特性，设计特定的负载均衡算法
* 一致性保障
* 容错备份机制