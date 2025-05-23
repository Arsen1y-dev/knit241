package org.knit.solutions.task20.clipboard;

import org.springframework.stereotype.Service;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

@Service
public class SystemClipboardService implements ClipboardService {
    private final Clipboard clipboard;

    public SystemClipboardService() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    @Override
    public void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }

    @Override
    public void clearClipboard() {
        clipboard.setContents(new StringSelection(""), null);
    }
} 