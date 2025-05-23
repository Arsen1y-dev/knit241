package org.knit.solutions.task20.model;

import java.util.Objects;

public class PasswordEntry {
    private String site;
    private String login;
    private String encryptedPassword;

    public PasswordEntry() {
    }

    public PasswordEntry(String site, String login, String encryptedPassword) {
        this.site = site;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordEntry that = (PasswordEntry) o;
        return Objects.equals(site, that.site) &&
                Objects.equals(login, that.login) &&
                Objects.equals(encryptedPassword, that.encryptedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site, login, encryptedPassword);
    }

    @Override
    public String toString() {
        return "PasswordEntry{" +
                "site='" + site + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
} 