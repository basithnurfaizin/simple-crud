package com.nurfaizin.backend.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SecureUtil {

    public static String getMd5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    private static String shuffle(String base, String salt) {
        int halfLength = salt.length()/2;
        return salt.substring(0, halfLength) + base + salt.substring(halfLength);
    }

    private static String infix(String base, String salt) {
        // base length = 96 , salt =32
        StringBuilder stringBuilder = new StringBuilder(base);
        for (int i=15; i>=0; i--){
            String infix = salt.substring(2*i, 2*i+2);
            stringBuilder.insert(6*i, infix);
        }
        return stringBuilder.toString();
    }

    private static String extract(String salted) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<16;i++) {
            stringBuilder.append(salted, 8*i, 8*i+2);
        }
        return  stringBuilder.toString();
    }

    public static String generateRandomToken(String input) {
        String candidates = input.replaceAll("\\s+", "");
        List<Character> characters = new ArrayList<>();
        for(char c : candidates.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(candidates.length());
        while (characters.size() != 0 ) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    
}
