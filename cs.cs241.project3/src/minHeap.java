import java.util.Arrays;
import java.util.Random;

/**
 * Created by travis on 2/5/17.
 */
public class minHeap {

    int[] heap;
    int lastElement;
    static final int heapSize = 100;
    int counter = 0;
    int iterations = 20;

    public minHeap() {
        int[] temp = new int[heapSize];
        heap = temp;
    }

    /**
     * This constructor creates a heap efficiently
     * using the reheap function.
     *
     */
    public minHeap(int size){
        int[] temp = new int[size + 1];
        heap = temp;
        lastElement = 0;

    }

    /**
     * swaps the two elements, using a temp
     * variable to hold a value for the swap
     *
     * @param sequence
     * @param i
     * @param j
     */
    private void swap(int[] sequence, int i, int j) {
        int temp = sequence[i];
        sequence[i] = sequence[j];
        sequence[j] = temp;
    }

    public void add(int newvalue){
        lastElement++;
        ensureCapacity();
        heap[0] = newvalue;
        int nextElement = lastElement;
        int parent = nextElement / 2;
        int compare = newvalue - heap[parent];

        while((parent > 0) && compare > 0)
        {
            heap[nextElement] = heap[parent];
            nextElement = parent;
            parent = nextElement / 2;
        }

        heap[nextElement] = newvalue;
        this.counter += reheap(heap,0, this.lastElement);
    }

    private void ensureCapacity() {
        if(lastElement >= heap.length)
            heap = Arrays.copyOf(heap, 2* heap.length);
    }

    public int remove(){
        int root = 0;

        if(isempty())
        {
            root = heap[1];
            heap[1] = heap[lastElement];
            lastElement--;
            reheap(1);
        }
        return root;
    }

    /**
     * This function takes a tree with the index of i
     * and makes it a heap
     *
     * @param i
     */
    public int reheap(int i){
        boolean done = false;
        int temp = heap[i];
        int leftChild = 2 * i;

        while (!done && (leftChild <= lastElement))
        {
            int larger = leftChild;
            int rightChild = leftChild + 1;
            int compare = heap[rightChild] - heap[leftChild];
            if( (rightChild <= lastElement) && compare > 0)
            {
                larger = rightChild;
            }

            if(temp - heap[larger] < 0)
            {
                heap[i] = heap[larger];
                i = larger;
                leftChild = 2 *i;
                counter++;
            }
            else
            {
                done = true;
            }
        }
        heap[i] = temp;

        return counter;
    }

    public int reheap(int[] heap, int i, int lastElement){
        boolean done = false;
        int temp = heap[i];
        int leftChild = 2*i+1;
        int counter = 0;

        while(!done && (leftChild <= lastElement))
        {
            int larger = leftChild;
            int rightChild = leftChild + 1;

            if( rightChild <= lastElement)
            {
                if((heap[rightChild] - (heap[larger])) < 0)
                {
                    larger = rightChild;
                    counter++;
                }
            }

            if(temp - larger < 0)
            {
                heap[i] = heap[larger];
                i = larger;
                leftChild = 2*i+1;
                counter++;
            }
            else
            {
                done = true;
                counter++;
            }
        }
        heap[i] = temp;

        return counter;
    }

    public minHeap heapSort(int[] sequence, int n) {

        for(int i = ((n/2) - 1); i >= 0; i--)
        {
            int j = reheap(sequence, i, n - 1);
            counter = counter + j;
        }

        swap(sequence, 0, n - 1);

        for(int lastElement = n - 2; lastElement > 0; lastElement--)
        {
            reheap(sequence, 0, lastElement);
            swap(sequence, 0, lastElement);
        }

        return this;
    }

    public int getroot(){
        int root = 0;

        if(!isempty())
            root = heap[1];
        return root;
    }

    public boolean isempty(){
        return lastElement < 1;
    }

    public int getSize(){
        return heapSize;
    }

    private boolean checkIndex(int[] rand, int randomInt) {

        boolean isinSequence = false;

        for(int index : rand)
        {
            if(rand[index] == randomInt)
            {
                isinSequence = true;
            }
            index++;
        }

        return isinSequence;
    }

    public void clear() {
        while(lastElement > -1)
        {
            heap[lastElement] = 0;
            lastElement--;
        }
        lastElement = 0;
    }

    public void printHeap() {
        for(int i = 0; i < 9; i++)
        {
            System.out.print(this.heap[i]);
            System.out.print(",");
        }
        System.out.println("...");
    }

    public minHeap worstCaseSort() {
        minHeap sortedHeap = new minHeap();

        for(int k = 0; k < iterations; k++)
        {
            for(int o: heap)
            {
                sortedHeap.add(o);
            }

            this.clear();
        }

        sortedHeap.counter = (sortedHeap.counter/iterations);
        return sortedHeap;
    }

    public minHeap efficientSort() {
        int[] temp = new int[heapSize];
        minHeap sortedHeap = new minHeap();

        for(int j = 0; j< iterations; j++)
        {
            for(int i = 0; i < heapSize; i++)
            {
                Random rand = new Random();
                temp[i] = rand.nextInt(99+1);
            }
            sortedHeap = this.heapSort(temp, heapSize);
        }

        this.counter = (this.counter/iterations);
        return sortedHeap;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int removeMin() {
        int root = heap[0];
        heap[0] = heap[lastElement];
        lastElement--;
        reheap(0);
        return root;
    }
}
