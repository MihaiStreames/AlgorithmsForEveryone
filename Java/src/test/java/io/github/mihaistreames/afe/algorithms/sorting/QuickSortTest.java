package io.github.mihaistreames.afe.algorithms.sorting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class QuickSortTest {
    private static final String API_URL = "http://127.0.0.1:5001/data?type=random&size=100";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    /**
     * Fetches a list of integers from the testing API.
     *
     * @return A list of integers.
     */
    private List<Integer> getTestDataFromApi() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                fail("Failed to fetch data from API, status code: " + response.statusCode());
            }

            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            return gson.fromJson(response.body(), listType);

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Failed to connect to TestingAPI: " + e.getMessage());
            return Collections.emptyList(); // Should not be reached
        }
    }

    @Test
    void testQuickSort() {
        System.out.println("Fetching random test data from API for QuickSort...");
        List<Integer> apiData = getTestDataFromApi();

        List<Integer> expectedSortedData = new ArrayList<>(apiData);
        Collections.sort(expectedSortedData);

        QuickSort.sort(apiData);

        assertEquals(expectedSortedData, apiData);
        System.out.println("QuickSort test passed!");
    }
}