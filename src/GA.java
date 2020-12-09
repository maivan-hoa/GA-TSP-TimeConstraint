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

            while(a==b){ // == so sánh địa chỉ object trên memory
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
        ArrayList<Individual> children = new ArrayList<>();

        int t = rd.nextInt(a.getGene().size()-1);

        ArrayList<Integer> ca = new ArrayList<>();
        ArrayList<Integer> cb = new ArrayList<>();
        for(int i=0; i<t; i++){
            ca.add(a.getGene().get(i));
            cb.add(b.getGene().get(i));
        }

        for(int i=t; i<a.getGene().size(); i++){
            ca.add(b.getGene().get(i));
            cb.add(a.getGene().get(i));
        }

        if(!checkIndividualValid(ca)){
            makeIndividualValid(ca);
        }

        if(!checkIndividualValid(cb)){
            makeIndividualValid(cb);
        }

        Individual ind1 = new Individual(ca);
        Individual ind2 = new Individual(cb);

        children.add(ind1);
        children.add(ind2);

        return children;
    }

    Individual mutation(Individual a){
        int t1 = rd.nextInt(a.getGene().size());
        while(t1!=0){
            t1 = rd.nextInt(a.getGene().size());
        }

        int t2 = rd.nextInt(a.getGene().size());
        while(t2!=0 && t2!=t1){
            t2 = rd.nextInt(a.getGene().size());
        }

        ArrayList<Integer> ca = new ArrayList<>(a.getGene());
        ca.set(t1, a.getGene().get(t2));
        ca.set(t2, a.getGene().get(t1));

        Individual ind = new Individual(ca);
        return ind;
    }

    void selection(){

    }

    boolean checkIndividualValid(ArrayList<Integer> a){
        return true;
    }

    void makeIndividualValid(ArrayList<Integer> a){

    }

}
