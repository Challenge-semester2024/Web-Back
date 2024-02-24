package Challengesemester2024.businessProcess.util;

import java.util.concurrent.ThreadLocalRandom;

public class UtilServiceImpl implements UtilService {
    @Override
    public String getRandomNum() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000,900000));
    }
}
