package TP1;

public class Exercise1_1 {
    public static int findLocalMinimum(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if ((mid == 0 || arr[mid - 1] > arr[mid]) &&
                    (mid == arr.length - 1 || arr[mid + 1] > arr[mid])) {
                return mid;     // Local minimum found
            } else if (mid > 0 && arr[mid - 1] < arr[mid]) {
                high = mid - 1; // Move left
            } else {
                low = mid + 1;  // Move right
            }
        }
        return low; // Return the index of the local minimum
    }

    public static void main(String[] args) {
        int[] arr = {9, 7, 3, 8, 5, 6, 2};
        int index = findLocalMinimum(arr);

        System.out.println("Local minimum found at index: " + index);
        System.out.println("Value: " + arr[index]);
    }
}