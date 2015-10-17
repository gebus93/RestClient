package pl.gebickionline.restclient;


class ExecuteRequestException extends RuntimeException {
    public ExecuteRequestException(Exception e) {
        super(e.getMessage());
    }
}
