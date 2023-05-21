package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserKeyGenerator {
    private String generateRandomKey() {
        int keyLength = 6;
        String valuesToKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder key = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < keyLength; i++) {
            int randoms = random.nextInt(valuesToKey.length());
            key.append(valuesToKey.charAt(randoms));
        }

        return key.toString();
    }

    public void generateKey(User user) {
        if(user.isAuthorized()) {
            String key = generateRandomKey();

            System.out.println("Generated key: " + key);
            System.out.println("The key will be valid for 1 hour");
        } else {
            System.out.println("User is blocked.");
        }
    }


}
