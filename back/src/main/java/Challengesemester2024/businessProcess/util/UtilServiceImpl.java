package Challengesemester2024.businessProcess.util;

import Challengesemester2024.Exception.collections.IoException.NotExitsInitImageFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import software.amazon.ion.IonException;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class UtilServiceImpl implements UtilService {
    private ResourceLoader resourceLoader;
    @Override
    public String getRandomNum() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000,900000));
    }
    @Override
    public String getRandomUUID(String originalName) {
        String uuid = UUID.randomUUID().toString();
        return uuid+originalName;
    }

    @Override
    public String getInitImagePath(String fileName) throws IOException { //해당 에러 발생시, 상위 함수로 에러 던져짐
        Resource resource = resourceLoader.getResource("classpath:static/image/" + fileName);
        return resource.getFile().getAbsolutePath();
    }

}
