package Challengesemester2024.Exception.collections.IoException;

import Challengesemester2024.Exception.message.S3ExceptionMessage;

import java.io.IOException;

public class ImageInputException extends IOException {

    public ImageInputException() {
        super(S3ExceptionMessage.ImageInputException);
    }
}
