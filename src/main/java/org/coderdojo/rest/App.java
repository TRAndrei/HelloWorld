package org.coderdojo.rest;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;

public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/rest/");

    public static void main(String[] args) throws ExecutionException {
        try {
            System.out.println("Coder Dojo sample app");

            final ResourceConfig resourceConfig = new ResourceConfig(XSiZeroResource.class);
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));

            HttpHandler httpHandler = new CLStaticHttpHandler(App.class.getClassLoader(), "/static/");
            server.getServerConfiguration().addHttpHandler(httpHandler, "/");

            server.start();

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
