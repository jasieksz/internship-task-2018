package pl.codewise.internships;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

public class MyMessageQueue implements MessageQueue {

    private PriorityBlockingQueue<Message> blockingQueue;

    public int getSize() {
        return blockingQueue.size();
    }

    public MyMessageQueue() {
        this.blockingQueue = new PriorityBlockingQueue<Message>(100);
    }

    public void add(Message message) {
        blockingQueue.add(message);
    }

//    private void removeExpiredMessages() {
//        //blockingQueue.stream()
//        // .filter(m -> System.currentTimeMillis() - m.getCreationTime() > 1000*60*5);
//        while (blockingQueue.iterator().hasNext()) {
//            Message message = blockingQueue.iterator().next();
//            if (System.currentTimeMillis() - message.getCreationTime() > 1000 * 60 * 5) {
//                blockingQueue.remove(message);
//            }
//        }
//    }

    public Snapshot snapshot() {
//        List<Message> snapshotMessages = blockingQueue.stream()
//                .filter(m -> Message.isMessageExpired().test(m))
//                .limit(100)
//                .collect(Collectors.toList());
//
//        return new Snapshot(snapshotMessages);
        List<Message> snapshotMessages = new ArrayList<>();
        Iterator<Message> it = blockingQueue.iterator();
        int messageCount = 0;

        while (it.hasNext() && messageCount < 100){
            Message message = it.next();
            messageCount += 1;
            if (!Message.isMessageExpired().test(message)) {
                snapshotMessages.add(message);
            } else {
                it.remove();
                messageCount -= 1;
            }
        }
        return new Snapshot(snapshotMessages);
    }

    public long numberOfErrorMessages() {
//        return blockingQueue.stream()
//                .filter(m -> Message.isMessageExpired().test(m))
//                .limit(100)
//                .filter(m -> Message.isErrorHttp().test(m))
//                .count();
        Iterator<Message> it = blockingQueue.iterator();
        int messageCount = 0;
        int errorCount = 0;

        while (it.hasNext() && messageCount < 100){
            Message message = it.next();
            messageCount += 1;
            if (!Message.isMessageExpired().test(message)){
                if (Message.isErrorHttp().test(message)){
                    errorCount += 1;
                }
            } else {
                it.remove();
                messageCount -= 1;
            }

        }

        return errorCount;
    }
}
