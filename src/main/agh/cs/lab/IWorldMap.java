package agh.cs.lab;


public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(agh.cs.lab.Animal animal);
    void placeChild (Animal child);
    Vector2d placeForChild(Vector2d parentsPosition);

    Vector2d getStarterPosition();

    void run();

    boolean isOccupied(Vector2d position);

    Vector2d turnIntoProperPosition(Vector2d afterMove);
}
