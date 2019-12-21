package agh.cs.lab;
import java.util.ArrayList;

public class OptionsParser {
    public MoveDirection[] parse(String[] orders){
        ArrayList<MoveDirection> moves = new ArrayList<MoveDirection>();

        for(String order : orders){
            MoveDirection move;
            switch (order){
                case "f":
                case "forward":
                    move = MoveDirection.FORWARD;
                    break;
                case "b":
                case "backward":
                    move = MoveDirection.BACKWARD;
                    break;
                case "r":
                case "right":
                    move = MoveDirection.RIGHT;
                    break;
                case "l":
                case "left":
                    move = MoveDirection.LEFT;
                    break;
                default:
                    throw new IllegalArgumentException(order + " is invalid.");
            }
            moves.add(move);
        }
        return moves.toArray(new MoveDirection[0]);
    }
}
