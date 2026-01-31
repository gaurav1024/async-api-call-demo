package com.example.demo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.dto.CombinedDTO;
import com.example.demo.dto.QuoteDTO;
import com.example.demo.dto.UserDTO;

@Service
public class DashboardService {    
    @Value("${clients.user.base-url}")
    private String userUrl;

    @Value("${clients.quote.base-url}")
    private String quoteUrl;

    private final RestClient userRestClient;

    private final RestClient quoteRestClient;

    DashboardService(@Qualifier("userRestClient") RestClient userRestClient, @Qualifier("quoteRestClient") RestClient quoteRestClient) {
        this.userRestClient = userRestClient;   
        this.quoteRestClient = quoteRestClient;
    }

    public UserDTO getUser() {
        UserDTO user = userRestClient.get()
        .uri(userUrl)
        .retrieve()
        .body(UserDTO.class);
        return user;
    }

    public QuoteDTO getQuote() {
        QuoteDTO quote = quoteRestClient.get()
        .uri(quoteUrl)
        .retrieve()
        .body(QuoteDTO.class);
        return quote;
    }

    public CombinedDTO getCombinedDTO(){
        CompletableFuture<UserDTO> userFuture = CompletableFuture.supplyAsync(this::getUser).exceptionally(ex -> UserDTO.GUEST());
        CompletableFuture<QuoteDTO> quoteFuture = CompletableFuture.supplyAsync(this::getQuote).exceptionally(ex -> QuoteDTO.DEFAULT());
        return new CombinedDTO(quoteFuture.join(), userFuture.join());
    }
    
}
