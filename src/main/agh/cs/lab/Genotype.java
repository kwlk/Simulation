package agh.cs.lab;

import java.lang.reflect.Array;
import java.util.*;

public class Genotype {
    private byte[] genotype = new byte[32];

    static Genotype getStarterGenotype(){
        Random rand = new Random();
        Genotype gen = new Genotype();

        for (int i=0; i<32; i++){
            gen.genotype[i] = (byte)rand.nextInt(8);
        }

        gen.turnIntoProperGen();
        return gen;
    }

    byte getGen(int number){

        return this.genotype[number];

    }
    
    private void turnIntoProperGen(){
        Random rand = new Random();
        int[] isNumber = new int[8];
        
        for (int i = 0; i<8; i++){
            isNumber[i] = 0;
        }
        
        for (int i = 0; i < 32; i++){
            isNumber[this.getGen(i)] ++;
        }

        List<Byte> missingGenes = new ArrayList<>();
        
        for (byte i = 0; i < 8; i++){
            if (isNumber[i] == 0)
                missingGenes.add(i);
        }
        for (int g : missingGenes){
            boolean putNewGenIn = false;
            while (!putNewGenIn) {
                int r = rand.nextInt(32);
                if (isNumber[this.getGen(r)] > 1) {
                    isNumber[this.getGen(r)]--;
                    this.genotype[r] = (byte) g;
                    isNumber[g]++;
                    putNewGenIn = true;
                }
            }
        }
        Arrays.sort(this.genotype);
    }

    public static Genotype getChildGenotype (Genotype stronger, Genotype weaker){
        Random rand = new Random();
        Genotype gen = new Genotype();
        int divisionOne = rand.nextInt(30) + 1;
        int divisionTwo = rand.nextInt((31 - divisionOne)) + divisionOne;
        int caseRandom = rand.nextInt(3);
        switch (caseRandom) {
            case 0: {
                for (int i = 0; i < divisionOne; i++) {
                    gen.genotype[i] = stronger.genotype[i] ;
                }
                for (int i = divisionOne; i < divisionTwo; i++) {
                    gen.genotype[i] = stronger.genotype[i];
                }
                for (int i = divisionTwo; i < 32; i++) {
                    gen.genotype[i] = weaker.genotype[i];
                }
                Arrays.sort(gen.genotype);
            }

            case 1: {
                for (int i = 0; i < divisionOne; i++) {
                    gen.genotype[i] = stronger.genotype[i] ;
                }
                for (int i = divisionOne; i < divisionTwo; i++) {
                    gen.genotype[i] = weaker.genotype[i];
                }
                for (int i = divisionTwo; i < 32; i++) {
                    gen.genotype[i] = stronger.genotype[i];
                }
                Arrays.sort(gen.genotype);
            }

            case 2: {
                for (int i = 0; i < divisionOne; i++) {
                    gen.genotype[i] = weaker.genotype[i];
                }
                for (int i = divisionOne; i < divisionTwo; i++) {
                    gen.genotype[i] = stronger.genotype[i];
                }
                for (int i = divisionTwo; i < 32; i++) {
                    gen.genotype[i] = stronger.genotype[i];
                }
                Arrays.sort(gen.genotype);
            }
        }
        return gen;
    }

    public String toString(){
        StringBuilder res= new StringBuilder();
        for(Byte b : this.genotype)
            res.append((char) (b + (int) '0'));
        return res.toString();
    }

    boolean equal(Genotype other){
        for (int i=0; i<32; i++){
            if (this.getGen(i)!=other.getGen(i)) return false;
        }
        return true;
    }

}
