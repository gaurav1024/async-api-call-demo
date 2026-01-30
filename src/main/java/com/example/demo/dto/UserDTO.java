package com.example.demo.dto;

public record UserDTO(Long id, 
    String name, 
    String username, 
    String email, 
    Address address, 
    String phone, 
    String website, 
    Company company) {
        public static UserDTO GUEST() {
            return new UserDTO(0L, "Guest user", "", "", new Address("", "", "", "", new Geo("", "")), "", "", new Company("", "", ""));
        }
    }

record Address (String street, String suite,
    String city,
    String zipcode, Geo geo) {}

record Company (String name, 
    String catchPhrase, 
    String bs) {}

record Geo (String lat, String lng) {}