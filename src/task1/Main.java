package task1;

/** Демонстрація роботи класу Payment (завдання 1, варіант 9). */
public class Main {

    public static void main(String[] args) {
        System.out.println(" Завдання 1. Клас Payment (зарплата) \n");

        // 1. Конструктор без параметрів (значення з ініціалізатора)
        Payment p1 = new Payment();
        System.out.println("p1 (без параметрів): " + p1);

        // 2. Конструктор з прізвищем, ім'ям та окладом
        Payment p2 = new Payment("Демчук", "Павло", 25000);
        System.out.println("p2 (прізвище, ім'я, оклад): " + p2);

        // 3. Повний конструктор
        Payment p3 = new Payment("Іваненко", "Олена", 30000, 2015, 15, 23, 18, 22);
        System.out.println("p3 (повний): " + p3);

        // 4. Конструктор копіювання
        Payment p4 = new Payment(p3);
        System.out.println("p4 (копія p3): " + p4);

        System.out.println("\n Обчислення для p3 ");
        System.out.printf("Нарахована сума: %.2f грн%n", p3.calculateAccrued());
        System.out.printf("Утримана сума:   %.2f грн%n", p3.calculateWithheld());
        System.out.printf("На руки:         %.2f грн%n", p3.getNetAmount());
        System.out.println("Стаж роботи:     " + p3.calculateExperience() + " р.");

        System.out.println("\n Сетери ");
        p2.setHireYear(2020);
        p2.setBonusPercent(10);
        p2.setDays(20, 21);
        System.out.println("p2 після змін: " + p2);

        System.out.println("\n equals ");
        System.out.println("p3.equals(p4): " + p3.equals(p4));
        System.out.println("p3.equals(p2): " + p3.equals(p2));
        p4.setSalary(31000);
        System.out.println("p4 після зміни окладу, p3.equals(p4): " + p3.equals(p4));

        System.out.println("\n Статичні методи ");
        System.out.println("Створено об'єктів: " + Payment.getCount());
        System.out.println("Податок за замовчуванням: " + Payment.getDefaultTaxPercent() + "%");
        System.out.println("Стаж з 2010 по 2025: " + Payment.experience(2010, 2025) + " р.");
        Payment.setDefaultTaxPercent(19.5);
        Payment p5 = new Payment("Коваль", "Петро", 20000);
        System.out.println("Новий податок за замовчуванням 19.5%, p5: " + p5);
        System.out.println("Створено об'єктів: " + Payment.getCount());

        System.out.println("\n Перевірка некоректних даних ");
        try {
            p5.setDays(25, 22);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
        try {
            new Payment("Тест", "Тест", -100);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
