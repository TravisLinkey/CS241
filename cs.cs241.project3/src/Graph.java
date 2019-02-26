import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Graph<T> {

     /**
     * This field reads the city file
     */
    public File cityFile = new File("./src/city.txt");
    /**
     * This field reads the road file
     */
    public File roadFile = new File("./src/road.txt");
    /**
     * This field is the multidimensional array which stores
     * the edge weights for the graph
     */
    public int[][] edgeGraph = null;
    /**
     * This {@link List} stores the {@link Vertex's} of
     * the {@link Graph}
     */
    private List<Vertex<T>> verticies;
    /**
     * This {@link List} stores the {@link Edge's} of
     * the {@link Graph}
     */
    private List<Edge<T>> edges;
    /**
     * This {@link Integer} array stores the cities numbers
     */
    private int[] cityNumber = new int[20];
    /**
     * This field is a counter for the cities
     */
    private int cityCounter = 0;
    /**
     * This field stores the valid city codes
     * in a {@link List}
     */
    private static ArrayList validCityCodes = new ArrayList();

    /**
     * This constructor instantiates the {@link ArrayList's}
     * which store the vertices and edges, as well as the
     * multidimensional array for the {@link Graph}
     * @throws IOException
     */
    public Graph() throws IOException {
        verticies = new ArrayList();
        edges = new ArrayList();
        edgeGraph = new int[20][20];
    }

    /**
     * This method gets the {@link ArrayList} of valid city codes
     * @return
     */
    public static ArrayList getValidCityCodes() {
        return validCityCodes;
    }

    /**
     * This method reads the file for the cities and calls
     * the {@link #parseStringCity(String)} method
     * @throws FileNotFoundException
     */
    public void populateCities() throws FileNotFoundException {
        Scanner cityScanner = new Scanner(cityFile);

        while(cityScanner.hasNextLine())
        {
            String line = cityScanner.nextLine();
            parseStringCity(line);
        }

        cityScanner.close();

    }

    /**
     * This method reads the file for the roads and calls
     * the {@link #parseStringRoad(String)} method
     * @throws FileNotFoundException
     */
    public void populateRoads() throws FileNotFoundException {
       Scanner roadScanner = new Scanner(roadFile);

       while(roadScanner.hasNextLine())
       {
           String line = roadScanner.nextLine();
           parseStringRoad(line);
       }

       roadScanner.close();
    }

    /**
     * This method parses the string passed as a parameter
     * adding the information to the {@link Vertex}
     *
     * @param string
     */
    public void parseStringCity(String string){
        String[] fields = string.split("\\s+");
        int number, pop, elev;
        String name, citycode;

        number = Integer.parseInt(fields[0]);
        pop = Integer.parseInt(fields[3]);
        elev = Integer.parseInt(fields[4]);

        citycode = fields[1];
        validCityCodes.add(citycode);
        cityNumber[cityCounter] = number;
        name = fields[2];

        Vertex vertex = new Vertex(number, citycode, name, pop, elev);
        this.addVertex(vertex);
    }

    /**
     * This method parses the string passed as a parameter
     * adding edge weight to the graph and creating an
     * {@link Edge} corresponding to the given vertices
     *
     * @param string
     */
    public void parseStringRoad(String string){
        String[] fields = string.split("\\s+");
        int fromVertex, toVertex, edgeWeight;

        fromVertex = Integer.parseInt(fields[0]) - 1;
        toVertex = Integer.parseInt(fields[1]) - 1;
        edgeWeight = Integer.parseInt(fields[2]);

        Vertex from = this.getVertex(fromVertex);
        Vertex to = this.getVertex(toVertex);

        edgeGraph[fromVertex][toVertex] = edgeWeight;

        addNeighbors(from, to, edgeWeight);

        Edge edge = new Edge(from,to,edgeWeight);
        this.addEdge(edge.getFrom(),edge.getTo(),edge.getCost());
    }

    /**
     * This method adds the {@link Vertex} passed as a parameter
     * to the {@link Vertex#outgoingEdges} {@link List}
     * @param from
     * @param to
     * @param cost
     */
    private void addNeighbors(Vertex from, Vertex to, int cost) {
        from.addOutgoingEdge(to, cost);
    }

    /**
     * This method adds the {@link Vertex} passed as a parameter
     * to the {@link List} of vertices
     * @param v
     * @return
     */
    public boolean addVertex(Vertex<T> v) {
        boolean added = false;
        if (verticies.contains(v) == false) {
            added = verticies.add(v);
        }
        return added;
    }

    /**
     * This method gets the {@link Vertex} from the {@link List}
     * of vertices at the index passed as a parameter.
     *
     * @param n
     * @return
     */
    public Vertex<T> getVertex(int n) {
        return verticies.get(n);
    }

    /**
     * This method changes the value of the {@link Vertex}
     * in the {@link Graph} which is stored in the multidimensional
     * array to the value passed as a parameter.
     * Effectively adding the edge to the graph
     *
     * @param from
     * @param to
     * @return
     */
    public boolean addEdge(Vertex<T> from, Vertex<T> to, int cost){

        Edge<T> e = new Edge(from, to, cost);
        if (from.findEdge(to) != null)
            return false;
        else {
            edgeGraph[from.getCityNumber()-1][to.getCityNumber()-1] = cost;
            edges.add(e);
            return true;
        }
    }

    /**
     * This method changes the value of the {@link Vertex}
     * in the {@link Graph} which is stored in the multidimensional
     * array to the value passed as a parameter.
     * Effectively adding the edge to the graph
     *
     * @param from
     * @param to
     * @return
     */
    public boolean userAddEdge(Vertex<T> from, Vertex<T> to, int cost) throws IllegalArgumentException {

        edgeGraph[from.getCityNumber()-1][to.getCityNumber()-1] = cost;
        System.out.println("You have inserted a road from " + from.getName() +
                " to " + to.getName() + " with a distance of " + cost);
        return true;
    }

    /**
     * This method changes the value of the {@link Vertex}
     * in the {@link Graph} which is stored in the multidimensional
     * array to 0. Effectively removing the edge from the graph
     * @param from
     * @param to
     * @return
     */
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) {

        if(edgeGraph[from.getCityNumber()-1][to.getCityNumber()-1] == 0)
        {
            System.out.println("The road from " + from.getName() +
                    " and " + to.getName() + " doesn't exist.");
            return false;
        }
        else
        {
            edgeGraph[from.getCityNumber()-1][to.getCityNumber()-1] = 0;
            System.out.println("You have removed the road from " + from.getName() + " to " + to.getName());
            return true;
        }
    }

    /**
     * This method finds and returns the {@link Vertex}
     * which corresponds to the city that is
     * passed as a parameter
     *
     * @param name
     * @return
     */
    public Vertex findVertexByName(String name) {
        Vertex<T> match = null;

        for (Vertex<T> v : verticies) {
            if (name.equals(v.getCityCode())) {
                match = v;
                break;
            }
        }
        return match;
    }

    /**
     * This method finds and returns the {@link Integer}
     * value which corresponds to the city that is
     * passed as a parameter
     *
     * @param name
     * @return
     */
    public int findNumberByName(String name) {
        Vertex<T> match = null;

        for (Vertex<T> v : verticies) {
            if (name.equals(v.getCityCode())) {
                match = v;
                break;
            }
        }
        return match.getCityNumber();
    }

    /**
     * This method implements Dijkstra's algorithm
     * to find the shortest path between two {@link Vertex's}
     * passed as arguments
     *
     * @param start
     * @param end
     */
    public void getShortestPath(Vertex start, Vertex end) {
        boolean done = false;
        Stack<Vertex> path = new Stack();
        Vertex frontVertex = start;
        Vertex nextNeighbor = null;
        int nextCost = 0;
        int iteration = 1;

        PriorityQueue<GraphEntry> queue = new PriorityQueue();

        queue.add(new GraphEntry(start,0,null));

        while(!done && queue.peek() != null)
        {
           GraphEntry frontEntry = queue.poll();
           frontVertex = frontEntry.getTo();

           if(!frontVertex.isVisited())
           {
              frontVertex.setVisited(true);
              frontVertex.setCurrentCost(frontEntry.getTotalWeight());
              frontVertex.setFrom(frontEntry.getFrom());

               if(this.allverticesVisted())
                   done = true;
               else
               {
                   int i = 0;
                   while(frontVertex.getOutgoingEdgeCount() > i)
                   {
                     nextNeighbor = frontVertex.getOutgoingEdge(i).getTo();
                     int weightOfNextNeighbor = frontVertex.getOutgoingEdge(i).getCost();

                     if(!nextNeighbor.isVisited())
                     {
                         nextCost = weightOfNextNeighbor + frontVertex.getCurrentCost();
                         queue.add(new GraphEntry(nextNeighbor, nextCost, frontVertex));
                     }
                     i++;

                     if(nextNeighbor.getName().equals(end.getName()))
                     {
                         if(nextCost <= nextNeighbor.currentCost || nextNeighbor.currentCost == 0)
                         {
                             end.setCurrentCost(nextCost);
                             path.push(nextNeighbor);
                             path.push(frontVertex);
                             while(frontVertex.getFrom() != null)
                             {
                                 frontVertex = frontVertex.getFrom();
                                 path.push(frontVertex);
                             }
                         }
                     }
                   }
               }
           }
        }

        int pathCost = end.currentCost;
        System.out.print("The minimum distance between "+ start.getName() + " and " + end.getName() + " is " + pathCost
        + " through the route: ");

        while(!path.isEmpty())
        {
            System.out.print(path.pop().getCityCode() + ", ");
        }

        System.out.println();
    }

    /**
     * This method returns a boolean value which represents
     * whether all the vertices in the {@link List}
     * have been visited or not
     *
     * @return boolean
     */
    private boolean allverticesVisted() {
        boolean allvisted = false;
        for(Vertex v: verticies)
        {
            if(v.isVisited())
            {
               allvisted = true;
            }
            else
                allvisted = false;
        }
        return allvisted;
    }

    /**
     * This method outputs the information associated with
     * the {@link Vertex} passed as a parameter
     *
     * @param city
     */
    public void QueryCity(Vertex city) {
        System.out.println(city.getCityNumber() + " " + city.getCityCode() +  " " + city.getName() + " " +
                city.getPopulation() + " " + city.getElevation());
    }
}

