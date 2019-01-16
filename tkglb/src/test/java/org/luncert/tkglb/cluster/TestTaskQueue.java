package org.luncert.tkglb.cluster;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.tkglb.cypher.CPiece;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.luncert.tkglb.cypher.CPiece.UnionType;

@RunWith(JUnit4.class)
public class TestTaskQueue {

    private TaskQueue taskQueue = new TaskQueue();

    private List<CPiece> genCPiece() {
        final Random random = new Random();
        List<CPiece> pieces = new LinkedList<>();
        CPiece piece = CPiece.builder()
                        .pieceType(random.nextBoolean() ? PieceType.Read : PieceType.Update)
                        .unionType(UnionType.None)
                        .content(null).build();
                        pieces.add(piece);
                        
        for (int i = 0, limit = random.nextInt(5); i < limit; i++) {
            piece = CPiece.builder()
                        .pieceType(random.nextBoolean() ? PieceType.Read : PieceType.Update)
                        .unionType(UnionType.Union)
                        .content(null).build();
            pieces.add(piece);
        }
        return pieces;
    }

    /**
     * 在并发环境中测试TaskQueue的enQueue，deQueue，waitForTask三个方法
     * 
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        List<CPiece> pieces = genCPiece();
        Thread t = new Thread(() -> {
            try {
                taskQueue.waitForTask();
                assert(taskQueue.size() == pieces.size());
                System.out.println(taskQueue.deQueue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t.start();
        taskQueue.enQueue(pieces);
        t.join();
    }

}