import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {
    int count = main.Count;
    int bars[] = main.Bars;
    int index = count + 1;
    boolean sorted = false;

    Color bg = Color.black;
    Color us = Color.white;
    Color s = Color.green;
    Color beam = new Color(255, 0, 0, 50);

    long n = (int) (1000000 * main.Millis);  // Number of nanoseconds I want to wait

    int width = main.WIDTH, height = main.HEIGHT;
    int w = (int) (width / count);

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(bg);

        g.setColor(Color.black);

        // Draw the rectangles based on the bars array
        for (int i = 0; i < count ; i++) {
            if (i <= index) g.setColor(s);
            else g.setColor(us);
            g.fillRect((w * i)   , height, w - 1, -bars[i]);

            if (i == index) {
                // Draw a beam to show what index it's looking at
                g.setColor(beam);

                g.fillRect(w * i, height, w - 1, -height);
            }
        }
    }

    public void selectionSort() {
        // Selection Sort
        int min;
        for (int i = 0; i < count; i++) {
            min = i;

            for (int j = i; j < count; j++) {
                if (bars[j] < bars[min]) min = j;
            }

            int temp = bars[min];
            bars[min] = bars[i];
            bars[i] = temp;

            if (i == (count - 1)) sorted = true;

            index = i;
            repaint();

            sleepFor(n);
        }
    }

    public void insertionSort() {
        int x = bars.length;
        for (int i = 1; i < x; i++) {
            int j = i - 1;
            int key = bars[i];

            while (j >= 0 && bars[j] > key) {
                bars[j + 1] = bars[j];
                j--;
            }
            index = i;
            bars[j + 1] = key;
            repaint();
            sleepFor(n);
        }
    }

    public void heapify(int bars[], int a, int i) {
        sleepFor(n);
        repaint();
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < a && bars[l] > bars[largest]) largest = l;

        if (r < a && bars[r] > bars[largest]) largest = r;

        if (largest != i) {
            int swap = bars[i];
            bars[i] = bars[largest];
            bars[largest] = swap;

            heapify(bars, a, largest);
        }

    }

    public void heapSort() {
        int n = bars.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(bars, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = bars[0];
            bars[0] = bars[i];
            bars[i] = temp;

            heapify(bars, i, 0);
        }
    }

    public void bubbleSort() {
        int b = bars.length;
        for (int i = 0; i < b - 1; i++) {
            for (int j = 0; j < b - i - 1; j++) {
                if (bars[j] > bars[j + 1]) {
                    int temp = bars[j];
                    bars[j] = bars[j + 1];
                    bars[j + 1] = temp;
                  }
                }
                repaint();
              sleepFor(n);
        }
    }

    public void shuffle(int bars[]) {
        for (int i = 0; i < bars.length; i++) {
            int index = (int) (Math.random() * bars.length);
            int temp = bars[index];
            bars[index] = bars[i];
            bars[i] = temp;
        }
    }

    int c = 0;

    public void bogoSort() {
        while (!sorted) {
            shuffle(bars);
            repaint();
            sleepFor(n);

            sorted = chekcIfSorted(bars);
            c++;
        }
         

        System.out.println(c);
    }

    public boolean chekcIfSorted(int bars[]) {
        for (int i = 0; i < bars.length - 1; i++) {
            if (bars[i] > bars[i + 1]) return false;
        }
        return true;
    }

    // public void gnomeSort() {
    //     int i = 0;
    //     while (i < bars.length) {
    //         if (i == 0) i++;
    //         if (bars[i] >= bars[i - 1]) i++;
    //         else {
    //             int temp = 0;
    //             temp = bars[i];
    //             bars[i] = bars[i - 1];
    //             bars[i - 1] = temp;
    //             i--;
    //         }
    //         repaint();
    //         sleepFor(n);
    //     }
    // }

    // public void timSort() {
    //     int n = bars.length;
    //     int RUN = 32;
    //     for (int i = 0; i < n; i += RUN) {
    //         insertionSort(i, Math.min((i + 31), (n - 1)));
    //     }

    //     for (int size = RUN; size < n; size = 2 * size) {
    //         for (int left = 0; left < n; left += 2 * size) {
    //             int mid = left + size - 1;
    //             int right = Math.min((left + 2 * size - 1), (n - 1));
    //             merge(left, mid, right);
    //         }
    //     }
    // }

    // public void insertionSort(int left, int right) {
    //     for (int i = left + 1; i <= right; i++) {
    //         int temp = bars[i];
    //         int j = i - 1;
    //         while (j >= left && bars[j] > temp) {
    //             bars[j + 1] = bars[j];
    //             j--;
    //         }
    //         bars[j + 1] = temp;
    //     }
    // }

    // public void merge(int l, int m, int r) {
    //     int len1 = m - l + 1, len2 = r - m;
    //     int[] left = new int[len1];
    //     int[] right = new int[len2];
    //     for (int x = 0; x < len1; x++) {
    //         left[x] = bars[l + x];
    //     }
    //     for (int x = 0; x < len2; x++) {
    //         right[x] = bars[m + 1 + x];
    //     }

    //     int i = 0;
    //     int j = 0;
    //     int k = l;

    //     while (i < len1 && j < len2) {
    //         if (left[i] <= right[j]) {
    //             bars[k] = left[i];
    //             i++;
    //         } else {
    //             bars[k] = right[j];
    //             j++;
    //         }
    //         k++;
    //     }

    //     while (i < len1) {
    //         bars[k] = left[i];
    //         k++;
    //         i++;
    //     }

    //     while (j < len2) {
    //         bars[k] = right[j];
    //         k++;
    //         j++;
    //     }
    // }

    public void sleepFor(long nanoseconds) {
        long timeElapsed;
        final long startTime = System.nanoTime();
        do {
            timeElapsed = System.nanoTime() - startTime;
        } while (timeElapsed < nanoseconds);
    }
}