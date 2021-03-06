package pl.codewise.internships;

public class Tuple<F, S> {
    private final F first;
    private final S second;

    public Tuple(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F first(){
        return first;
    }

    public S second(){
        return second;
    }
}