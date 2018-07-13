package helpers;

import java.util.Random;

public class RandomNumberGenerator {
    private long seed = 2376457;

    public int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int gerRandomNumberBasedOnAverage(int avg) {

//        Random r = new Random();
//
//        return (int)Math.round(-(Math.log(r.nextDouble()) / avg) * 100 + 1);

        return getRandomNumberInRange(1, avg + avg - 1);
    }
}