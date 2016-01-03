package pl.gebickionline.restclient;

public class ResponseConversionException extends RuntimeException {
    public ResponseConversionException(Exception e) {
        super(e.getMessage());
    }
}
