package com.example.demo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.dto.CombinedDTO;
import com.example.demo.dto.QuoteDTO;
import com.example.demo.dto.UserDTO;

@Service
public class DashboardService {    
    @Value("${url.user}")
    private String userUrl;

    @Value("${url.quote}")
    private String quoteUrl;

    private final RestClient restClient;

    DashboardService(RestClient restClient) {
        this.restClient = restClient;
    }

    public UserDTO getUser() {
        UserDTO user = restClient.get()
        .uri(userUrl)
        .retrieve()
        .body(UserDTO.class);
        return user;
    }

    public QuoteDTO getQuote() {
        QuoteDTO quote = restClient.get()
        .uri(quoteUrl)
        .retrieve()
        .body(QuoteDTO.class);
        return quote;
    }

    public CombinedDTO getCombinedDTO() throws ExecutionException, InterruptedException{
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
            CompletableFuture<UserDTO> userFuture = CompletableFuture.supplyAsync(this::getUser, executor);
            CompletableFuture<QuoteDTO> quoteFuture = CompletableFuture.supplyAsync(this::getQuote, executor);
            return new CombinedDTO(quoteFuture.join(), userFuture.join());
        }
        
    }
    
}
