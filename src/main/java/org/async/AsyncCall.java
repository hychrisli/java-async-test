package org.async;

import org.asynchttpclient.*;

public class AsyncCall {

    private AsyncHttpClient client;
    private RequestBuilder builder;

    public AsyncCall () {
        builder = new RequestBuilder("GET");
        client = Dsl.asyncHttpClient();
    }

    public void get(String url){
        Request request = builder.setUrl(url).build();
        client.executeRequest(request, new AsynGetHandler());
        System.out.println("Hello there, aync baby!");

        // BoundRequestBuilder getRequest = client.prepareGet(url);
        // getRequest.execute(new AsynGetHandler());
    }

    public void post(String url, String body){

    }

    public static void main (String[] args) {
        AsyncCall asyncCall = new AsyncCall();
        asyncCall.get("https://www.google.com");

    }
}
