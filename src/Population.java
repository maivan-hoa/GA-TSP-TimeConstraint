import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population {
    int sizePopulation; // so luong ca the trong quan the
    ArrayList<Individual> individuals = new ArrayList<>(); // lưu các cá thể trong quần thể

    Population(int n){
        this.sizePopulation = n; // số lượng cá thể trong quần thể
    }

    public void init(){

        for(int i=0; i< sizePopulation; i++){
            ArrayList<Integer> gene = new ArrayList<>();
            for(int j=1; j<Main.numCity; j++){
                gene.add(j);
            }
            Collections.shuffle(gene);
            gene.add(0, 0);
            Individual ind = new Individual(gene);
            individuals.add(ind);
        }
    }

    public ArrayList<Individual> getIndividuals(){
        return individuals;
    }

    void setIndividuals(ArrayList<Individual> individuals){
        this.individuals = individuals;
    }

    void add(List<Individual> offsprings){  //thêm các cá thể con mới tạo ra vào quần thể
        individuals.addAll(offsprings);
    }

    Individual getBestIndividual(){
        ArrayList<Individual> individualsort = new ArrayList<>(individuals);
        individualsort.sort((i1, i2) -> {
            Double di1 = i1.getFitness();
            Double di2 = i2.getFitness();
            return di1.compareTo(di2);
        });

        return individualsort.get(0);
    }
}
