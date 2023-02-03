import javax.swing.*;
import java.util.Random;

import java.awt.*;

public class main {
    public static int Count, WIDTH, HEIGHT, Millis;
    public static int[] Bars;
    
    // ~~~ OPTIONAL SETTINGS ~~~ \\
    // 0 = Selection Sort, 1 = Insertion Sort, 2 = Heap Sort, 3 = Bubble Sort, 4 = Bogosort (NOT RECOMMENDED)
    public static int SortingMethod = 0; 

    // 0 = Slow, 1 = Medium, 2 = Fast
    public static int Speed = 3;
    
    // 0 = Small, 1 = Medium, 2 = Large 3 = Max
    public static int Size = 0; 
    
    // ~~~ END OF OPTIONAL SETTINGS ~~~ \\
    // ~~~ DO NOT EDIT BELOW THIS LINE ~~~ \\
    
    public static Random rand;
    static int FPS;    // > 1000 runs instantly;
    

    public static void main(String[] args) {
        init();
        makeBars(Count);
        initFrame();
    }

    public static void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.width / 2;
        HEIGHT = 720;

        // Change frame rate based on speed
        switch(Speed) {
            case 0: 
                FPS = 30;
                break;
            case 1:
                FPS = 60;
                break;
            case 2:
                FPS = 120;
                break;
            default: 
                System.out.println("Invalid speed, defaulting to medium");
                FPS = 60;
                break;
        }
  
        // Count is the number of bars
        switch(Size) {
            case 0:
                Count = WIDTH / 16;
                break;
            case 1:
                Count = WIDTH / 8;
                break;
            case 2:
                Count = WIDTH / 4;
                break;
            case 3:
                Count = WIDTH / 2;
                break;
            default: 
                System.out.println("Invalid size, defaulting to medium");
                Count = WIDTH / 8;
                break;
        }

        // Milliseconds to wait between each frame
        Millis = Math.floorDiv(1000, FPS);

        Bars = new int[Count];
        rand = new Random();
    }

    public static void initFrame() {
        // FRAME DETAILS
        Draw draw = new Draw();
        JFrame frame = new JFrame("Sorting Program");

        frame.add(draw);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        System.out.println("Sorting...");

        // Select your sorting method here

        switch(SortingMethod) {
            case 0:
                draw.selectionSort();
                break;
            case 1:
                draw.insertionSort();
                break;
            case 2:
                draw.heapSort();
                break;
            case 3:
                draw.bubbleSort();
                break;
            case 4:
                draw.bogoSort();
                break;
            default:
                System.out.println("Invalid sorting method");
                break;
        }
        
        System.out.println("Done!");
    }

    public static void makeBars(int c) {
        for (int i = 0; i < c; i++) {
            Bars[i] = rand.nextInt(HEIGHT - (int) HEIGHT / 5) + (int) (HEIGHT / 5);
        }
    }
}