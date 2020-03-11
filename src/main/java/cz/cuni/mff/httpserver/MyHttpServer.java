package cz.cuni.mff.httpserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MyHttpServer {

    private static final int BACKLOG = 100;

    private final HttpServer server;

    public MyHttpServer(final int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress((InetAddress) null, port), BACKLOG);
    }

    public void register(final Class<?>... clazzez) {
        server.createContext("/systems/version", exchange -> { // TODO: take path value from the @Path annotation

            byte[] response = "1.0".getBytes(); // TODO: the actual response need to be taken from the method invocation

            exchange.sendResponseHeaders(200, response.length); // 200 means ok
            exchange.getResponseBody().write(response);
            exchange.close();
        });
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
