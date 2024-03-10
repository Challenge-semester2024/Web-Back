package Challengesemester2024.Exception.collections.business;

import Challengesemester2024.Exception.message.DbExceptionMessage;

public class DatabaseNotFoundException extends BusinessException{

    public DatabaseNotFoundException() {
        super(DbExceptionMessage.DatabaseNotFoundException);
    }
}
