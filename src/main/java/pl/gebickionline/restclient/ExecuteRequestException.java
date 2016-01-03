package pl.gebickionline.restclient;


public class ExecuteRequestException extends RuntimeException {
    public ExecuteRequestException(Exception e) {
        super(e.getMessage());
    }
}
