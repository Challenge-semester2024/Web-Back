package Challengesemester2024.businessProcess.auth.util;

import java.util.concurrent.ThreadLocalRandom;

public class Util {
    public static String getRandomNum(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000,900000));
    }

}
