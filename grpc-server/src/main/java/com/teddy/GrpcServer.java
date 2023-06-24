package com.teddy;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class GrpcServer {

    @Value("${grpc.port}")
    private int grpcPort;

    @EventListener(ApplicationReadyEvent.class)
    public void startGrpcServerAfterStartup() {
        Server server = ServerBuilder.forPort(grpcPort)
                .addService(new HelloGrpcImpl()).build();
        try {
            server.start();
            System.out.println("grpc server started ... at port " + grpcPort);
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

//    public static void main(String[] args) {
//        Server server = ServerBuilder.forPort(8081)
//                .addService(new HelloGrpcImpl()).build();
//        try {
//            server.start();
//            server.awaitTermination();
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
