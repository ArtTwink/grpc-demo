package com.example.demo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.tasks.Echo;
import io.grpc.examples.tasks.EchoServiceGrpc;
import org.junit.jupiter.api.Test;
import org.lognet.springboot.grpc.context.LocalRunningGrpcPort;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "grpc.port=0")
class EchoServiceTest {
    @LocalRunningGrpcPort
    int port;

    @Test
    void echoTest() {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",port)
                .usePlaintext()
                .build();

        final Echo.Message response = EchoServiceGrpc.newBlockingStub(channel)
                .sayHello(Echo.Message.newBuilder()
                        .setText("hello")
                        .build());

        assertEquals("olleh",response.getText());
    }
}