package com.ahastudio.components;

import java.security.MessageDigest;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageDigester {
    private final MessageDigest messageDigest1;
    private final MessageDigest messageDigest2;

    public MessageDigester(MessageDigest messageDigest1,
                           MessageDigest messageDigest2) {
        this.messageDigest1 = messageDigest1;
        this.messageDigest2 = messageDigest2;
    }

    public String digest(String source) {
        byte[] digest1 = messageDigest1.digest(source.getBytes());
        byte[] digest2 = messageDigest2.digest(source.getBytes());
        return "[Digest #1]" +
                "\n알고리즘: " + messageDigest1.getAlgorithm() +
                "\n결과: " + bytesToHex(digest1) +
                " (" + digest1.length + ")" +
                "\n[Digest #2]" +
                "\n알고리즘: " + messageDigest2.getAlgorithm() +
                "\n결과: " + bytesToHex(digest2) +
                " (" + digest2.length + ")";
    }

    private String bytesToHex(byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> String.format("%02X", bytes[i] & 0xFF))
                .collect(Collectors.joining());
    }
}
