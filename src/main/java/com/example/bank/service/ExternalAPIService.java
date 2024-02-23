package com.example.bank.service;

import org.springframework.web.client.RestTemplate;

public class ExternalAPIService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void getPosts() {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "/posts";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("Response from API: " + response);
    }

}
