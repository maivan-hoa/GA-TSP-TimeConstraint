import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Individual {
    Random rd = Main.rand;
    ArrayList<Integer> gene = new ArrayList<>();
    double fitness;

    public void initGene(){
        for(int i=1; i<Main.numCity; i++){
            gene.add(i);
        }
        Collections.shuffle(gene);
        gene.add(0, 0);
    }

    public void setFitness(){
        fitness = 0.0;
        for(int i=0; i< Main.numCity-1; i++){
            fitness += Main.graph[gene.get(i)][gene.get(i+1)];
        }

        fitness += Main.graph[gene.get(Main.numCity-1)][0];
    }

    public ArrayList<Integer> getGene(){
        return gene;
    }
}
