package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.utils;

import java.util.Random;

public class ColorGenerator {

    public static String colorGenerator()
    {
        Random random = new Random();

        int nextInt = random.nextInt(256*256*256);

        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }

}
