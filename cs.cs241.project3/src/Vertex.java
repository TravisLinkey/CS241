import java.util.ArrayList;
import java.util.List;

/**
 * Created by travis on 2/26/17.
 */

public class Vertex<T> {
    private List<Edge<T>> incomingEdges;
    private List<Edge<T>> outgoingEdges;

    private int City_Number;
    private String City_Code;
    private String Full_City_Name;
    private int Population;
    private int Elevation;

    private boolean isVisited;
    private String number;

    public Vertex() {
        this(0, null, null, 0, 0);
    }

    public Vertex(String n) {
        this(0,null,n, 0, 0);
    }

    public Vertex(int city_Number, String city_Code, String full_City_Name, int population, int elevation) {
        incomingEdges = new ArrayList<Edge<T>>();
        outgoingEdges = new ArrayList<Edge<T>>();
        City_Code = city_Code;
        City_Number = city_Number;
        Full_City_Name = full_City_Name;
        Population = population;
        Elevation = elevation;
        isVisited = false;
    }

    public String getName() {
        return Full_City_Name;
    }

    public boolean addEdge(Edge<T> e) {
        if (e.getFrom() == this)
            outgoingEdges.add(e);
        else if (e.getTo() == this)
            incomingEdges.add(e);
        else
            return false;
        return true;
    }

    public void addOutgoingEdge(Vertex<T> to, int cost) {
        Edge<T> out = new Edge<T>(this, to, cost);
        outgoingEdges.add(out);
    }

    public void addIncomingEdge(Vertex<T> from, int cost) {
        Edge<T> out = new Edge<T>(this, from, cost);
        incomingEdges.add(out);
    }

    public boolean hasEdge(Edge<T> e) {
        if (e.getFrom() == this)
            return incomingEdges.contains(e);
        else if (e.getTo() == this)
            return outgoingEdges.contains(e);
        else
            return false;
    }

    public boolean remove(Edge<T> e) {
        if (e.getFrom() == this)
            incomingEdges.remove(e);
        else if (e.getTo() == this)
            outgoingEdges.remove(e);
        else
            return false;
        return true;
    }

    public int getIncomingEdgeCount() {
        return incomingEdges.size();
    }

    public Edge<T> getIncomingEdge(int i) {
        return incomingEdges.get(i);
    }

    public List getIncomingEdges() {
        return this.incomingEdges;
    }

    public int getOutgoingEdgeCount() {
        return outgoingEdges.size();
    }

    public Edge<T> getOutgoingEdge(int i) {
        return outgoingEdges.get(i);
    }

    public List getOutgoingEdges() {
        return this.outgoingEdges;
    }

    public Edge<T> findEdge(Vertex<T> dest) {

        for (Edge<T> e : outgoingEdges)
        {
            if (e.getTo().getName().compareTo(dest.getName()) == 0)
                return e;
        }

        return null;
    }

    public Edge<T> findEdge(Edge<T> e) {
        if (outgoingEdges.contains(e))
            return e;
        else
            return null;
    }

    public int cost(Vertex<T> dest) {
        if (dest == this)
            return 0;

        Edge<T> e = findEdge(dest);
        int cost = Integer.MAX_VALUE;
        if (e != null)
            cost = e.getCost();
        return cost;
    }

    public boolean hasEdge(Vertex<T> dest) {
        return (findEdge(dest) != null);
    }

    public void setVisited(boolean set) {
        isVisited = set;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public String toString() {
        StringBuffer tmp = new StringBuffer("Vertex(");
        tmp.append(Full_City_Name);
        tmp.append(", data=");
        tmp.append(Population);
        tmp.append("), in:[");
        for (int i = 0; i < incomingEdges.size(); i++) {
            Edge<T> e = incomingEdges.get(i);
            if (i > 0)
                tmp.append(',');
            tmp.append('{');
            tmp.append(e.getFrom().Full_City_Name);
            tmp.append(',');
            tmp.append(e.getCost());
            tmp.append('}');
        }
        tmp.append("], out:[");
        for (int i = 0; i < outgoingEdges.size(); i++) {
            Edge<T> e = outgoingEdges.get(i);
            if (i > 0)
                tmp.append(',');
            tmp.append('{');
            tmp.append(e.getTo().Full_City_Name);
            tmp.append(',');
            tmp.append(e.getCost());
            tmp.append('}');
        }
        tmp.append(']');
        return tmp.toString();
    }

    public int getPopulation() {
        return Population;
    }

    public int getElevation() {
        return Elevation;
    }

    public String getCityCode() {
        return City_Code;
    }

    public int getCityNumber() {
        return City_Number;
    }
}