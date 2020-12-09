import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //static int sizePopulation;
    static Double[][] graph;
    static Population population;
    static int numOfInd;
    static Double[][] timeConstrain;
    static int numCity;
    static Random rand;

    public static void main(String[] args) throws FileNotFoundException {
        int seed = 1;
        rand = new Random(seed);
        String filename = "rc_201.1.txt";
        readData(filename);
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=1;i<10;i++){
            a.add(i);
        }

        ArrayList<Integer> b = new ArrayList<>(a);
        b.set(0, 1);
        System.out.println(a);
        System.out.println(b==a);

    }

    public static void readData(String filename) throws FileNotFoundException {
        File file = new File("D:\\20201\\Evolutionary Computing\\SolomonPotvinBengio\\"+ filename);
        Scanner input = new Scanner(file);
        numCity = Integer.parseInt(input.nextLine());

        graph = new Double[numCity][numCity];
        String str;
        for(int i=0; i<numCity; i++){
            str = input.nextLine();
            String[] dist = str.split(" ");
            for(int j=0; j<numCity; j++){
                graph[i][j] = Double.parseDouble(dist[j]);
            }
        }

        timeConstrain = new Double[numCity][2];
        for(int i=0; i<numCity; i++){
            str = input.nextLine();
            str = str.replaceAll(" +", " ");
            String[] time = str.split(" ");
            timeConstrain[i][0] = Double.parseDouble(time[0]);
            timeConstrain[i][1] = Double.parseDouble(time[1]);
        }
    }
}
