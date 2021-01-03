package xyz.zerxoi.basic;

public class GenericMethodDemo {
    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6,7};
        String[] strs = {"a", "b", "c", "d"};
        printArray(arr);
        printArray(strs);
    }

    static <T> void printArray(T[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}

