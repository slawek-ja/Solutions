package pl.coderstrust.sort;

public class BubbleSort {
    public static int[] sort(int[] array) {
        int[] tab = java.util.Arrays.copyOf(array, array.length);
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length - 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    swap(tab, j, j + 1);
                }
            }
        }
        return tab;
    }

    private static void swap(int tabA[], int a, int b) {
        int buff = 0;
        buff = tabA[a];
        tabA[a] = tabA[b];
        tabA[b] = buff;
    }
}
