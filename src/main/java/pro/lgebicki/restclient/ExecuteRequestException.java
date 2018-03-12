package pro.lgebicki.restclient;


public class ExecuteRequestException extends RuntimeException {
    public ExecuteRequestException(Exception e) {
        super(e.getMessage());
    }
}
