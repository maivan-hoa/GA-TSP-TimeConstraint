import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA {
    Random rd = new Random();
    Population population;
    double pOfMutation; // ngưỡng xác suất để lai ghép hoặc đột biến
    int ITERATIONs;
    Individual bestSolution;
    int timeReset = 50;

    public GA(int sizePopulation, int iterations, double pOfMutation){
        population = new Population(sizePopulation);
        ITERATIONs = iterations;
        this.pOfMutation = pOfMutation;
    }

    public void run(int nN){ //nN số lần thực hiện lai ghép và đột biến
        population.init();
        //bestSolution = population.getBestIndividual(); //lấy ngẫu nhiên một cá thể coi như tốt nhất
        bestSolution = population.getIndividuals().get(0);
        int changeBest = 0; // Nếu sau timeReset thế hệ mà bestSolution không đổi thì khởi tạo lại quần thể

        for(int iter = 0; iter<ITERATIONs; iter++){
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
            }

            population.add(children);
            Individual bestInter = selection(); // thực hiện chọn lọc

            if(bestInter.getFitness() < bestSolution.getFitness()){
                bestSolution = bestInter;
                changeBest = 0;
            }

            changeBest++;
            if(changeBest > timeReset){
                population.init();
                changeBest = 0;
            }

            System.out.println("Thế hệ: " + (iter+1));
            System.out.println("Best Fitness: " + bestSolution.getFitness());
            System.out.println("Gen: " + bestSolution.getGene());
            System.out.println("--------------------------------------------------------");
        }

    }

    ArrayList<Individual> crossOver(Individual a, Individual b){
        ArrayList<Individual> children = new ArrayList<>();
        ArrayList<Integer> ca = new ArrayList<>();
        ArrayList<Integer> cb = new ArrayList<>();
        //chọn một điểm cắt trên gen
        /*
        int t = rd.nextInt(a.getGene().size()-1);
        for(int i=0; i<t; i++){
            ca.add(a.getGene().get(i));
            cb.add(b.getGene().get(i));
        }

        for(int i=t; i<a.getGene().size(); i++){
            ca.add(b.getGene().get(i));
            cb.add(a.getGene().get(i));
        }
        */

        // chọn hai điểm cắt
        int m1, m2;
        do{
            m1 = rd.nextInt(a.getGene().size());
            m2 = rd.nextInt(a.getGene().size());
        }while (m1>m2 || m1==m2);

        for(int i=0; i<m1; i++){
            ca.add(b.getGene().get(i));
            cb.add(a.getGene().get(i));
        }

        for(int i=m1; i<=m2; i++){
            ca.add(a.getGene().get(i));
            cb.add(b.getGene().get(i));
        }

        for(int i=m2+1; i<a.getGene().size(); i++){
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
        int t1;

        do{
            t1 = rd.nextInt(a.getGene().size());
        }
        while(t1 == 0);

        int t2;
        do{
            t2 = rd.nextInt(a.getGene().size());
        }
        while(t2 ==0 || t2 == t1);

        ArrayList<Integer> ca = new ArrayList<>(a.getGene());
        ca.set(t1, a.getGene().get(t2));
        ca.set(t2, a.getGene().get(t1));

        Individual ind = new Individual(ca);
        return ind;
    }

    Individual selection(){
        population.getIndividuals().sort((i1, i2) -> {
           Double di1 = i1.getFitness();
           Double di2 = i2.getFitness();
           return di1.compareTo(di2);
        });

        ArrayList<Individual> newIndividuals = new ArrayList<>();
        int l = population.getIndividuals().size()-1;
        for(int i=0; i<population.sizePopulation; i++){
            newIndividuals.add(population.getIndividuals().get(i));
            //newIndividuals.add(population.getIndividuals().get(l-i));
        }

        population.setIndividuals(newIndividuals);

        return population.getIndividuals().get(0);
    }

    boolean checkIndividualValid(ArrayList<Integer> a){
        for(int i=0; i<a.size()-1; i++){
            for(int j=i+1; j<a.size(); j++){
                if(a.get(i) == a.get(j)){
                    return false;
                }
            }
        }

        return true;
    }

    void makeIndividualValid(ArrayList<Integer> a){
        ArrayList<Integer> check = new ArrayList<>();
        for(int i=0; i<a.size(); i++){
            check.add(i);
        }

        for(int i=0; i<a.size(); i++){
            boolean ok = true;
            for(int j=0; j<i; j++){
                if(a.get(i) == a.get(j)){
                    ok = false;
                    break;
                }
            }
            if(ok){
                check.remove(check.indexOf(a.get(i)));
            }
        }

        Collections.shuffle(check);
        for(int i=0; i<a.size(); i++){
            for(int j=0; j<i; j++){
                if(a.get(i) == a.get(j)){
                    a.set(i, check.get(0));
                    check.remove(0);
                }
            }
        }
    }

}
