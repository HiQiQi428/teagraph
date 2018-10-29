START n = node:Pig(name = 'A') RETURN n
START n = node:Pig('name:A') RETURN n
START r = relationship:Own(name = 'Andres') RETURN r
MATCH (n { name: 'A' })-->(x) RETURN n, count(*) // 返回了开始节点和与之关联的节点数量
MATCH (person:Person) MERGE (city:City { name: person.bornIn }) RETURN city
MERGE (keanu:Person { name: 'Keanu Reeves' }) ON CREATE SET keanu.created = timestamp() RETURN keanu.name, keanu.created
MERGE (person:Person) ON MATCH SET person.found  = True RETURN person.name, person.found
MERGE (keanu:Person { name: 'Keanu Reeves' }) ON CREATE SET keanu.created = timestamp() ON MATCH SET keanu.lastSeen = timestamp(), keanu.found = True RETURN keanu.name, keanu.created, keanu.lastSeen // 如果节点不存在则设置created属性，否则设置lastSeen和found属性
CREATE CONSTRAINT ON (n:Person) ASSERT n.name IS UNIQUE
CREATE CONSTRAINT ON (n:Person) ASSERT n.role IS UNIQUE
MERGE (michael:Person{ name: 'Michael Douglas', role: 'Grodon Gekko' }) RETURN michael
MATCH (n { name: 'Andres' }) SET n.name = NULL RETURN n // 删除节点属性
MATCH (at { name: 'Andres' }), (pn { name: 'Peter' }) SET at = pn RETURN at, pn // 复制属性
MATCH (peter { name: 'Peter' }) SET peter += { hungry: TRUE, position: 'Entrepreneur' } RETURN peter
MATCH (n { name: 'Stefan' }) SET n:German:Bossman RETURN n
MATCH (n { name: 'Andres' }) DETACH DELETE n
MATCH (n { name: 'Andres' }) REMOVE n.age, n:German:Swedish RETURN n
MATCH p = (begin)-[*]-(END) WHERE begin.name = 'A' AND END.name = 'D' FOREACH (n IN nodes(p) | SET n.marked = TRUE )
MATCH (a:Person {name: 'A'}) FOREACH (name IN ['Mike', 'Carl', 'Bruce'] | CREATE (a)-[:FRIEND]->(:Person {name: 'name'}))
MATCH (root { name: 'root' }) CREATE UNIQUE (root)-[:LOVES]-(someone:German { alone: false }) RETURN someone // 原本root节点没有与任何节点有LOVES关系，因此创建了一个新的节点与root建立LOVES关系
MATCH (n) RETURN ORDER BY n.name, n.age DESC // 多属性排序，先根据第一个属性排序，值相同时再检查第二个属性，依次类推
MATCH (n) RETURN n ORDER BY n.name SKIP toInt(3 * rand()) + 1 LIMIT toInt(3 * rand()) + 1
MATCH (david { name: 'David' })--(otherPerson)-->() WITH otherPerson, count(*) AS foaf WHERE foaf > 1 RETURN otherPerson
MATCH (n) WITH n ORDER BY n.name DESC LIMIT 3 RETURN collect(n.name)
MATCH (n { name: 'Anders' })--(m) WITH m ORDER BY m.name DESC LIMIT 1 MATCH (m)--(o) RETURN o.name
UNWIND [1, 2, 3] AS x RETURN x
WITH [1, 1, 2, 2] AS coll UNWIND coll AS x WITH DISTINCT x RETURN collect(x) AS set
MATCH (n:Actor) RETURN n.name AS name UNION ALL MATCH (n:Movie) RETURN n.title AS name // UNION ALL将两个查询的结果组合在一起，结果包含重复行
MATCH (n:Actor) RETURN n.name AS name UNION MATCH (n:Movie) RETURN n.title AS name // 结果无重复值
CALL db.labels
CALL `db`.`labels`
CALL org.neo4j.procedure.example.addNodeToIndex('users', 0, 'name')
CALL org.neo4j.procedure.example.addNodeToIndex('users', $node, 'name')
CALL db.labels() YIELD result AS label WHERE label CONTAINS 'User' RETURN count(label) AS numLabels
MATCH (n:Member) RETURN org.neo4j.function.example.join(collect(n.name))
MATCH (john {name: 'John'})-[:friend]->(fof) RETURN john.name, fof.name
MATCH (user)-[:friend]->(follower) WHERE user.name IN ['Joe', 'John', 'Sara', 'Maria', 'Steve'] AND follower.name = ~'S.*' RETURN user.name, follower.name
MATCH (n {name: 'John'})-[:FRIEND]-(friend) WITH n, count(friend) AS friendsCount WHERE friendsCount > 3 RETURN n, friendsCount
MATCH (n {name: 'John'})-[:FRIEND]-(friend) WITH n, count(friend) AS friendsCount SET n.friendsCount = friendsCount RETURN n.friendsCount
CREATE (adam:User{name: 'Adam'}), (pernilla:User{name: 'Pernilla'}), (david:User{name: 'David'}), (adam)-[:FRIEND]->(pernilla), (pernilla)-[:FRIEND]->(david)
MATCH (user: User{name: 'Adam'})-[r1: FRIEND]-()-[r2:FRIEND]-(friend_of_a_friend) RETURN friend_of_a_friend.name AS fofName
MATCH (user:User{name: 'Adam'})-[r1:FRIEND]-(friend), (friend)-[r2:FRIEND]-(friend_of_a_friend) RETURN friend_of_a_friend.name AS fofName
MATCH (user:User{name: 'Adam'})-[r1:FRIEND]-(friend)
MATCH (friend)-[r2:FRIEND]-(friend_of_a_friend) RETURN friend_of_a_friend.name AS fofName
START n = node:nodes(name = 'A') RETURN n
MATCH (n) RETURN CASE n.eyes WHEN 'blue' THEN 1 WHEN 'brown' THEN 2 ELSE 3 END AS result
MATCH (a) WHERE a.surname STARTS WITH 'Sven' AND a.prename ENDS WITH 'PY' RETURN a
MATCH (n) WHERE n.name = ~'.*h.*' AND n.address = ~'Sweden\\/Malmo' RETURN n // 注意反斜杠也要转义
MATCH (n) RETURN n.name SKIP 1 LIMIT 3
MATCH (n) WHERE id(n) = 10 RETURN n.name
MATCH (n) WHERE id(n) IN [1, 3, 54] RETURN n.name
MATCH (n) WHERE 21 < n.age < 30 RETURN n
MATCH (n) RETURN n // 这是注释
MATCH (me)-[:KNOWS*1..2]-(remote_friend) WHERE me.name = 'Filipa' RETURN remote_friend.name
MATCH (a:Person{name: 'Charile Sheen'}) RETURN [(a)-->(b) WHERE b:Movie | b.year] AS years
MATCH (actor:Person {name: 'Charlie Sheen'})-[:ACTED_IN]->(movie:Movie) RETURN actor { .name, .realName, movies: collect(movie { .title, .year })}
MATCH (actor:Person)-[:ACTED_IN]->(movie:Movie) WITH actor, count(movie) AS nrOfMovies RETURN actor { .name, nrOfMovies }
MATCH (actor:Person{ name: 'Charlie Sheen' }) RETURN actor { .*, .age }
MATCH (wallstreet { title: 'Wall Street' })<-[:ACTED_IN | :DIRECTED]-(person) RETURN person.name
MATCH (n { name: 'Rob Reiner'})-[r:`TYPE WITH SPACE`]->() RETURN type(r)
MATCH (martin { name: 'Charlie Sheen'})-[:ACTED_IN*1..3]-(movie:Movie) RETURN movie.title // 结果：['Wall Street', 'The American President', 'The American President']
MATCH p = (michael {name: 'Michael Douglas'})-->() RETURN p // 结果：[Node[2]\{name:"Michael Douglas"\},:ACTED_IN[5]\{role:"President Andrew Shepherd"\},Node[6]\{title:"The American President"\}]
MATCH (a)-[r]-(b) WHERE id(r)=0 RETURN a,b // 尝试匹配两个方向的关系
MATCH (martin:Person { name: 'Martin Sheen' }), (oliver:Person { name: 'Oliver Stone' }), p = shortestPath((martin)-[*..15]-(oliver)) RETURN p
MATCH (martin:Person { name: 'Martin Sheen' }), (oliver:Person { name: 'Oliver Stone' }), p = shortestPath((martin)-[*..15]-(oliver)) WHERE NONE (r IN rels(p) WHERE type(r) = 'FATHER') RETURN p // WHERE断言确保不考虑两个节点间的父亲/儿子关系
MATCH (martin:Person { name: 'Martin Sheen' }), (michael:Person { name: 'Michael Douglas' }), p = allShortestPaths((martin)-[*]-(michael)) RETURN p
MATCH (a:Movie {title: 'Wall Street'}) OPTIONAL MATCH (a)-->(x) RETURN x // 返回null，因为没有外向关系
MATCH (n) WHERE n:Swedish AND n.name = 'Peter' OR NOT exists(n.name) AND n[toLower('AGE')] < 30 RETURN n // has被exists代替了
MATCH (n) WHERE n.name = ~'(?i)ANDR.*' RETURN n // 非大小写敏感的正则表达式
MATCH (tobias { name: 'Tobias' }), (others) WHERE others.name IN ['Andres', 'Peter'] AND (tobias)<--(others) RETURN others // 模式作为断言
MATCH (n)-[r]->() WHERE n.name = 'Andres' AND type(r) = ~'K.*' RETURN r
MATCH (n) WHERE n.belt = 'white' OR n.belt IS NULL RETURN n
CREATE (n:Person:Swedish) RETURN n // 多标签节点
MATCH (`Tom Smith` { name: 'Tom Smith' }) RETURN `Tom Smith`.name AS source