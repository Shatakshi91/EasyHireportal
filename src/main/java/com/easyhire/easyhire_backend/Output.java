package com.easyhire.easyhire_backend;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Output{
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("password123"));
    }
}