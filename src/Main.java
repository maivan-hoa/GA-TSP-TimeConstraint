import com.sun.javaws.IconUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Double[][] graph;
    static Double[][] timeConstrain;
    static int numCity;
    static Random rand;

    public static void main(String[] args) throws FileNotFoundException {
        int seed = 1;
        rand = new Random(seed);
        String filename = "rc_201.1.txt";
        readData(filename);

        int sizePopulation = 100;
        int ITERATIONs = 100; //số thế hệ
        int nN = 50;
        double pOfMutation= 0.3;

        GA ga = new GA(sizePopulation, ITERATIONs, pOfMutation);
        ga.run(nN);

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
