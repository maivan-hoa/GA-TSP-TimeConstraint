import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA {
    Random rd = Main.rand;
    Population population;
    double pOfMutation; // ngưỡng xác suất để lai ghép hoặc đột biến

    public GA(int numOfInd){
        population = new Population(numOfInd);
    }

    public void run(int nN){ //nN số lần thực hiện lai ghép và đột biến
        population.init();
        ArrayList<Individual> bestSolution = new ArrayList<>();
        List<Individual> individuals = population.getIndividuals();
        List<Individual> children = new ArrayList<>();

        for(int i=0; i<nN; i++){
            Individual a = individuals.get(rd.nextInt(individuals.size()));
            Individual b = individuals.get(rd.nextInt(individuals.size()));

            while(a==b){
                b = individuals.get(rd.nextInt(individuals.size()));
            }

            double p = rd.nextDouble();
            if(p > pOfMutation){
                children.addAll(crossOver(a, b));
            }
            else{
                Individual ia = mutation(a);
                Individual ib = mutation(b);
                children.add(ia);
                children.add(ib);
            }

            population.add(children);
            selection(); // thực hiện chọn lọc
        }
    }

    ArrayList<Individual> crossOver(Individual a, Individual b){

    }

    Individual mutation(Individual a){

    }

    void selection(){

    }


}
