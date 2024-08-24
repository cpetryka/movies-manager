package com.app.application.service.impl;

import com.app.application.service.HttpClientService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class HttpClientServiceImpl implements HttpClientService {
    private final HttpClient httpClient;
    private final Gson gson;

    @SneakyThrows
    private static HttpRequest requestGet(String url) {
        return HttpRequest.newBuilder()
                .uri(new URI(url))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
    }

    @SneakyThrows
    @Override
    public<T> T get(String url, TypeToken<T> type) {
        var response = httpClient.send(
                requestGet(url),
                HttpResponse.BodyHandlers.ofString()
        );

        return gson.fromJson(response.body(), type);
    }
}
