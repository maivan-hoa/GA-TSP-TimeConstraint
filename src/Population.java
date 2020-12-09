import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
    int sizePopulation; // so luong ca the trong quan the
    Random rand = Main.rand;
    ArrayList<Individual> individuals = new ArrayList<>(); // lưu các cá thể trong quần thể

    Population(int n){
        this.sizePopulation = n; // số lượng cá thể trong quần thể
    }

    public void init(){
        for(int i=0; i< sizePopulation; i++){
            Individual ind = new Individual();
            ind.initGene();
            ind.setFitness();
            individuals.add(ind);
        }
    }

    public ArrayList<Individual> getIndividuals(){
        return individuals;
    }

    void add(List<Individual> offsprings){  //thêm các cá thể con mới tạo ra vào quần thể
        individuals.addAll(offsprings);
    }
}
