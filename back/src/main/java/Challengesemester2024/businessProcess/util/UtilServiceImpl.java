package Challengesemester2024.businessProcess.util;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class UtilServiceImpl implements UtilService {
    @Override
    public String getRandomNum() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000,900000));
    }
    @Override
    public String getRandomUUID(String originalName) {
        String uuid = UUID.randomUUID().toString();
        return uuid+originalName;
    }
}
