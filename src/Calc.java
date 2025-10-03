import javax.xml.crypto.KeySelector;
import java.util.Scanner;

public class Calc {
    public void Calculator()
    {
        Scanner ConsoleRead = new Scanner(System.in);
        boolean run = true;
        double num1;
        double num2;
        double result;
        while(run)
        {
            System.out.print("Если вы хотите закончить выполнение программы нажмите q \nВведите первое число:");
            if(ConsoleRead.hasNextDouble())
            {
                num1 = ConsoleRead.nextDouble();
                System.out.print("Введите второе число: ");
                if(ConsoleRead.hasNextDouble())
                {
                    num2 = ConsoleRead.nextDouble();
                    ConsoleRead.nextLine();
                    System.out.print("Введите операцию, которую надо выполнить: ");
                    String answer = ConsoleRead.nextLine();
                    switch(answer)
                    {
                        case "*":
                            result = num1*num2;
                            System.out.println(result);
                            break;
                        case "/":
                            result = num1/num2;
                            System.out.println(result);
                            break;
                        case "+":
                            result = num1+num2;
                            System.out.println(result);
                            break;
                        case "-":
                            result = num1 -num2;
                            System.out.println(result);
                            break;
                    }
                }
                else
                {
                    System.out.println("Вы ввели неправильное число");
                }
            }
            else if(ConsoleRead.nextLine().equalsIgnoreCase("q"))
            {
                run=false;
            }
            else
            {
                System.out.println("Вы ввели неправильное число");
            }

        }
    }
}
