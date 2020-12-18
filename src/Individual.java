import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual {
    ArrayList<Integer> gene;
    double fitness;

    Individual(ArrayList<Integer> gene){
        this.gene = gene;
        setFitness();
    }

    public void setFitness(int a){
        fitness = 0.0;
        for(int i=0; i< Main.numCity-1; i++){
            fitness += Main.graph[gene.get(i)][gene.get(i+1)];
        }

        fitness += Main.graph[gene.get(Main.numCity-1)][0];
    }

    // fitness with time constraint
    public void setFitness(){
        fitness = 0.0; // có cộng thêm độ phạt
        double sumTime = 0.0; // tổng thời gian đã đi, không cộng thêm phạt

        for(int i=1; i< Main.numCity; i++){
            fitness += Main.graph[gene.get(i-1)][gene.get(i)];
            sumTime += Main.graph[gene.get(i-1)][gene.get(i)];

            if(sumTime < Main.timeConstraint[gene.get(i)][0]){
                double t = Main.timeConstraint[gene.get(i)][0] - sumTime;
                sumTime += t;
                fitness += t;
            }

            if(sumTime > Main.timeConstraint[gene.get(i)][1]){
                fitness += 2*Math.pow(sumTime-Main.timeConstraint[gene.get(i)][1], 2);
            }
        }

        // tính toán khi quay về 0
        fitness += Main.graph[gene.get(Main.numCity-1)][0];
        sumTime += Main.graph[gene.get(Main.numCity-1)][0];

        if(sumTime > Main.timeConstraint[0][1]){
            fitness += Math.pow(sumTime-Main.timeConstraint[0][1], 2);
        }

    }

    public double getFitness(){
        return fitness;
    }

    public ArrayList<Integer> getGene(){
        return gene;
    }
}
