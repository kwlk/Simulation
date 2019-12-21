package agh.cs.lab;

import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

class PointOnMap {
   // boolean grass;
    SortedSet<Animal> animals = new TreeSet<>(new AnimalEnergyComparator());
    boolean tryForChildren;

    PointOnMap(Animal animal){
       // this.grass = false;
        this.tryForChildren = false;
        this.animals.add(animal);
    }

    /*public PointOnMap(){
        //this.grass = true;
        this.tryForChildren = false;
    }*/
}
