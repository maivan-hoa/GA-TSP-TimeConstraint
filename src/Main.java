import com.sun.javaws.IconUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Double[][] graph;
    static Double[][] timeConstraint;
    static int numCity;

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "rc_201.3.txt";
        readData(filename);

        int sizePopulation = 10*Main.numCity;
        int ITERATIONs = 1000; //số thế hệ
        int nN = 100; //số lần lai ghép đột biến
        double pOfMutation= 0.5; //xác suất lai ghép

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

        timeConstraint = new Double[numCity][2];
        for(int i=0; i<numCity; i++){
            str = input.nextLine();
            str = str.replaceAll(" +", " ");
            String[] time = str.split(" ");
            timeConstraint[i][0] = Double.parseDouble(time[0]);
            timeConstraint[i][1] = Double.parseDouble(time[1]);
        }
    }


}
