package org.knit.solutions.task20.repository;

import org.knit.solutions.task20.model.PasswordEntry;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPasswordRepository implements PasswordRepository {
    private final Map<String, PasswordEntry> storage = new ConcurrentHashMap<>();

    @Override
    public void save(PasswordEntry entry) {
        storage.put(entry.getSite(), entry);
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
    }

    @Override
    public void saveAll(List<PasswordEntry> entries) {
        entries.forEach(this::save);
    }
} 