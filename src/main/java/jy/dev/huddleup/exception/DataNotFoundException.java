package jy.dev.huddleup.exception;

public class DataNotFoundException extends RuntimeException {

    private final HttpResponse httpResponse;

    public DataNotFoundException(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }
}