package agh.cs.lab;

public interface IPositionChangeObserver {
    void positionChanged(Animal animal);
    void positionToChangeFirstPart(Animal animalWithOldPosition);
}
