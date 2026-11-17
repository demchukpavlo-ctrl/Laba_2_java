import java.util.Scanner;

/**
 * Лабораторна робота 2. Головне меню для вибору завдання.
 * Виконав: Демчук Павло Мар'янович.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\n Лабораторна робота 2 ");
            System.out.println("1 - Завдання 1 (варіант 9): клас Payment (зарплата)");
            System.out.println("2 - Завдання 2 (варіант 10): клас Rectangle (прямокутник)");
            System.out.println("3 - Завдання 3 (варіант 3): клас Calendar");
            System.out.println("0 - Вихід");
            System.out.print("Ваш вибір: ");

            if (!in.hasNextLine())
                return;
            String choice = in.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1" -> task1.Main.main(new String[0]);
                case "2" -> task2.Main.main(new String[0]);
                case "3" -> runTask3(in);
                case "0" -> {
                    System.out.println("До побачення!");
                    return;
                }
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    /**
     * Завдання 3 читає дані з клавіатури, тому вводимо їх тут і передаємо як
     * аргументи.
     */
    private static void runTask3(Scanner in) {
        System.out.print("Введіть номер дня та рік (наприклад: 32 2002): ");
        if (!in.hasNextLine())
            return;
        String[] parts = in.nextLine().trim().split("[\\s,;]+");
        if (parts.length == 2) {
            task3.Main.main(parts);
        } else {
            System.out.println("Потрібно ввести два числа.");
        }
    }
}
