package com.redhat.developers.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // creating completableFuture
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        // to get result from CompletableFuture.get() method
        // the get() method blocks until the Future is complete. So, the call will block forever because the Future is never completed.
        String result = completableFuture.get();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Yoooooo");
    }


}
