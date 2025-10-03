import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    static Scanner consoleRead = new Scanner(System.in);
    public static void main(String[] args) {
        try
        {
            System.out.print("");
            changeProgram();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
    private static void changeProgram() throws IOException
    {
        boolean running = true;
        while (running) {
            System.out.println("""
                    Выберите что вас интересует👋:
                    1) Калькулятор
                    2) Контактная книга
                    3) Чат-бот
                    4) Играть угадай слово
                    5) Анализ текста
                    6) Работа с файлами
                    7) Нажмите 'q' для выхода из программы""");

            System.out.print("Выберите вариант: ");
            String input = consoleRead.nextLine().trim();
            switch (input) {
                case "1": // Калькулятор
                    Calc calc = new Calc();
                    calc.Calculator();
                    break;
                case "2": // Контактная книга
                    ContactBook contacts=new ContactBook();
                    contacts.Contacts();
                    break;
                case "3": // Чат-бот
                    chatBot.chat();
                    break;
                case "4": // Угадай слово
                    gameWords.guessWord();
                    break;
                case "5": // Анализ текста
                    System.out.println("Анализ текста...");
                    break;
                case "6": // Работа с файлами
                    System.out.println("Работа с файлами...");
                    break;
                case "q": case "Q": case "7":
                    System.out.println("Программа закончила свое выполнение");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
            System.out.println(); // Пустая строка для разделения
        }

    }
}