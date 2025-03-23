package com.dct.nextgen.exception;

public class BaseBadRequestException extends BaseException {

    public BaseBadRequestException(String entityName, String errorKey) {
        super(entityName, errorKey);
    }

    public BaseBadRequestException(String entityName, String errorKey, Object[] args, Throwable error) {
        super(entityName, errorKey, args, error);
    }
}
