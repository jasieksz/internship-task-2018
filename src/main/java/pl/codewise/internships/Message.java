package pl.codewise.internships;


import java.util.Objects;
import java.util.function.Predicate;

public class Message implements Comparable<Message> {

    private final String userAgent;
    private final int errorCode;
    private final long creationTime;

    public Message(String userAgent, int errorCode) {
        this.userAgent = userAgent;
        this.errorCode = errorCode;
        this.creationTime = System.currentTimeMillis();
    }


    public String getUserAgent() {
        return userAgent;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return creationTime == message.creationTime;
    }

    public int compareTo(Message message) {
        return Long.compare(this.creationTime, message.getCreationTime());
    }

    public static Predicate<Message> isMessageExpired() {
        return message -> System.currentTimeMillis() - message.getCreationTime() > 1000*60*5;
    }

    public static Predicate<Message> isErrorHttp() {
        return message -> message.errorCode == 404;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "userAgent='" + userAgent + '\'' +
                ", errorCode=" + errorCode +
                ", creationTime=" + creationTime +
                '}';
    }
}