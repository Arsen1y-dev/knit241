package org.knit.solutions.task20.security;

import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class MasterPasswordHolder {
    private char[] masterPassword;

    public void setMasterPassword(char[] masterPassword) {
        this.masterPassword = masterPassword;
    }

    public char[] getMasterPassword() {
        return masterPassword;
    }

    public void clear() {
        if (masterPassword != null) {
            Arrays.fill(masterPassword, '\0');
            masterPassword = null;
        }
    }
} 