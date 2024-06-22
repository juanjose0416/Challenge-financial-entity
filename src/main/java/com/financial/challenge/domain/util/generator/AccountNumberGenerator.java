package com.financial.challenge.domain.util.generator;

public class AccountNumberGenerator {

    public static String generateNumber(){
        return String.format("%08d", (int)(Math.random() * 100000000));
    }
}
