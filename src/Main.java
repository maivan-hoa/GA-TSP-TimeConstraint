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
    static int sizePopulation;
    static int ITERATIONs;
    static double pOfMutation;
    static int nN;

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "rc_204.1.txt";
        readData(filename);

        sizePopulation = 2000;
        ITERATIONs = 2000;  //số thế hệ
        nN = 2000;          //số lần lai ghép đột biến
        pOfMutation= 0.5;   //xác suất lai ghép

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

    public static int checkGenValid(ArrayList<Integer> gen){ // kiểm tra gen kết quả có hợp lệ với ràng buộc thời gian
        double sumTime = 0.0;
        for(int i=1; i<numCity; i++){
            sumTime += graph[gen.get(i-1)][gen.get(i)];
            if(sumTime < timeConstraint[gen.get(i)][0]){
                sumTime = timeConstraint[gen.get(i)][0];
            }
            if(sumTime > timeConstraint[gen.get(i)][1]){
                return 0;
            }
        }

        sumTime += graph[gen.get(numCity-1)][0];
        if(sumTime > timeConstraint[0][1]){
            return 0;
        }
        return 1;
    }

}
