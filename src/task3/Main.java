package task3;

import java.util.Scanner;

/**
 * Демонстрація роботи класу Calendar (завдання 3, варіант 3).
 * Вхідні дані можна передати в командному рядку: java task3.Main 32 2002
 * або ввести з клавіатури.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(" Завдання 3. Клас Calendar \n");

        // 1. Обробка введених даних
        if (args.length == 2) {
            printResult(args[0] + " " + args[1]);
        } else {
            Scanner in = new Scanner(System.in);
            System.out.print("Введіть номер дня та рік (наприклад: 32 2002): ");
            if (in.hasNextLine()) {
                printResult(in.nextLine());
            }
        }

        // 2. Демонстрація конструкторів
        System.out.println("\n Конструктори ");
        Calendar c1 = new Calendar();
        Calendar c2 = new Calendar(32, 2002);
        Calendar c3 = new Calendar("60, 2024");
        Calendar c4 = new Calendar(c2);
        System.out.println("Без параметрів:  " + c1);
        System.out.println("(32, 2002):      " + c2);
        System.out.println("\"60, 2024\":      " + c3 + "  (високосний рік)");
        System.out.println("\"60, 2023\":      " + new Calendar("60 2023"));
        System.out.println("Копія c2:        " + c4);

        // 3. Методи об'єкта
        System.out.println("\n Методи ");
        Calendar last = new Calendar(365, 2025);
        System.out.println("365-й день 2025: " + last);
        System.out.println("Наступний день:  " + last.next());
        System.out.println("Попередній від 1-го дня 2025: " + new Calendar(1, 2025).previous());
        System.out.println("c2: місяць №" + c2.getMonth() + " (" + c2.getMonthName() + "), день "
                + c2.getDayOfMonth() + ", день тижня №" + c2.getDayOfWeek() + " (" + c2.getDayOfWeekName()
                + "), вихідний: " + c2.isWeekend());
        Calendar today = new Calendar(Calendar.toDayOfYear(17, 11, 2026), 2026);
        System.out.println("17.11.2026 — це " + today.getDayOfYear() + "-й день року: " + today);

        // 4. Статичні методи
        System.out.println("\n Статичні методи ");
        System.out.println("2024 високосний: " + Calendar.isLeapYear(2024)
                + ", 1900: " + Calendar.isLeapYear(1900) + ", 2000: " + Calendar.isLeapYear(2000));
        System.out.println("Днів у лютому 2024: " + Calendar.daysInMonth(2, 2024));
        System.out.println("Днів у 2026 році: " + Calendar.daysInYear(2026));

        // 5. equals
        System.out.println("\n equals ");
        System.out.println("c2.equals(c4): " + c2.equals(c4));
        System.out.println("c2.equals(c3): " + c2.equals(c3));

        // 6. Некоректні дані
        System.out.println("\n Некоректні дані ");
        String[] bad = { "366 2023", "0 2020", "abc 2020" };
        for (String s : bad) {
            try {
                new Calendar(s);
            } catch (IllegalArgumentException e) {
                System.out.println("\"" + s + "\": " + e.getMessage());
            }
        }

        System.out.println("\nСтворено об'єктів Calendar: " + Calendar.getCount());
    }

    private static void printResult(String input) {
        try {
            System.out.println("Результат: " + new Calendar(input));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
