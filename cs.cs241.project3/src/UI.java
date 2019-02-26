import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by travis on 2/23/17.
 */
class UI {
    /**
     * This field stores the {@link Scanner} variable
     * to take user input
     */
    private Scanner scan = new Scanner(System.in);
    /**
     * This field stores the answer of the user input
     */
    public char ans;

    /**
     * This method outputs the choices for the menu
     */
    public void getChoice(){
        System.out.println("Q Query the city information by entering the city code.");
        System.out.println("D Find the minimum distance between two cities.");
        System.out.println("I Insert a road by entering two city codes and distance.");
        System.out.println("R Remove an existing road by entering two city codes.");
        System.out.println("H Display this message.");
        System.out.println("E Exit.");
    }

    /**
     * This field gets the user input choice
     * from the menu
     * @return
     */
    public char getCommand() {
        System.out.print("Command? ");

        ans = takeInput('Q','D','I','R','H','E');

        return ans;
    }

    /**
     * This method returns the {@link Vertex}
     * which corresponds to the user input
     * city codes
     * @return
     * @throws IOException
     */
    public Vertex getCity() throws IOException {
        Graph graphVertex = new Graph();
        graphVertex.populateCities();
        graphVertex.populateRoads();

        ArrayList citycodes = Graph.getValidCityCodes();

        String city = takeInput(citycodes);

        return graphVertex.findVertexByName(city);
    }

//    public int getCityNumber() throws IOException {
//        Graph graphVertex = new Graph();
//        graphVertex.populateCities();
//
//        ArrayList citycodes = Graph.getValidCityCodes();
//
//        String city = takeInput(citycodes);
//
//        return graphVertex.findNumberByName(city);
//    }

    /**
     * This method checks whether user input is valid or not.
     * @param validInputs all possible valid char inputs
     * @return choice the correct input for the prompt
     */
    private  char takeInput(char...validInputs) {
        char choice;

        try
        {
            choice = scan.next().charAt(0);
            if(!isInputValid(validInputs, choice))
                throw new InputMismatchException();
        } catch(InputMismatchException e) {
            System.out.println("Error: Invalid input");
            scan.nextLine();
            choice = 'X';
        }
        return choice;
    }

    /**
     * This method checks whether user input is valid or not.
     * @param validInputs all possible valid char inputs
     * @return choice the correct input for the prompt
     */
    private String takeInput(ArrayList validInputs) {
        String choice;

        do {
            try
            {
                choice = scan.next();
                if(!isInputValid(validInputs, choice))
                    throw new InputMismatchException();
            } catch(InputMismatchException e) {
                System.out.println("Error: Invalid input");
                scan.nextLine();
                choice = "X";
            }
        }while(choice == "X");

        return choice;
    }

    /**
     * This method checks whether user input is valid or not.
     * @param lower all possible valid int inputs
     * @param upper all possible valid int inputs
     */
    private int takeInputRange(int lower, int upper) {
        int choice;

        do {
            try
            {
                choice = scan.nextInt();
                if(!isInputValid(lower, upper, choice))
                    throw new InputMismatchException();
            } catch(InputMismatchException e) {
                System.out.println("Error: Invalid input, input another distance");
                scan.nextLine();
                choice = -1;
            }
        }while(choice == -1);

        return choice;
    }

    /**
     * This method lets the user input a distance for the
     * {@link Edge's} between cities
     * @return
     */
    public int getDistance() {
        int distance = takeInputRange(0,1000);

        return distance;
    }

    /**
     * This method checks whether user input is valid or not.
     * @param inputs array of valid inputs
     * @param c the value the user inputted
     * @return boolean valid representing if the input is valid or not
     */
    private  boolean isInputValid(char inputs[], char c) {
        for(char i : inputs)
        {
            if(c == i)
                return true;
        }
        return false;
    }

    /**
     * This method checks whether user input is valid or not.
     * @param inputs array of valid inputs
     * @param c the value the user inputted
     * @return boolean valid representing if the input is valid or not
     */
    private  boolean isInputValid(ArrayList inputs, String c) {
        if(inputs.contains(c))
            return true;
        return false;
    }

    /**
     * This method handles exceptions for the user input.
     * returning false if the input is valid, and false
     * if it is invalid
     *
     * @param lower
     * @param upper
     * @return boolean
     */
    private  boolean isInputValid(int lower, int upper, double c) {
        if(lower <= c && c <= upper)
            return true;
        return false;
    }

}
