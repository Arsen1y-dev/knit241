package org.knit.solutions.task12;

public class TextEditor {
    private String currentText;
    private Caretaker caretaker;

    public TextEditor(Caretaker caretaker) {
        this.currentText = "";
        this.caretaker = caretaker;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public void addText(String text) {
        this.currentText += text;
    }

    public void saveState() {
        this.caretaker.saveState(currentText);
    }

    public void undo() {
        this.currentText = this.caretaker.popLastState();
    }
}





