import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class textAnalyze {
    static Scanner console = new Scanner(System.in);
    static String text;
    public static void menu()
    {
                try
                {
                    fileAnalyze();
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
    }

    private static void fileAnalyze() throws IOException
    {
        System.out.print("Введите путь к файлу");
        String path = console.nextLine();
        List<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = reader.readLine())!=null)
        {
            String[] parts = line.trim().split("\\s+");
            for(String w : parts)
            {
                if(!w.isEmpty())
                {
                    words.add(w);
                }
            }
        }
        int totalWords = words.size();
        int totalLetters=0;
        char totalChar = 0;
        for (String w : words) {
            totalLetters += w.replaceAll("[^a-zA-Zа-яА-Я]", "").length(); // считаем только буквы
        }
        System.out.println("Всего слов: " + totalWords);
        System.out.println("Всего букв: " + totalLetters);
    }
}
