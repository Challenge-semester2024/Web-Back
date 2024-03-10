package Challengesemester2024.Exception.collections.IoException;

import Challengesemester2024.Exception.message.DbExceptionMessage;

import java.io.IOException;


public class allImageFileIOException extends IOException {

    public allImageFileIOException() {
        super(DbExceptionMessage.AllImageFileIOError);
    }
}
