package org.async;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

import java.io.IOException;

public class MyFutureCallback implements FutureCallback<HttpResponse> {

    public void completed(HttpResponse httpResponse) {
        try {
            System.out.println(httpResponse.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void failed(Exception e) {
        e.printStackTrace();
    }

    public void cancelled() {

    }
}
