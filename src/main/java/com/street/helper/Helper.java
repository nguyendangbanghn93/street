package com.street.helper;

import java.util.concurrent.ThreadLocalRandom;

public class Helper {
    static public int random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
