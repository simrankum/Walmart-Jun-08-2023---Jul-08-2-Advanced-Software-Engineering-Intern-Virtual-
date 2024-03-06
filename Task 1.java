import java.util.Arrays;

public class PowerOfTwoMaxHeap {
    private int[] heapArray;
    private int size;
    private int numChildrenPerParent;

    public PowerOfTwoMaxHeap(int numChildrenPerParent) {
        this.numChildrenPerParent = numChildrenPerParent;
        this.heapArray = new int[1]; // Initial size of the heap array
        this.size = 0;
    }

    public void insert(int value) {
        if (size == heapArray.length) {
            // Double the array size if it's full
            heapArray = Arrays.copyOf(heapArray, size * 2);
        }

        heapArray[size++] = value;
        heapifyUp(size - 1);
    }

    public int popMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty.");
        }

        int max = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    private void heapifyUp(int index) {S
        int parentIndex = (index - 1) / numChildrenPerParent;

        if (index > 0 && heapArray[index] > heapArray[parentIndex]) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int maxChildIndex = getMaxChildIndex(index);

        if (maxChildIndex != -1 && heapArray[maxChildIndex] > heapArray[index]) {
            swap(index, maxChildIndex);
            heapifyDown(maxChildIndex);
        }
    }

    private int getMaxChildIndex(int index) {
        int maxChildIndex = -1;
        int startChildIndex = index * numChildrenPerParent + 1;
        int endChildIndex = Math.min(startChildIndex + numChildrenPerParent, size);

        for (int i = startChildIndex; i < endChildIndex; i++) {
            if (maxChildIndex == -1 || heapArray[i] > heapArray[maxChildIndex]) {
                maxChildIndex = i;
            }
        }

        return maxChildIndex;
    }

    private void swap(int i, int j) {
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }
}
