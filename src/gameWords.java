import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class gameWords {
    // Убрали static run - используем локальную переменную
    static Scanner console = new Scanner(System.in);
    static List<String> words = new ArrayList<>();
    static String userInputWord = "";
    static String word = "";
    static int userScore = 0;
    static int botScore = 0;
    static StringBuilder progress;
    static int tryCount = 7;

    public static void guessWord() {
        boolean gameRunning = true; // Локальная переменная для игры

        try {
            loadWords("words.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        while(gameRunning) {
            System.out.print("1) exit-выйти\n2) сдаться\n Введите слово чтобы угадать загаданное ботом: ");
            userInputWord = console.nextLine().trim().toLowerCase();
            gameRunning = CheckInput(userInputWord); // Получаем статус продолжения игры
        }
    }

    private static String changeWord() {
        tryCount = 7;
        Random rand = new Random();
        String randomWord = words.get(rand.nextInt(words.size()));
        progress = new StringBuilder("_".repeat(randomWord.length()));
        return randomWord;
    }

    public static boolean CheckInput(String userInputWord) {
        boolean found = false;

        // Обработка специальных команд
        if (userInputWord.equalsIgnoreCase("exit") || userInputWord.equalsIgnoreCase("1")) {
            System.out.println("Бот: Пока 👋");
            return false; // Завершаем игру
        }
        else if (userInputWord.equalsIgnoreCase("сдаться") || userInputWord.equalsIgnoreCase("2")) {
            System.out.println("Вы сдались бот получает балл");
            botScore++;
            startNewRound(); // Запускаем новый раунд
            return true; // Продолжаем игру
        }

        // Проверка полного слова
        if (userInputWord.equalsIgnoreCase(word)) {
            System.out.println("Вы угадали слово!!!");
            userScore++;
            startNewRound(); // Запускаем новый раунд
            return true;
        }

        // Проверка одной буквы
        else if (userInputWord.length() == 1) {
            char guess = userInputWord.charAt(0);
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess && progress.charAt(i) == '_') {
                    progress.setCharAt(i, guess);
                    found = true;
                }
            }

            if (found) {
                System.out.println("Угаданные буквы: " + progress);
                if (progress.toString().equals(word)) {
                    System.out.println("Вы угадали слово полностью!!!");
                    userScore++;
                    startNewRound();
                }
            } else {
                tryCount--;
                System.out.println("Такой буквы нет в слове\nКоличество оставшихся попыток = " + tryCount);
            }
        }
        // Проверка части слова
        else if (userInputWord.length() > 1 && userInputWord.length() <= word.length()) {
            for (int i = 0; i < userInputWord.length(); i++) {
                char currentChar = userInputWord.charAt(i);
                for(int j = 0; j < word.length(); j++) {
                    if (currentChar == word.charAt(j) && progress.charAt(j) == '_') {
                        progress.setCharAt(j, currentChar);
                        found = true;
                    }
                }
            }
            tryCount--;
            if (found) {
                System.out.println("Угаданные буквы: " + progress);
                if (progress.toString().equals(word)) {
                    System.out.println("Вы угадали слово полностью!!!");
                    userScore++;
                    startNewRound();
                }
            } else {
                tryCount--;
                System.out.println("Буквы не совпали в этих позициях\nКоличество оставшихся попыток = " + tryCount);
            }
        }
        // Некорректный ввод
        else {
            System.out.println("Введите одну букву или попробуйте угадать слово целиком.");
        }

        // Проверка окончания попыток
        if(tryCount <= 0) {
            System.out.println("У вас закончились попытки. Загаданное слово: " + word);
            botScore++;
            startNewRound();
        }

        return true; // Продолжаем игру
    }

    private static void startNewRound() {
        word = changeWord();
        System.out.println("\n=================================");
        System.out.println("Ваш рекорд: " + userScore + "\nРекорд бота: " + botScore);
        System.out.println("Новое слово: " + progress);
        System.out.println("=================================\n");
    }

    private static void loadWords(String fileName) throws IOException {
        File file = new File(fileName);

        if(!file.exists()) {
            System.out.println("Файл-словарь не найден, создается новый.");
            file.createNewFile();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim().toLowerCase();
                if (!trimmed.isEmpty()) {
                    words.add(trimmed);
                }
            }

            if (!words.isEmpty()) {
                word = changeWord();
                System.out.println("Ваш рекорд: " + userScore + "\nРекорд бота: " + botScore);
                System.out.println("Новое слово: " + progress);
            } else {
                System.out.println("Файл words.txt пуст. Добавьте слова в формате: каждое слово на новой строке.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}