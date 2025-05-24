package org.knit.solutions.task20.crypto;

public interface EncryptionService {
    String encrypt(String password, char[] masterPassword);
    String decrypt(String encryptedPassword, char[] masterPassword);
} 