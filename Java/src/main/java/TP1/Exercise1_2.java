package TP1;

public class Exercise1_2 {
    public static boolean searchInBitonicArray(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int peak = findPeak(arr);

        // Search in increasing part
        int index = binarySearch(arr, 0, peak, target, true);
        if (index != -1) {
            return true;
        }

        // Search in decreasing part
        return binarySearch(arr, peak + 1, arr.length - 1, target, false) != -1;
    }

    private static int findPeak(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            int mid = Math.floorDiv(low + high, 2);

            if (arr[mid] > arr[mid + 1]) {
                high = mid;     // Left or mid
            } else {
                low = mid + 1;  // Right
            }
        }
        return low; // Peak index
    }

    private static int binarySearch(int[] arr, int low, int high, int target, boolean ascending) {
        while (low <= high) {
            int mid = Math.floorDiv(low + high, 2);

            if (arr[mid] == target) {
                return mid;
            }

            if (ascending) {
                if (arr[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (arr[mid] > target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 12, 4, 2};
        int target = 4;
        boolean found = searchInBitonicArray(arr, target);
        System.out.println("Target found: " + found);
    }
}