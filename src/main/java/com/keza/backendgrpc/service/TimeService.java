package com.keza.backendgrpc.service;

import com.keza.backendgrpc.time.EmptyRequest;
import com.keza.backendgrpc.time.Output;
import com.keza.backendgrpc.time.TimeServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.LocalDateTime;

@GrpcService
public class TimeService extends TimeServiceGrpc.TimeServiceImplBase {

    @Override
    public void now(EmptyRequest request, StreamObserver<Output> responseObserver) {
        Output response = Output.newBuilder()
                .setTime(LocalDateTime.now().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void nowStream(EmptyRequest request, StreamObserver<Output> responseObserver) {
            for (int i = 1; i <= 50; i++) {
                Output response = Output.newBuilder()
                        .setTime(LocalDateTime.now().toString())
                        .build();

                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
    }
}
