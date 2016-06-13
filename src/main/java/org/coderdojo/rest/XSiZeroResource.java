package org.coderdojo.rest;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// native DLL from http://fizzed.com/oss/rxtx-for-java

@Singleton
@Path("xsizero")
public class XSiZeroResource {
    private final Callback callback = new Callback();
    private final SerialHandler serialHandler;

    public XSiZeroResource() {
        serialHandler = new SerialHandler(callback);
        serialHandler.initialize("COM3");

    }

    @GET
    @Produces("text/plain")
    public String getBoard() {
        serialHandler.write("a");

        return callback.getData();
    }

    private static class Callback implements ICallback {
        private String data;

        @Override
        public void onEvent(String incoming) {
            data = incoming;
        }

        public String getData() {
            return data;
        }
    }
}
