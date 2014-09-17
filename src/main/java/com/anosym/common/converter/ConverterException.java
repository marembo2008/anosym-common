package com.anosym.common.converter;

/**
 *
 * @author marembo
 */
public class ConverterException extends RuntimeException {

    private final Class<?> fromClass;
    private final Class<?> toClass;

    public ConverterException(
            Class<?> fromClass,
            Class<?> toClass) {
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    public ConverterException(
            Class<?> fromClass,
            Class<?> toClass, String message) {
        super(message);
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    public ConverterException(
            Class<?> fromClass,
            Class<?> toClass, String message, Throwable cause) {
        super(message, cause);
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    public ConverterException(
            Class<?> fromClass,
            Class<?> toClass, Throwable cause) {
        super(cause);
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    public ConverterException(
            Class<?> fromClass,
            Class<?> toClass, String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    public Class<?> getToClass() {
        return toClass;
    }

    public Class<?> getFromClass() {
        return fromClass;
    }

}
