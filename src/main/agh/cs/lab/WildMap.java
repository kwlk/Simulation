package agh.cs.lab;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class WildMap implements IWorldMap, IPositionChangeObserver {


    List <Animal> animals = new ArrayList<>();
    Map <Vector2d, PointOnMap >objects = new HashMap<>();
    List <Vector2d> noPlants = new ArrayList<>();
    List <Vector2d> plants = new ArrayList<>();

   // private final Vector2d mapBottomLeft = new Vector2d(0,0);
    private final Vector2d mapUpperRight;
    private final Vector2d jungleUpperRight;
    private final Vector2d jungleBottomLeft;


    public WildMap(int width, int height, double jungleRatio) {
        this.mapUpperRight = new Vector2d(width - 1 , height - 1);
        int jungleWidth =(int)(width*Math.sqrt(jungleRatio));
        int jungleHeight = (int)(height*Math.sqrt(jungleRatio));
        this.jungleBottomLeft = new Vector2d((int)((this.mapUpperRight.x / 2 ) - (jungleWidth/2)), (int)((this.mapUpperRight.y/2) - (jungleHeight/2)));
        this.jungleUpperRight = new Vector2d(this.jungleBottomLeft.x + jungleWidth - 1, this.jungleBottomLeft.y + jungleHeight - 1);
        for (int i =0; i <= this.mapUpperRight.x; i++){
            for (int j=0; j <= this.mapUpperRight.y ; j++){
                this.noPlants.add(new Vector2d( i , j ));
            }
        }
    }

    public Vector2d getStarterPosition(){
        Random rand = new Random();
        int counter = 0;
        while (counter < 1000000){
            int tempX = rand.nextInt(this.mapUpperRight.x + 1);
            int tempY = rand.nextInt(this.mapUpperRight.y + 1);
            if(!isOccupied(new Vector2d(tempX, tempY))) {
                return new Vector2d(tempX, tempY);
            }
            counter++;
        }
        throw new RuntimeException("The map is too small for all these animals!");
    }

    public Vector2d getRandomJungleGrassPosition(){
        Random rand = new Random();
        if (this.noPlants.size()==0) return null;
        int randomPosition = rand.nextInt(this.noPlants.size());
        int i = 0;
        while (i < noPlants.size()) {/*
            int tempX = rand.nextInt(this.jungleUpperRight.x - this.jungleBottomLeft.x + 1) + this.jungleBottomLeft.x;
            int tempY = rand.nextInt(this.jungleUpperRight.y - this.jungleBottomLeft.y + 1) + this.jungleBottomLeft.y;
            if (!isOccupied(new Vector2d(tempX, tempY)))
                return new Vector2d(tempX, tempY);
        */
            if (this.isInJungle(this.noPlants.get((randomPosition+i) % this.noPlants.size()))) return this.noPlants.get((randomPosition+i) % this.noPlants.size());
            i++;
        }

        return null;
    }

    boolean isInJungle (Vector2d position){
        return (position.x <= this.jungleUpperRight.x && position.x >= this.jungleBottomLeft.x && position.y <= this.jungleUpperRight.y && position.y >= this.jungleBottomLeft.y);
    }

    public Vector2d getRandomNotJungleGrassPosition(){
        Random rand = new Random();
        if (this.noPlants.size()==0) return null;
        int randomPosition = rand.nextInt(this.noPlants.size());
        int i = 0;
        while (i < noPlants.size()) {/*
            int tempX = rand.nextInt(this.jungleUpperRight.x - this.jungleBottomLeft.x + 1) + this.jungleBottomLeft.x;
            int tempY = rand.nextInt(this.jungleUpperRight.y - this.jungleBottomLeft.y + 1) + this.jungleBottomLeft.y;
            if (!isOccupied(new Vector2d(tempX, tempY)))
                return new Vector2d(tempX, tempY);
        */
            if (!this.isInJungle(this.noPlants.get((randomPosition+i) % this.noPlants.size()))) return this.noPlants.get((randomPosition+i) % this.noPlants.size());
            i++;
        }
        return null;
    }

    public void growGrass(){
        int result = 0;
        Vector2d jungleGrass = getRandomJungleGrassPosition();
        Vector2d notJungleGrass = getRandomNotJungleGrassPosition();
        if (jungleGrass != null){
            /*if (this.objects.get(jungleGrass)!= null)
                //this.objects.get(jungleGrass).grass = true;



            else
                this.objects.put(jungleGrass, new PointOnMap());
*/
            this.noPlants.remove(jungleGrass);
            this.plants.add(jungleGrass);
            //this.plants.put(jungleGrass, new Grass(jungleGrass));

        }
        if(notJungleGrass!= null) {
           /* if (this.objects.get(notJungleGrass)!= null)
                this.objects.get(notJungleGrass).grass = true;
            else
                this.objects.put(notJungleGrass, new PointOnMap());
*/
            this.noPlants.remove(notJungleGrass);
            this.plants.add(notJungleGrass);
            //this.plants.put(notJungleGrass, new Grass(notJungleGrass));
        }
    }

    public void run(){
        for(int i = 0; i < animals.size(); i++){
            /*animals.get(i % (animals.size())).move(directions[i]);*/
            Animal changer = animals.get(i % (animals.size()));
            changer.move();

            // Put this in day??
        }
    }

    public Vector2d turnIntoProperPosition(Vector2d afterMove){
        if (afterMove.x > mapUpperRight.x)
            afterMove.substract(new Vector2d(mapUpperRight.x + 1, 0));
        if (afterMove.y > mapUpperRight.y)
            afterMove.substract(new Vector2d(0, mapUpperRight.y + 1));
        if (afterMove.x < 0)
            afterMove.add(new Vector2d(mapUpperRight.x + 1, 0));
        if (afterMove.y < 0)
            afterMove.add(new Vector2d(0, mapUpperRight.y + 1));
        return afterMove;
    }


    public boolean place(Animal animal){
        animal.addObserver(this);
        this.animals.add(animal);
       // List<Animal> animalsAlreadyOnThisPosition = this.objects.get(animal.getPosition());
        //animalsAlreadyOnThisPosition.add(animal);
        //this.objects.put(animal.getPosition(), animalsAlreadyOnThisPosition);
        if (this.objects.get(animal.getPosition())==null){
            this.objects.put(animal.getPosition(), new PointOnMap(animal));
        }
        else this.objects.get(animal.getPosition()).animals.add(animal);
        return true;
    }

    public void placeChild(@NotNull Animal child){
        child.addObserver(this);
        this.animals.add(child);
        if (objects.get(child.getPosition())!= null)
            this.objects.get(child.getPosition()).animals.add(child);
        else {
            this.objects.put(child.getPosition(), new PointOnMap(child));
        }
    }

    public Vector2d placeForChild(Vector2d parentsPosition){
       // boolean positionChecked[] = new boolean[8];
       // boolean noFreeSpace = false;
        Random rand = new Random();
       // for (int i=0; i<8; i++)
       //     positionChecked[i] = false;
       // while(!noFreeSpace) {
            int r = rand.nextInt(8);
            //if (!positionChecked[r])
                switch (r) {
                    case 0:
                    {
                        //positionChecked[0] = true;
                        //if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x, parentsPosition.y + 1))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x, parentsPosition.y + 1));
                    }
                    case 1:
                    {
                        //positionChecked[1] = true;
                        //if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x + 1, parentsPosition.y + 1))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x + 1, parentsPosition.y + 1));
                    }
                    case 2:
                    {
                       // positionChecked[2] = true;
                       // if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x + 1, parentsPosition.y ))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x + 1, parentsPosition.y ));
                    }
                    case 3:
                    {
                        //positionChecked[3] = true;
                       // if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x + 1, parentsPosition.y - 1))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x + 1, parentsPosition.y - 1));
                    }
                    case 4:
                    {
                        //positionChecked[4] = true;
                        //if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x , parentsPosition.y - 1))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x , parentsPosition.y - 1));
                    }
                    case 5:
                    {
                       // positionChecked[5] = true;
                       // if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x - 1, parentsPosition.y - 1))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x - 1, parentsPosition.y - 1));
                    }
                    case 6:
                    {
                        //positionChecked[6] = true;
                        //if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x - 1, parentsPosition.y ))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x - 1, parentsPosition.y ));
                    }
                    default:
                    {
                        //positionChecked[7] = true;
                        //if (this.objectAt(this.turnIntoProperPosition(new Vector2d(parentsPosition.x - 1, parentsPosition.y + 1))).getClass() != Animal.class)
                            return this.turnIntoProperPosition(new Vector2d(parentsPosition.x - 1, parentsPosition.y + 1));
                    }
                    /*noFreeSpace = true;
                    for (int i=0; i<8; i++){
                        if (!positionChecked[i])
                            noFreeSpace = false;
                    }*/
                }
       // }
       // return parentsPosition;
    }


    public boolean canMoveTo(Vector2d position){
        return !this.isOccupied(position);
    }
    public boolean isOccupied(Vector2d position){
        if (this.objects.get(position) == null && this.noPlants.contains(position)) return false;
        return  (!this.objects.get(position).animals.isEmpty() || !this.noPlants.contains(position));
    }


    public void positionToChangeFirstPart(Animal animalWithOldPosition) {

        objects.get(animalWithOldPosition.getPosition()).animals.remove(animalWithOldPosition);


    }

    @Override
    public void positionChanged(@NotNull Animal animalWithNewPosition) {
        if (objects.get(animalWithNewPosition.getPosition())!= null)
            objects.get(animalWithNewPosition.getPosition()).animals.add(animalWithNewPosition);
        else {
            objects.put(animalWithNewPosition.getPosition(), new PointOnMap(animalWithNewPosition));
        }
    }

    /* @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(mapBottomLeft, mapUpperRight);
    }*/
}
