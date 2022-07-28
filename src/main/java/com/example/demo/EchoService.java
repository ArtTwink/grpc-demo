package com.example.demo;

import io.grpc.examples.tasks.Echo;
import io.grpc.examples.tasks.EchoServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import java.util.logging.Logger;

@GRpcService
public class EchoService extends EchoServiceGrpc.EchoServiceImplBase {
    private static final Logger logger = Logger.getLogger(DemoApplication.class.getName());
    @Override
    public void sayHello(Echo.Message request, StreamObserver<Echo.Message> responseObserver) {
        responseObserver.onNext(
                Echo.Message.newBuilder()
                        .setText(new StringBuilder(request.getText())
                                .reverse().toString()
                        )
                        .build()
        );
        logger.info("Got request from client: " + request);
        responseObserver.onCompleted();
    }
}