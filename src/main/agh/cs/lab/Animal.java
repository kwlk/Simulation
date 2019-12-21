package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Animal<list> implements IMapElement{

    int energy;
    private Vector2d position;
    private MapDirection direction;
    private IWorldMap map;
    private Genotype genotype;
    long lifeLengthSoFar = 0;
    private List<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();
    int children = 0;

    /*public Animal(IWorldMap map){
        this.map = map;
    }*/

    public Animal(IWorldMap map, int energy){
        this.map = map;
        this.energy = energy;
        this.position = this.map.getStarterPosition();
        this.direction = MapDirection.getStarterMapDirection();
        this.genotype = Genotype.getStarterGenotype();
        map.place(this);
    }


    public void dies(WildMap map){
        map.animals.remove(this);
        map.objects.get(this.getPosition()).animals.remove(this);
    }


    public Animal (Animal stronger, Animal weaker){
        this.map = stronger.map;
        this.position = stronger.map.placeForChild(stronger.getPosition());
        this.energy = stronger.energy/4 + weaker.energy/4;
        stronger.energy -= stronger.energy/4;
        weaker.energy -= weaker.energy/4;
        this.direction = MapDirection.getStarterMapDirection();
        this.genotype = Genotype.getChildGenotype(stronger.genotype, weaker.genotype);
        this.map.placeChild(this);
    }

    public Vector2d getPosition(){
        return position;
    }

    public String toString(){
        return "Animal "+ this.position.toString() +this.direction.toString();

    }

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }


    public void move(){
        switch (this.turn()){
            case 0:
            case 1:
                this.direction = this.direction.halfNext();
            case 2:
                this.direction = this.direction.next();
            case 3:
                this.direction = this.direction.next().halfNext();
            case 4:
                this.direction = this.direction.next().next();
            case 5:
                this.direction = this.direction.previous().halfPrevious();
            case 6:
                this.direction = this.direction.previous();
            case 7:
                this.direction = this.direction.halfPrevious();

                Vector2d newMove = this.direction.toUnitVector();
                Vector2d afterMove = this.position.add(newMove);
                afterMove = map.turnIntoProperPosition(afterMove);
                this.positionChanged(afterMove);
        }
    }

    public byte turn(){
        Random rand = new Random();
        return this.genotype.getGen(rand.nextInt(32));
    }






    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);

    }
    private void positionChanged(Vector2d newPosition){
        for (IPositionChangeObserver o : observers){
            o.positionToChangeFirstPart(this);
        }
        this.position = newPosition;
        for (IPositionChangeObserver o : observers){
            o.positionChanged(this);
        }
    }

    Genotype getGenotype(){
        return this.genotype;
    }


}
