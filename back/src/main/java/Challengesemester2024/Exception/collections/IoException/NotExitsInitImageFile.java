package Challengesemester2024.Exception.collections.IoException;

import Challengesemester2024.Exception.message.DbInitExceptionMessage;

import java.io.IOException;

public class NotExitsInitImageFile extends IOException {
    public NotExitsInitImageFile() {
        super(DbInitExceptionMessage.NoExitsInitImageFile);
    }
}
