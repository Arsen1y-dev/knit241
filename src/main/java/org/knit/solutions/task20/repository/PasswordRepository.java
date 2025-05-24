package org.knit.solutions.task20.repository;

import org.knit.solutions.task20.model.PasswordEntry;
import java.util.List;
import java.util.Optional;

public interface PasswordRepository {
    void save(PasswordEntry entry);
    Optional<PasswordEntry> findBySite(String site);
    List<PasswordEntry> findAll();
    void delete(String site);
    void saveAll(List<PasswordEntry> entries);
} 