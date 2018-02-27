package pl.codewise.internships;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/*
    Only single thread basic unit tests
 */
public class MessageQueueTest {

    private MyMessageQueue messageQueue;
    private Message m0 = new Message("d", 404);
    private Snapshot s0;

    @Before
    public void prepareQueue(){
        messageQueue = new MyMessageQueue();
        List<Message> messageArray = new ArrayList<>();

        Message m1 = new Message("a", 404);
        Message m2 = new Message("b", 200);
        Message m3 = new Message("c", 100);

        messageQueue.add(m1);
        messageQueue.add(m2);
        messageQueue.add(m3);
        messageArray.add(m1);
        messageArray.add(m2);
        messageArray.add(m3);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        s0 = new Snapshot(messageArray);

    }

    @Test
    public void snapshotTest(){
        assertEquals(s0, messageQueue.snapshot());
    }

    @Test
    public void numberOfErrorMessagesTest(){
        assertEquals(1, messageQueue.numberOfErrorMessages());
    }

    @Test
    public void errorCodePredicateTest(){
        assertTrue(Message.isErrorHttp().test(m0));
    }

    @Test
    public void isMessageExpiredPredicateTest(){
        assertTrue(Message.isMessageExpired(10).test(m0));
    }

}