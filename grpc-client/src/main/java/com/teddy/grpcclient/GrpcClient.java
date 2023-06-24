package com.teddy.grpcclient;

import com.teddy.grpcsample.api.HelloServiceGrpc;
import com.teddy.grpcsample.api.HelloRequest;
import com.teddy.grpcsample.api.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GrpcClient {

    HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = null;

    @Value("${grpc.dest.port}")
    private int grpcDestPort;

    @EventListener(ApplicationReadyEvent.class)
    public void startGrpcClientAfterStartup() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", grpcDestPort)
                .usePlaintext()
                .build();

        helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(channel);
        System.out.println("helloServiceBlockingStub is created ...");

    }

    public HelloResponse sendGreeting(String firstName, String lastName) {
        if(helloServiceBlockingStub != null) {
            HelloResponse helloResponse = helloServiceBlockingStub.hello(HelloRequest.newBuilder().setFirstName(firstName).setLastName(lastName).build());
            System.out.println("response greeting is " + helloResponse.getGreeting());
            return helloResponse;
        } else {
            throw new RuntimeException("stub not created .");
        }
    }




}
