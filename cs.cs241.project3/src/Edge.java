/**
 * Created by travis on 2/26/17.
 */

public class Edge<T> {
    private Vertex<T> from;
    private Vertex<T> to;
    private int cost;
    private boolean mark;

    public Edge(Vertex<T> from, Vertex<T> to) {
        this(from, to, 0);
    }

    public Edge(Vertex<T> from, Vertex<T> to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        mark = false;
    }

    public Vertex<T> getTo() {
        return to;
    }

    public Vertex<T> getFrom() {
        return from;
    }

    public int getCost() {
        return cost;
    }

    public String toString() {
        StringBuffer tmp = new StringBuffer("Edge[from: ");
        tmp.append(from.getName());
        tmp.append(",to: ");
        tmp.append(to.getName());
        tmp.append(", cost: ");
        tmp.append(cost);
        tmp.append("]");
        return tmp.toString();
    }
}
