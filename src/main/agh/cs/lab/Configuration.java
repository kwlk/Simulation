package agh.cs.lab;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration {
    public final int width;
    public final int height;
    public final int startEnergy;
    public final int startAnimals;
    public final int moveEnergy;
    public final int starterPlants;
    public final int plantEnergy;
    public final double jungleRatio;
    public final int timeRunning;

    Configuration (){
        width = height = startEnergy = startAnimals = moveEnergy = plantEnergy = starterPlants = timeRunning = 0;
        jungleRatio = 0;
    }

    public static Configuration fromJson(final String parametersPath) throws FileNotFoundException {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader(System.getProperty("user.dir") + "/" + parametersPath), Configuration.class);
        }catch (FileNotFoundException ex){
            System.out.println("Configuration file not found!\n Path: "+ parametersPath+"\n"+ex.toString());
            throw ex;
        }
    }
}
