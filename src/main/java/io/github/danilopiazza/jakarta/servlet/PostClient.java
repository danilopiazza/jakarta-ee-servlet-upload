package io.github.danilopiazza.jakarta.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

public class PostClient {
    public static void main(String[] args) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(args[0]))
                .POST(BodyPublishers.ofInputStream(ZeroInputStream::new))
                .build();
        HttpClient.newHttpClient()
                .send(request, BodyHandlers.discarding());
    }
}

class ZeroInputStream extends InputStream {
    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        Arrays.fill(b, off, off + len, (byte) 0);
        return len;
    }
}
