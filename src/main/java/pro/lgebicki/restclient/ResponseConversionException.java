package pro.lgebicki.restclient;

public class ResponseConversionException extends RuntimeException {
    public ResponseConversionException(Exception e) {
        super(e.getMessage());
    }
}
