package com.example.workflow;

import com.example.workflow.classes.APOD;
import com.example.workflow.classes.JsonBodyHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.workflow.classes.ServerStartupLogger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@SpringBootApplication
public class Application {


    public static void main(String... args) {
        SpringApplication.run(Application.class, args);

        ServerStartupLogger logger = new ServerStartupLogger();

        logger.serverLogTimestamp();

        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                URI.create("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"))
                .header("accept", "application/json")
                .build();

        // use the client to send the request
        try {

            var response = client.send(request, new JsonBodyHandler<>(APOD.class));

            System.out.println(response.body().get().title);

            // the response:
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
