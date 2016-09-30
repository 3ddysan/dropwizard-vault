package edu.thinktank.vault.util;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import static okhttp3.Protocol.HTTP_1_1;

public class MockClient implements Interceptor {

    private Queue<Response.Builder> events = new ArrayDeque<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        return events.remove().request(chain.request()).protocol(HTTP_1_1).build();
    }

    public void enqueue(Response.Builder response) {
        events.add(response);
    }

}
