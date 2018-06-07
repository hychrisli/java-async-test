package org.async;


import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.HttpResponseStatus;
import org.asynchttpclient.Response;

public class AsynGetHandler implements AsyncHandler<Response> {

    private final Response.ResponseBuilder builder = new Response.ResponseBuilder();

    public State onStatusReceived(HttpResponseStatus status) throws Exception {
        builder.accumulate(status);
        return State.CONTINUE;
    }

    public State onHeadersReceived(HttpHeaders headers) throws Exception {
        builder.accumulate(headers);
        return State.CONTINUE;
    }

    public State onBodyPartReceived(HttpResponseBodyPart content) throws Exception {
        builder.accumulate(content);
        return State.CONTINUE;
    }

    public void onThrowable(Throwable throwable) {
        System.out.println(throwable.getCause());
        System.out.println(throwable.getMessage());
    }

    public Response onCompleted() throws Exception {
        Response response = builder.build();
        System.out.println(response.getResponseBody());
        return response;
    }
}
