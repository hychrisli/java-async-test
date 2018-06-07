package org.async;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.asynchttpclient.*;

import java.io.IOException;
import java.util.concurrent.Future;

public class AsyncCall {

    private AsyncHttpClient client;
    private RequestBuilder getBuilder, postBuilder;

    public AsyncCall () {
        getBuilder = new RequestBuilder("GET");
        postBuilder = new RequestBuilder("POST");
        client = Dsl.asyncHttpClient();
    }

    public void get(String url){
        Request request = getBuilder.setUrl(url).build();
        client.executeRequest(request, new AsynGetHandler());
        System.out.println("Hello there, aync baby!");

        // BoundRequestBuilder getRequest = client.prepareGet(url);
        // getRequest.execute(new AsynGetHandler());
    }

    public void post(String url, String body){
        Request request = postBuilder.setUrl(url).setBody(body).build();
        client.executeRequest(request, new AsynGetHandler());
        System.out.println("Hello there, aync POST baby!");
    }

    public void getV2() throws  Exception{

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts/100");
            httpclient.execute(request, new MyFutureCallback());
            // HttpResponse response = future.get();
            // System.out.println("Response: " + response.getStatusLine());
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }


    public static void main (String[] args) throws Exception {
        AsyncCall asyncCall = new AsyncCall();
        // asyncCall.get("https://www.google.com");
        /*asyncCall.post("https://jsonplaceholder.typicode.com/posts",
                "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1\n" +
                "}");*/
        // asyncCall.get("https://jsonplaceholder.typicode.com/posts/101");
        // asyncCall.get("https://jsonplaceholder.typicode.com/posts/100");
        asyncCall.getV2();

    }
}
