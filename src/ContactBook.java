import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ContactBook {
    static String fullName = "";
    static String numberPhone="";
    static String email="";
    static String Path ="";
    static Scanner ConsoleRead = new Scanner(System.in);

    public static void Contacts() {


        boolean run = true;

        while (run)
        {
            System.out.println("У вас уже есть файл, который вы создавали в этом приложении? для выхода нажмите q");
            String answer = ConsoleRead.nextLine().toLowerCase();
            switch(answer)
            {
                case "да": case "yes":
                System.out.print("Введите путь куда сохранить файл контакты: ");
                try
                {
                    Path = ConsoleRead.nextLine();
                    addContact(Path);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                break;
                case "no": case "нет":
                    createNewFile(Path);

                    break;
                case "q":
                    run = false;;
                    break;
            }
        }
    }

    private static void addContact(String Path)
    {
        try(FileWriter fileWriter = new FileWriter(Path, true )) //true - добавление в файл
        {
            System.out.print("Введите ФИО контакта, которого надо добавить: ");
            fullName = ConsoleRead.nextLine();

            System.out.print("Введите номер телефона: ");
            numberPhone = ConsoleRead.nextLine();

            System.out.print("Введите почту вашего контакта: ");
            email = ConsoleRead.nextLine();

            fileWriter.write("\nФИО: " + fullName + "\t");
            fileWriter.write("Телефон: " + numberPhone +"\t");
            fileWriter.write("Email: " + email + "\n");
            fileWriter.write("________________________");
            System.out.println("Данные успешно сохранены");
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
        }
    }

    private static  void createNewFile(String Path)
    {
        try {
            System.out.print("Введите путь куда вы хотите сохранить файл");
            Path = ConsoleRead.nextLine();
            if(!Path.endsWith("\\") && !Path.endsWith("/"))
            {
                Path = Path+File.separator;
            }
            Path = Path+"contacts.txt";
            File file = new File(Path);
            if(file.createNewFile())
            {
                System.out.println("File created: " + file.getAbsolutePath());
                addContact(Path);
            }
            else
            {
                System.out.print("Файл уже существует\n");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
