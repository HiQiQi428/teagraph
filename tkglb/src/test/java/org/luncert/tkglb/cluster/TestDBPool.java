package org.luncert.tkglb.cluster;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.tkglb.cluster.bean.DBNode;
import org.luncert.tkglb.cluster.bean.NodeStatus;
import org.luncert.tkglb.cluster.bean.Task;
import org.luncert.tkglb.cypher.CPiece;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.luncert.tkglb.cypher.CPiece.UnionType;

@RunWith(JUnit4.class)
public class TestDBPool {

    private DBPool pool = new DBPool();
    private DBNode node, node1, node2, node3;

    private Task newTask() {
        CPiece piece = CPiece.builder()
                        .pieceType(PieceType.Read)
                        .unionType(UnionType.None)
                        .content("MATCH (n:Pig) RETURN n")
                        .build();
        return new Task(0, 0, piece);
    }

    @Before
    public void before() {
        // node = pool.newDBNode();
        // node1 = pool.newDBNode();
        // node2 = pool.newDBNode();
        // node3 = pool.newDBNode();
    }

    @Test
    public void testGetReadyDBNode() throws InterruptedException {
        // ready -> reading
        node1.execute(newTask());
        node2.execute(newTask());
        node3.execute(newTask());

        // reading -> ready
        node1.executeFinished();
        node2.executeFinished();
        node3.executeFinished();

        // read -> reading
        node1.execute(newTask());
        node2.execute(newTask());

        // reading -> ready
        node1.executeFinished();
        node2.executeFinished();

        node1.execute(newTask());
        node1.executeFinished();

        pool.printHeap();
        System.out.println(pool.getReadyDBNode());
    }

    @Test
    public void testAction() {
        String nodeId = node.getId();
        
        pool.newAction(nodeId, NodeStatus.Reading, (n) -> System.out.println("node is reading"));
        
        node.execute(newTask());

        pool.newAction(nodeId, NodeStatus.Ready, (n) -> {
            System.out.println("node is ready");
            n.execute(null);
        });

        node.executeFinished();
    }

    @Test
    public void testActionConcurrent() throws InterruptedException {
        // 模拟异步
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node.execute(newTask());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node.executeFinished();
        }).start();

        pool.newAction(node.getId(), NodeStatus.Reading, (node) -> System.out.println("node is reading")).sync();

        pool.newAction(node.getId(), NodeStatus.Ready, (node) -> System.out.println("node is ready")).sync();
    }

}