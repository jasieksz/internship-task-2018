package pl.codewise.internships;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Snapshot {
    private final List<Message> messages;

    public Snapshot(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snapshot snapshot = (Snapshot) o;
        return Objects.equals(messages, snapshot.messages);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messages);
    }

    public List<Message> getMessages() {
        return messages;

    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "messages=" + messages +
                '}';
    }
}
