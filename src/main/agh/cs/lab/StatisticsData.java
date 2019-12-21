package agh.cs.lab;

import java.util.HashMap;
import java.util.Map;

public class StatisticsData {
    private int howManyAnimals[];
    private int howManyPlants[];
    Genotype dominantGenotype[];
    private int mediumEnergy[];
    private int mediumLifeLength[];
    private int mediumChildAmount[];

    StatisticsData(Day day){
        howManyAnimals = new int [day.configuration.timeRunning+1];
        howManyAnimals [0] = day.getHowManyAnimals();
        howManyPlants = new int [day.configuration.timeRunning+1];
        howManyPlants [0] = day.getHowManyPlants();
        dominantGenotype = new Genotype[day.configuration.timeRunning+1];
        dominantGenotype [0] = day.getMostFrequent();
        mediumEnergy = new int [day.configuration.timeRunning+1];
        mediumEnergy [0] = day.getMediumEnergyLevel();
        mediumLifeLength = new int[day.configuration.timeRunning+1];
        mediumLifeLength[0] = day.getMediumLifeLength();
        mediumChildAmount = new int [day.configuration.timeRunning+1];
        mediumChildAmount [0] = day.getMediumAmountOfChildren();
    }

    void update(Day day){
        howManyAnimals [day.getDayNumber()] = day.getHowManyAnimals();
        howManyPlants [day.getDayNumber()] = day.getHowManyPlants();
        dominantGenotype [day.getDayNumber()] = day.getMostFrequent();
        mediumEnergy [day.getDayNumber()] = day.getMediumEnergyLevel();
        mediumLifeLength[day.getDayNumber()] = day.getMediumLifeLength();
        mediumChildAmount [day.getDayNumber()] = day.getMediumAmountOfChildren();
    }

    private int getMediumHowManyAnimals(){
        int amount = 0;
        for (int i=0; i< this.howManyAnimals.length; i++){
            amount +=this.howManyAnimals[i];
        }
        return (amount/this.howManyAnimals.length);
    }

    private int getMediumHowManyPlants(){
        int amount = 0;
        for (int i=0; i< this.howManyPlants.length; i++){
            amount +=this.howManyPlants[i];
        }
        return (amount/this.howManyPlants.length);
    }

    private int getMediumEnergyLevel(){
        int amount = 0;
        for (int i=0; i< this.mediumEnergy.length; i++){
            amount +=this.mediumEnergy[i];
        }
        return (amount/this.mediumEnergy.length);
    }

    private int getMediumLifeLength(){
        int amount = 0;
        int counter = 0;
        for (int i=0; i< this.mediumLifeLength.length; i++){
            if (this.mediumLifeLength[i]!= 0) {
                amount += this.mediumLifeLength[i];
                counter++;
            }
        }
        return (amount/counter);
    }

    private int getMediumAmountOfChildren(){
        int amount = 0;
        for (int i=0; i< this.mediumChildAmount.length; i++){
            amount +=this.mediumChildAmount[i];
        }
        return (amount/this.mediumChildAmount.length);
    }


    private Genotype getMostFrequentGenotype(){
        Map<Genotype, Integer> hp =
                new HashMap<Genotype, Integer>();

        for(int i = 0; i < this.dominantGenotype.length; i++)
        {
            Genotype key = this.dominantGenotype[i];
            if(hp.containsKey(key))
            {
                int freq = hp.get(key);
                freq++;
                hp.put(key, freq);
            }
            else
            {
                hp.put(key, 1);
            }
        }

        int max_count = 0;
        Genotype res = null;

        for(Map.Entry<Genotype, Integer> val : hp.entrySet())
        {
            if (max_count < val.getValue())
            {
                res = val.getKey();
                max_count = val.getValue();
            }
        }


        return res;

    }

    public String toString() {
        return "StatisticsData:"+"\n"+
                "medium amount of animals = " + this.getMediumHowManyAnimals()+"," +"\n"+
                "medium amount of plants =" + this.getMediumHowManyPlants()+"," +"\n"+
                "medium energy level =" + this.getMediumEnergyLevel()+"," +"\n"+
                "most frequent genotype =" +this.getMostFrequentGenotype().toString() +"\n" +
                "medium life length of animal =" + this.getMediumLifeLength()+"," +"\n"+
                "medium amount of children =" + this.getMediumAmountOfChildren() + ".";
    }
}
