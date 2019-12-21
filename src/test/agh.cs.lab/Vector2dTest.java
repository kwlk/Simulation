package agh.cs.lab;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    private final Vector2d vector_1_1 = new Vector2d(1,1);
    private final Vector2d vector_1_2 = new Vector2d(1,2);
    private final Vector2d vector_2_1 = new Vector2d(2,1);
    private final Vector2d vector_0_0 = new Vector2d(0,0);
    private final Vector2d vector_0_1 = new Vector2d(0,1);
    private final Vector2d vector_2_2 = new Vector2d(2,2);
    private final Vector2d vector_0_2 = new Vector2d(0,2);

    @Test
    public void testEquals(){
        Assert.assertTrue(vector_2_2.equals(vector_0_1.add(vector_2_1)));
        Assert.assertNotEquals(vector_1_2, new Vector2d(-1,-2));

    }
    @Test
    public void testToString(){
        Assert.assertEquals(vector_1_1.toString(), "(1,1)");
        Assert.assertNotEquals("(1,2)", new Vector2d(-1, -2));
    }
    @Test
    public void testPrecedes(){
        Assert.assertTrue(new Vector2d(-1,-2).precedes(vector_1_2));
        Assert.assertTrue(vector_1_1.precedes(vector_1_1));
        Assert.assertFalse(vector_1_2.precedes(new Vector2d(-1,-2)));
    }
    @Test
    public void testFollows(){
        Assert.assertTrue(vector_1_2.follows(new Vector2d(-1,-2)));
        Assert.assertTrue(vector_1_1.precedes(vector_1_1));
        Assert.assertFalse(new Vector2d(-1,-2).follows(vector_1_2));
    }
    @Test
    public void testUpperRight(){
        Assert.assertEquals(vector_1_2.upperRight(vector_2_1), vector_2_2);
        Assert.assertNotEquals(vector_1_2.upperRight(vector_2_1), vector_1_1);
    }
    @Test
    public void testLowerLeft(){
        Assert.assertEquals(vector_1_2.lowerLeft(vector_2_1), vector_1_1);
        Assert.assertNotEquals(vector_1_2.lowerLeft(vector_2_1), vector_2_2);
    }
    @Test
    public void testAdd(){
        Assert.assertEquals(vector_2_2,vector_0_1.add(vector_2_1));
        Assert.assertNotEquals(vector_2_1,vector_0_1.add(vector_2_1));
    }
    @Test
    public void testSubstract(){
        Assert.assertEquals(vector_1_1, vector_1_2.substract(vector_0_1) );
        Assert.assertEquals(vector_0_0, vector_0_0.substract(vector_0_0));
        Assert.assertNotEquals(vector_1_1, vector_1_2.substract(vector_2_2));
    }
    @Test
    public void testOpposite(){
        Assert.assertEquals(new Vector2d(-1, -1), vector_1_1.opposite());
        Assert.assertEquals(vector_0_0, vector_0_0.opposite());
        Assert.assertNotEquals(vector_1_1, vector_1_1.opposite());
    }
    @Test
    public void testUnEndingAdd(){
        Assert.assertEquals(vector_0_0.unEndingAdd(vector_2_2, vector_1_2),vector_0_2 );
        Assert.assertEquals(vector_0_0.unEndingAdd(vector_1_1, vector_1_2),vector_1_1 );
    }
}