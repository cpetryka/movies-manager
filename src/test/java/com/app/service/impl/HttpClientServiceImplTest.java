package com.app.service.impl;

import com.app.model.MovieAdditionalInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;

import static org.junit.Assert.assertEquals;

class HttpClientServiceImplTest {
    private MockWebServer mockWebServer;
    private HttpClientServiceImpl httpClientService;

    @BeforeEach
    void beforeEach() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        httpClientService = new HttpClientServiceImpl(
                HttpClient.newBuilder().build(),
                new Gson()
        );
    }

    @AfterEach
    void afterEach() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("when request is made successfully")
    void test1() throws Exception {
        // Prepare MockWebServer
        String exampleJson = """
            {
                "production_companies": [
                    {
                        "name": "Production company 1",
                        "origin_country": "US"
                    },
                    {
                        "name": "Production company 2",
                        "origin_country": "US"
                    }
                ],
                "budget": 1000000,
                "revenue": 200000,
                "overview": "Sample description",
                "poster_path": "/poster.jpg"
            }
        """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(exampleJson)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
        );

        // Test
        MovieAdditionalInfo response = httpClientService.get(
                mockWebServer.url("/").toString(),
                new TypeToken<>() { }
        );

        // Assertions
        assertEquals(
                "MovieAdditionalInfo(productionCompanies=[ProductionCompany(name='Production company 1', originCountry='US'), ProductionCompany(name='Production company 2', originCountry='US')], budget=1000000, revenue=200000, overview='Sample description', posterUrl='https://image.tmdb.org/t/p/original/poster.jpg')",
                response.toString()
        );

        // Check if request was made as expected
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/", recordedRequest.getPath());

        // Solution adopted from:
        // https://rieckpil.de/how-to-test-java-http-client-usages-e-g-okhttp-apache-httpclient/
    }
}
