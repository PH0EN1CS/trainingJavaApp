import java.io.*;
import java.util.*;

public class chatBot {

        static boolean run = true;
        static Scanner console = new Scanner(System.in);

        // Храним список возможных ответов для каждого ключа
        static Map<String, List<String>> answers = new HashMap<>();

        public static void chat() {
            // Загружаем базу при старте
            try {
                loadAnswers();
            } catch (IOException e) {
                System.out.println("⚠ Ошибка загрузки базы: " + e.getMessage());
            }

            System.out.println("🤖 Привет! Я твой консольный чат-бот. Напиши что-нибудь или 'exit' для выхода.");

            while (run) {
                System.out.print("Вы: ");
                String userMessage = console.nextLine().trim().toLowerCase();
                botAnswer(userMessage);
            }
        }

        private static void botAnswer(String userMessage) {
            if (userMessage.equals("exit")) {
                System.out.println("Бот: Пока 👋");
                run = false;
                return;
            }

            if (answers.containsKey(userMessage)) {
                List<String> responses = answers.get(userMessage);
                Random rand = new Random();
                String randomAnswer = responses.get(rand.nextInt(responses.size()));
                System.out.println("Бот: " + randomAnswer);
            } else {
                System.out.println("Бот: Я пока не знаю, что ответить 😅");
                System.out.print("👉 Что мне отвечать на '" + userMessage + "'? ");
                String newAnswer = console.nextLine();

                // добавляем в память
                answers.put(userMessage, new ArrayList<>(List.of(newAnswer)));

                // и сохраняем в файл
                saveAnswer(userMessage, newAnswer);

                System.out.println("✅ Спасибо! Теперь я запомнил.");
            }
        }

        private static void loadAnswers() throws IOException {
            File file = new File("answers.txt");
            if (!file.exists()) {
                System.out.println("⚠ Файл базы не найден, создаю новый...");
                file.createNewFile();
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("=")) {
                        String[] parts = line.split("=");

                        // Первый элемент — ключ (фраза пользователя)
                        String key = parts[0].toLowerCase();

                        // Остальные — варианты ответов
                        List<String> responses = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));

                        answers.put(key, responses);
                    }
                }
                System.out.println("✅ Загружено " + answers.size() + " фраз из " + "answers.txt");
            }
        }

        private static void saveAnswer(String key, String value) {
            try (FileWriter writer = new FileWriter("answers.txt", true)) {
                writer.write(key + "=" + value + "\n");
            } catch (IOException e) {
                System.out.println("⚠ Ошибка сохранения: " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            chat();
        }
    }


