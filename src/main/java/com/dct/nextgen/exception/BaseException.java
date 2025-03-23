package com.dct.nextgen.exception;

import java.util.Arrays;

/**
 * Custom class for proactive exceptions in the application <p>
 *
 * {@link BaseException#entityName}: Name of the class that throws the exception <p>
 * {@link BaseException#errorKey}: The i18n message key used for language internationalization <p>
 * {@link BaseException#args}: Parameters accompanying the message of the `errorKey` if required
 *
 * @author thoaidc
 */
@SuppressWarnings("unused")
public class BaseException extends RuntimeException {

    private final String entityName;
    private final String errorKey;
    private final Object[] args;
    private final Throwable error;

    public BaseException(String entityName, String errorKey) {
        this(entityName, errorKey, null, null);
    }

    public BaseException(String entityName, String errorKey, Throwable error) {
        this(entityName, errorKey, null, error);
    }

    public BaseException(String entityName, String errorKey, Object[] args) {
        this(entityName, errorKey, args, null);
    }

    public BaseException(String entityName, String errorKey, Object[] args, Throwable error) {
        super(entityName + '-' + errorKey, error);
        this.entityName = entityName;
        this.errorKey = errorKey;
        this.args = args;
        this.error = error;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public Object[] getArgs() {
        return args;
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return "BaseException {" +
                "\nentityName: " + entityName +
                "\nerrorKey: " + errorKey +
                "\nargs: " + Arrays.toString(args) + "\n}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String entityName;
        private String errorKey;
        private Object[] args;
        private Throwable error;

        public Builder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        public Builder errorKey(String errorKey) {
            this.errorKey = errorKey;
            return this;
        }

        public Builder args(Object[] args) {
            this.args = args;
            return this;
        }

        public Builder error(Throwable error) {
            this.error = error;
            return this;
        }

        public BaseAuthenticationException buildAuthenticationException() {
            return new BaseAuthenticationException(entityName, errorKey, args, error);
        }

        public BaseBadRequestException buildBadRequestException() {
            return new BaseBadRequestException(entityName, errorKey, args, error);
        }

        public BaseBadRequestAlertException buildBadRequestAlertException() {
            return new BaseBadRequestAlertException(entityName, errorKey, args, error);
        }

        public BaseIllegalArgumentException buildIllegalArgumentException() {
            return new BaseIllegalArgumentException(entityName, errorKey, args, error);
        }
    }
}
