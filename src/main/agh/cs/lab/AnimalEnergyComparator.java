package agh.cs.lab;

import java.util.Comparator;

public class AnimalEnergyComparator implements Comparator {

    public int compare(Object original, Object comparable){
        if (original == comparable) return 0;
        if (!(original instanceof Animal || comparable instanceof Animal))
            return 0;
        Animal first = (Animal) original;
        Animal second = (Animal) comparable;
        if (first.energy == second.energy)
            return 0;
        return (first.energy > second.energy ? -1 : 1);
    }
}
