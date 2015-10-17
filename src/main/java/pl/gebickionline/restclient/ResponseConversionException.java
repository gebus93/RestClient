package pl.gebickionline.restclient;

class ResponseConversionException extends RuntimeException {
    public ResponseConversionException(Exception e) {
        super(e.getMessage());
    }
}
