package agh.cs.lab;

import agh.cs.lab.Vector2d;

import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    NORTHWEST,
    SOUTHEAST,
    SOUTHWEST,
    SOUTH,
    EAST,
    WEST;

    public static MapDirection getStarterMapDirection(){
        Random rand = new Random();
        return MapDirection.values()[rand.nextInt(MapDirection.values().length)];
    }

    public String toString(){
        switch(this) {
            case NORTH: return "N";
            case NORTHWEST: return "NW";
            case NORTHEAST: return "NE";
            case SOUTH: return "S";
            case SOUTHEAST: return "SE";
            case SOUTHWEST: return "SW";
            case EAST: return "E";
            default: return "W";
        }
    }

    public MapDirection next (){
        switch(this){
            case NORTH: return EAST;
            case NORTHEAST: return SOUTHEAST;
            case NORTHWEST: return NORTHEAST;
            case SOUTH: return WEST;
            case SOUTHWEST: return NORTHWEST;
            case SOUTHEAST: return SOUTHWEST;
            case EAST: return SOUTH;
            default: return NORTH;
        }
    }

    public MapDirection previous (){
        switch(this){
            case NORTH: return WEST;
            case NORTHEAST: return NORTHWEST;
            case NORTHWEST: return SOUTHWEST;
            case SOUTH: return EAST;
            case SOUTHWEST: return SOUTHEAST;
            case SOUTHEAST: return NORTHEAST;
            case EAST: return NORTH;
            default: return SOUTH;
        }
    }

    public MapDirection halfNext (){
        switch(this){
            case NORTH: return NORTHEAST;
            case NORTHEAST: return EAST;
            case NORTHWEST: return NORTH;
            case SOUTH: return SOUTHWEST;
            case SOUTHWEST: return WEST;
            case SOUTHEAST: return SOUTH;
            case EAST: return SOUTHEAST;
            default: return NORTHWEST;
        }
    }

    public MapDirection halfPrevious (){
        switch(this){
            case NORTH: return NORTHWEST;
            case NORTHEAST: return NORTH;
            case NORTHWEST: return WEST;
            case SOUTH: return SOUTHEAST;
            case SOUTHWEST: return SOUTH;
            case SOUTHEAST: return EAST;
            case EAST: return NORTHEAST;
            default: return SOUTHWEST;
        }
    }

    public Vector2d toUnitVector(){
        switch(this){
            case NORTH: return new Vector2d(0,1);
            case NORTHEAST: return new Vector2d(1,1);
            case NORTHWEST: return new Vector2d(-1, 1);
            case SOUTH: return new Vector2d(0, -1);
            case SOUTHEAST: return new Vector2d(1, -1);
            case SOUTHWEST: return new Vector2d(-1, -1);
            case EAST: return new Vector2d(1,0);
            default: return new Vector2d(-1, 0);
        }
    }



}
