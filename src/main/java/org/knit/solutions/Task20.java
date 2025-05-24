package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task20.PasswordManagerApp;


@TaskDescription(taskNumber = 20, taskDescription = "Password Manager с Spring и шифрованием")
public class Task20 implements Solution {
    @Override
    public void execute() {
        PasswordManagerApp.main(null);
    }
}
