import java.util.Random;

/**
 * Created by travis on 2/5/17.
 */
public class main {
    static final int iterations = 20;
    static final int heapSize = 100;

    public static void main(String[] args) {
        UI userinterface = new UI();
        boolean done = false;

        do {
            int choice = userinterface.askChoice();

            switch (choice) {
                case 1: {
                    int[] numArray = populateArray();
                    Heap randomHeap = new Heap();

                    for(int i = 0; i < numArray.length; i++)
                        randomHeap.heap[i] = numArray[i];

                    randomHeap = randomHeap.worstCaseSort();
                    int average2 = randomHeap.counter;

                    randomHeap.setCounter(0);

                    // efficient sort
                    randomHeap.efficientSort();
                    int average = randomHeap.counter;

                    System.out.println("Average swaps for series of insertions: " + average2);
                    System.out.println("Average swaps for optimal method: " + average);
                    break;
                }
                case 2: {
                    int[] numArray = generateNaturalNumbers();
                    Heap intHeap = new Heap();

                    for(int i = 0; i < numArray.length; i++)
                        intHeap.add(numArray[i]);

                    // insertion sort
                    int average = intHeap.counter;

                    intHeap.setCounter(0);

                    // efficient sort
                    Heap efficientSortHeap = intHeap.efficientSort();

                    int average2 = intHeap.counter;

                    System.out.print("Heap built using series of insertions: ");
                    intHeap.printHeap();
                    System.out.println("Number of swaps: " + average);
                    for(int i = 0; i < 9; i++){intHeap.removeMax();}
                    System.out.print("Heap after 10 removals: ");
                    intHeap.printHeap();

                    System.out.println();

                    System.out.print("Heap built using optimal method: ");
                    efficientSortHeap.printHeap();
                    System.out.println("Number of swaps: " + average2);
                    intHeap.remove();
                    System.out.print("Heap after 10 removals: ");
                    efficientSortHeap.printHeap();
                    break;
                }
                case 0:
                    done = true;
            }
        } while (!done);
    }

    private static int[] generateNaturalNumbers() {
        int[] newHeap = new int[heapSize];

        for (int i = 0; i < newHeap.length; i++)
            newHeap[i] = i+1;

        return newHeap;
    }

    /**
     * This method generates unique random integers
     * between 1-100 and puts them in the array
     */
    private static int[] populateArray() {
        int[] randomArray = new int[heapSize];
        Random rand = new Random();
        int counter = 0;

        do {
            int temp = rand.nextInt(99 )+1;
            boolean isfound = false;

            for(int i = 0; i< randomArray.length; i++)
            {
                if (randomArray[i] == temp)
                    isfound = true;
            }

                if(!isfound)
                {
                   randomArray[counter] = temp;
                   counter++;
                }
        } while (counter < 99);

        return randomArray;
    }
}
