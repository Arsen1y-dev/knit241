package org.knit.solutions.task20.service;

import org.knit.solutions.task20.clipboard.ClipboardService;
import org.knit.solutions.task20.crypto.EncryptionService;
import org.knit.solutions.task20.model.PasswordEntry;
import org.knit.solutions.task20.repository.PasswordRepository;
import org.knit.solutions.task20.security.MasterPasswordHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class PasswordService {
    private static final Logger logger = LoggerFactory.getLogger(PasswordService.class);
    
    private final PasswordRepository repository;
    private final EncryptionService encryptionService;
    private final ClipboardService clipboardService;
    private final MasterPasswordHolder masterPasswordHolder;

    public PasswordService(PasswordRepository repository,
                          EncryptionService encryptionService,
                          ClipboardService clipboardService,
                          MasterPasswordHolder masterPasswordHolder) {
        this.repository = repository;
        this.encryptionService = encryptionService;
        this.clipboardService = clipboardService;
        this.masterPasswordHolder = masterPasswordHolder;
    }

    public void addPassword(String site, String login, String password) {
        try {
            String encryptedPassword = encryptionService.encrypt(password, masterPasswordHolder.getMasterPassword());
            PasswordEntry entry = new PasswordEntry(site, login, encryptedPassword);
            repository.save(entry);
            logger.info("Пароль добавлен для сайта: {}", site);
        } catch (Exception e) {
            logger.error("Ошибка при добавлении пароля для сайта {}: {}", site, e.getMessage());
            throw e;
        }
    }

    public List<PasswordEntry> getAllPasswords() {
        try {
            List<PasswordEntry> passwords = repository.findAll();
            logger.info("Получен список паролей, количество: {}", passwords.size());
            return passwords;
        } catch (Exception e) {
            logger.error("Ошибка при получении списка паролей: {}", e.getMessage());
            throw e;
        }
    }

    public void copyPasswordToClipboard(String site) {
        try {
            repository.findBySite(site).ifPresent(entry -> {
                String decryptedPassword = encryptionService.decrypt(
                    entry.getEncryptedPassword(),
                    masterPasswordHolder.getMasterPassword()
                );
                clipboardService.copyToClipboard(decryptedPassword);
                logger.info("Пароль скопирован в буфер обмена для сайта: {}", site);
                
                // Clear clipboard after 30 seconds
                CompletableFuture.delayedExecutor(30, TimeUnit.SECONDS)
                    .execute(() -> {
                        clipboardService.clearClipboard();
                        logger.info("Буфер обмена очищен для сайта: {}", site);
                    });
            });
        } catch (Exception e) {
            logger.error("Ошибка при копировании пароля для сайта {}: {}", site, e.getMessage());
            throw e;
        }
    }

    public void deletePassword(String site) {
        try {
            repository.delete(site);
            logger.info("Пароль удален для сайта: {}", site);
        } catch (Exception e) {
            logger.error("Ошибка при удалении пароля для сайта {}: {}", site, e.getMessage());
            throw e;
        }
    }
} 