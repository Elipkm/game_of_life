package game.utils;

import java.util.Random;

public class Utils {
    public static int getRandomNumberInRange(int min, int max){
        return new Random().nextInt((max - min) + 1) + min;
    }
}
