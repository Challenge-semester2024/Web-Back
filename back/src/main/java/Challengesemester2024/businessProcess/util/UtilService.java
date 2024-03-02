package Challengesemester2024.businessProcess.util;

import software.amazon.ion.IonException;
import java.io.IOException;

public interface UtilService {
    public String getRandomNum();
    public String getRandomUUID(String originalName);
    public String getInitImagePath() throws IOException;

}
