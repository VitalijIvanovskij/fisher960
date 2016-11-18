import java.util.Random;

/**
 * Created by Виталий on 15.10.2016.
 */
abstract public class GeneratePosition {
    private final static String KING = "\u2654";
    private final static String QUEEN = "\u2655";
    private final static String ROOK = "\u2656";
    private final static String BISHOP = "\u2657";
    private final static String KNIGHT = "\u2658";

    private static String[] position = new String[8];

    public static String generatePosition(){

        Random randomSquare = new Random();

        //устанавливаем поле для белопольного слона
        position[randomSquare.nextInt(4) * 2 + 1] = BISHOP;
        //устанавливаем поле для чернопольного слона
        position[randomSquare.nextInt(4) * 2] = BISHOP;
        //устанавливаем первого коня
        int numberOfEmptySquare = randomSquare.nextInt(6);
        setFigure(numberOfEmptySquare, KNIGHT);

        //устанавливаем второго коня
        numberOfEmptySquare = randomSquare.nextInt(5);
        setFigure(numberOfEmptySquare, KNIGHT);

        //устанавливаем ферзя
        numberOfEmptySquare = randomSquare.nextInt(4);
        setFigure(numberOfEmptySquare, QUEEN);

        //устанавливаем ладьи и короля между ними
        int count = 1; //счётчик свободных полей
        for (int i = 0; i < 8; i++){
            if(count > 3) break;

            if(position[i] != null)continue;
            else if(position[i] == null && count == 2){
                position[i] = KING;
                count++;
            }
            else {
                position[i] = ROOK;
                count++;
            }
        }

        return getPositionAsString();
    }

    public static void resetPosition(){
        for(int i = 0; i < 8; i++)
            position[i] = null;
    }

    private static String getPositionAsString(){
        String positionAsString = "";
        for(String s : position) positionAsString += s;

        return positionAsString;
    }

    public static String getStartPosition(){
        return ROOK + KNIGHT + BISHOP + QUEEN + KING + BISHOP + KNIGHT + ROOK;
    }

    private static void setFigure(int numberOfSquare, String unicodeOfFigure){
        for(int i = 0; i < 8; i++){
            if(position[i] == null && numberOfSquare == 0) {
                position[i] = unicodeOfFigure;
                break;
            }
            else if (position[i] == null && numberOfSquare > 0)
                numberOfSquare--;
            else continue;
        }
    }
}

