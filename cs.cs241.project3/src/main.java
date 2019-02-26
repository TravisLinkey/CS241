import java.io.IOException;

/**
 * Created by travis on 2/23/17.
 */
public class main {
    public static void main(String args[]) throws IOException {
        UI userinterface = new UI();
        Graph newGraph = new Graph();
        newGraph.populateCities();
        newGraph.populateRoads();

        do {
            switch(userinterface.getCommand())
            {
                case 'Q':
                    System.out.print("City code:");
                    newGraph.QueryCity(userinterface.getCity());
                    break;
                case 'D':
                    System.out.print("City codes:");
                    newGraph.minDistance(userinterface.getCity(), userinterface.getCity());
                    break;
                case 'I':
                    System.out.print("City codes and distance:");
                    newGraph.userAddEdge(userinterface.getCity(),userinterface.getCity(),userinterface.getDistance());
                    break;
                case 'R':
                    System.out.print("City codes:");
                    newGraph.removeEdge(userinterface.getCity(),userinterface.getCity());
                    break;
                case 'H':
                    userinterface.getChoice();
                    break;
                case 'E':
                    System.exit(0);
                    break;
            }
        }while(userinterface.ans != 'E');

    }

}
