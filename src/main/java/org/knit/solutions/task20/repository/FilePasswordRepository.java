package org.knit.solutions.task20.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knit.solutions.task20.crypto.EncryptionService;
import org.knit.solutions.task20.model.PasswordEntry;
import org.knit.solutions.task20.security.MasterPasswordHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FilePasswordRepository implements PasswordRepository {
    private static final Logger logger = LoggerFactory.getLogger(FilePasswordRepository.class);
    private static final String STORAGE_FILE = "passwords.json";
    
    private final Map<String, PasswordEntry> storage = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    private final EncryptionService encryptionService;
    private final MasterPasswordHolder masterPasswordHolder;

    public FilePasswordRepository(EncryptionService encryptionService, MasterPasswordHolder masterPasswordHolder) {
        this.objectMapper = new ObjectMapper();
        this.encryptionService = encryptionService;
        this.masterPasswordHolder = masterPasswordHolder;
        loadFromFile();
    }

    @Override
    public void save(PasswordEntry entry) {
        storage.put(entry.getSite(), entry);
        saveToFile();
    }

    @Override
    public Optional<PasswordEntry> findBySite(String site) {
        return Optional.ofNullable(storage.get(site));
    }

    @Override
    public List<PasswordEntry> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String site) {
        storage.remove(site);
        saveToFile();
    }

    @Override
    public void saveAll(List<PasswordEntry> entries) {
        entries.forEach(this::save);
    }

    private void saveToFile() {
        try {
            // Шифруем весь файл с паролями
            String json = objectMapper.writeValueAsString(storage.values());
            String encrypted = encryptionService.encrypt(json, masterPasswordHolder.getMasterPassword());
            objectMapper.writeValue(new File(STORAGE_FILE), encrypted);
            logger.info("Пароли сохранены в файл");
        } catch (IOException e) {
            logger.error("Ошибка при сохранении паролей в файл: {}", e.getMessage());
            throw new RuntimeException("Не удалось сохранить пароли", e);
        }
    }

    private void loadFromFile() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) {
            logger.info("Файл с паролями не найден, создаем новое хранилище");
            return;
        }

        try {
            String encrypted = objectMapper.readValue(file, String.class);
            String json = encryptionService.decrypt(encrypted, masterPasswordHolder.getMasterPassword());
            List<PasswordEntry> entries = objectMapper.readValue(json, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, PasswordEntry.class));
            
            entries.forEach(entry -> storage.put(entry.getSite(), entry));
            logger.info("Загружено {} паролей из файла", entries.size());
        } catch (IOException e) {
            logger.error("Ошибка при загрузке паролей из файла: {}", e.getMessage());
            throw new RuntimeException("Не удалось загрузить пароли", e);
        }
    }
} 