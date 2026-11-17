package task2;

/** Демонстрація роботи класу Rectangle (завдання 2, варіант 10). */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Завдання 2. Клас Rectangle (прямокутник) ===\n");

        Rectangle r1 = new Rectangle();            // ініціалізатор: 1×1
        Rectangle r2 = new Rectangle(3, 4);
        Rectangle r3 = new Rectangle(4, 3);        // ті самі сторони, інший порядок
        Rectangle r4 = new Rectangle(6, 8);        // подібний до r2
        Rectangle r5 = new Rectangle(2, 6);        // та сама площа, що й r2 (12), але не подібний
        Rectangle r6 = new Rectangle(r2);          // копія
        Rectangle sq = Rectangle.square(5);        // статичний фабричний метод

        System.out.println("r1 (без параметрів): " + r1);
        System.out.println("r2 (3, 4):           " + r2);
        System.out.println("r3 (4, 3):           " + r3);
        System.out.println("r4 (6, 8):           " + r4);
        System.out.println("r5 (2, 6):           " + r5);
        System.out.println("r6 (копія r2):       " + r6);
        System.out.println("sq (квадрат 5):      " + sq);

        System.out.println("\n--- Периметр, площа, діагональ ---");
        System.out.printf("r2: P = %.2f, S = %.2f, d = %.2f%n", r2.perimeter(), r2.area(), r2.diagonal());
        System.out.println("sq є квадратом: " + sq.isSquare() + ", r2 є квадратом: " + r2.isSquare());

        System.out.println("\n--- Порівняння ---");
        System.out.println("r2 vs r3: " + r2.compareWith(r3));
        System.out.println("r2 vs r5: " + r2.compareWith(r5));
        System.out.println("r2 vs r4: " + r2.compareWith(r4));
        System.out.println("r4 vs r1: " + r4.compareWith(r1));
        System.out.println("r2.compareTo(r4) = " + r2.compareTo(r4));
        System.out.println("Більший з r2 і sq: " + Rectangle.max(r2, sq));

        System.out.println("\n--- Подібність ---");
        System.out.println("r2 подібний r4: " + r2.isSimilar(r4)
                + " (коефіцієнт " + r4.similarityCoefficient(r2) + ")");
        System.out.println("r2 подібний r5: " + r2.isSimilar(r5));
        System.out.println("r1 подібний sq: " + r1.isSimilar(sq));
        System.out.println("r2.scale(2.5) = " + r2.scale(2.5)
                + ", подібний r2: " + r2.isSimilar(r2.scale(2.5)));

        System.out.println("\n--- equals / hashCode ---");
        System.out.println("r2.equals(r3): " + r2.equals(r3) + ", hash: " + (r2.hashCode() == r3.hashCode()));
        System.out.println("r2.equals(r6): " + r2.equals(r6));
        System.out.println("r2.equals(r4): " + r2.equals(r4));

        System.out.println("\n--- Сетери ---");
        r6.setA(10);
        System.out.println("r6 після setA(10): " + r6);
        try {
            r6.setB(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        System.out.println("\nСтворено прямокутників: " + Rectangle.getCount());
    }
}
