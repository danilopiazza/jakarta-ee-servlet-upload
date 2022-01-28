package io.github.danilopiazza.jakarta.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/null")
public class NullServlet extends HttpServlet {
    private static final Logger LOG = System.getLogger(NullServlet.class.getSimpleName());
    private static final int BUFFER_SIZE = 8192;
    private static final long BYTES_PER_GIGABYTE = 1_000_000_000L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.log(Level.INFO, "POST /null");
        BufferedInputStream in = new BufferedInputStream(request.getInputStream());
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytes;
        long total = 0;
        long partial = 0;
        while ((bytes = in.read(buffer)) > 0) {
            total += bytes;
            partial += bytes;
            if (partial >= BYTES_PER_GIGABYTE) {
                LOG.log(Level.INFO, "Reading {0} GB...", total / BYTES_PER_GIGABYTE);
                partial = 0;
            }
        }
        response.getOutputStream().println(total / BYTES_PER_GIGABYTE + " GB");
    }
}
