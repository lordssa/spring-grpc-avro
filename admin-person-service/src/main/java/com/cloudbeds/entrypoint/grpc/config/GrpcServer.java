package com.cloudbeds.entrypoint.grpc.config;

import com.cloudbeds.avro.UserAddressService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.grpc.AvroGrpcServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class GrpcServer {

    @Value("${grpc.server.port:9091}")
    private int port;
    private Server server;
    private UserAddressService userAddressService;

    public GrpcServer(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    public void start() throws IOException {
        log.info("gRPC server is starting on port: {}.", port);
        server = ServerBuilder.forPort(port)
                .addService(AvroGrpcServer.createServiceDefinition(UserAddressService.class, userAddressService))
                .build().start();
        log.info("gRPC server started and listening on port: {}.", port);
        log.info("Following service are available: ");
        server.getServices().stream()
                .forEach(s -> log.info("Service Name: {}", s.getServiceDescriptor().getName()));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down gRPC server.");
            GrpcServer.this.stop();
            log.info("gRPC server shut down successfully.");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void block() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}

