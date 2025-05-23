package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task20.model.PasswordEntry;
import org.knit.solutions.task20.security.MasterPasswordHolder;
import org.knit.solutions.task20.service.PasswordService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import java.util.Scanner;

@ComponentScan("org.knit.solutions.task20")
@TaskDescription(taskNumber = 20, taskDescription = "Password Manager с Spring и шифрованием")
public class Task20 implements Solution {
    @Override
    public void execute() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Task20.class);
        
        // Получение мастер-пароля
        System.out.print("Введите мастер-пароль: ");
        char[] masterPassword = System.console() != null
                ? System.console().readPassword()
                : new Scanner(System.in).nextLine().toCharArray();
        
        MasterPasswordHolder masterPasswordHolder = context.getBean(MasterPasswordHolder.class);
        masterPasswordHolder.setMasterPassword(masterPassword);
        
        // Добавление хука для очистки мастер-пароля при завершении
        Runtime.getRuntime().addShutdownHook(new Thread(masterPasswordHolder::clear));
        
        PasswordService passwordService = context.getBean(PasswordService.class);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nКоманды:");
            System.out.println("add - Добавить новый пароль");
            System.out.println("list - Показать список паролей");
            System.out.println("copy <сайт> - Скопировать пароль в буфер обмена");
            System.out.println("delete <сайт> - Удалить пароль");
            System.out.println("exit - Выход из программы");
            System.out.print("\nВведите команду: ");
            
            String command = scanner.nextLine().trim();
            
            if (command.equals("exit")) {
                break;
            } else if (command.equals("add")) {
                System.out.print("Введите сайт: ");
                String site = scanner.nextLine();
                System.out.print("Введите логин: ");
                String login = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String password = scanner.nextLine();
                
                passwordService.addPassword(site, login, password);
                System.out.println("Пароль успешно добавлен");
            } else if (command.equals("list")) {
                System.out.println("\nСохраненные пароли:");
                for (PasswordEntry entry : passwordService.getAllPasswords()) {
                    System.out.printf("Сайт: %s, Логин: %s%n", entry.getSite(), entry.getLogin());
                }
            } else if (command.startsWith("copy ")) {
                String site = command.substring(5).trim();
                passwordService.copyPasswordToClipboard(site);
                System.out.println("Пароль скопирован в буфер обмена (будет очищен через 30 секунд)");
            } else if (command.startsWith("delete ")) {
                String site = command.substring(7).trim();
                passwordService.deletePassword(site);
                System.out.println("Пароль успешно удален");
            } else {
                System.out.println("Неизвестная команда");
            }
        }
        
        context.close();
    }
}
