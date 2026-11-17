package task2;

/**
 * Завдання 2, варіант 10.
 * Клас «прямокутник»: задається довжинами двох сторін; методи для
 * обчислення периметра, площі, порівняння та перевірки подібності
 * з іншим прямокутником.
 */
public class Rectangle implements Comparable<Rectangle> {

    /** Точність порівняння дійсних чисел. */
    public static final double EPS = 1e-9;
    /** Кількість створених прямокутників. */
    private static int count;

    private double a;   // перша сторона
    private double b;   // друга сторона

    static {
        count = 0;
    }

    /* нестатичний ініціалізатор: прямокутник 1×1 за замовчуванням */
    {
        a = 1.0;
        b = 1.0;
        count++;
    }

    /** Одиничний прямокутник 1×1. */
    public Rectangle() {
    }

    /** Квадрат зі стороною side. */
    public Rectangle(double side) {
        this(side, side);
    }

    /** Прямокутник зі сторонами a та b. */
    public Rectangle(double a, double b) {
        setSides(a, b);
    }

    /** Конструктор копіювання. */
    public Rectangle(Rectangle other) {
        this(other.a, other.b);
    }

    /* ---------- методи ---------- */

    public double perimeter() {
        return 2 * (a + b);
    }

    public double area() {
        return a * b;
    }

    public double diagonal() {
        return Math.hypot(a, b);
    }

    public boolean isSquare() {
        return Math.abs(a - b) < EPS;
    }

    /**
     * Порівняння з іншим прямокутником за площею.
     * @return від'ємне число, 0 або додатне число, якщо цей прямокутник менший, рівний або більший
     */
    @Override
    public int compareTo(Rectangle other) {
        double diff = area() - other.area();
        if (Math.abs(diff) < EPS) return 0;
        return diff < 0 ? -1 : 1;
    }

    /** Текстовий результат порівняння з іншим прямокутником (за площею). */
    public String compareWith(Rectangle other) {
        int c = compareTo(other);
        if (c == 0) return equals(other) ? "рівні" : "рівновеликі (однакова площа)";
        return c < 0 ? "менший" : "більший";
    }

    /** Подібність: відношення більшої сторони до меншої однакове. */
    public boolean isSimilar(Rectangle other) {
        return Math.abs(ratio() - other.ratio()) < EPS;
    }

    /** Відношення більшої сторони до меншої. */
    public double ratio() {
        return Math.max(a, b) / Math.min(a, b);
    }

    /** Коефіцієнт подібності до іншого прямокутника (або -1, якщо не подібні). */
    public double similarityCoefficient(Rectangle other) {
        if (!isSimilar(other)) return -1;
        return Math.max(a, b) / Math.max(other.a, other.b);
    }

    /** Масштабування сторін у k разів. */
    public Rectangle scale(double k) {
        return new Rectangle(a * k, b * k);
    }

    /* ---------- статичні методи ---------- */

    public static int getCount() {
        return count;
    }

    /** Фабричний метод для квадрата. */
    public static Rectangle square(double side) {
        return new Rectangle(side);
    }

    /** Повертає прямокутник з більшою площею. */
    public static Rectangle max(Rectangle r1, Rectangle r2) {
        return r1.compareTo(r2) >= 0 ? r1 : r2;
    }

    /* ---------- гетери та сетери ---------- */

    public double getA() { return a; }
    public double getB() { return b; }

    public void setA(double a) { setSides(a, this.b); }
    public void setB(double b) { setSides(this.a, b); }

    public void setSides(double a, double b) {
        if (a <= 0 || b <= 0 || Double.isNaN(a) || Double.isNaN(b)) {
            throw new IllegalArgumentException("Сторони прямокутника мають бути додатними");
        }
        this.a = a;
        this.b = b;
    }

    /* ---------- перевизначені методи Object ---------- */

    @Override
    public String toString() {
        return String.format("Rectangle{a=%.2f, b=%.2f, P=%.2f, S=%.2f}", a, b, perimeter(), area());
    }

    /** Прямокутники рівні, якщо мають однакові сторони (порядок сторін не важливий). */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rectangle other)) return false;
        return (Double.compare(a, other.a) == 0 && Double.compare(b, other.b) == 0)
                || (Double.compare(a, other.b) == 0 && Double.compare(b, other.a) == 0);
    }

    @Override
    public int hashCode() {
        // симетричний хеш, щоб узгоджуватися з equals (a×b == b×a)
        return Double.hashCode(Math.min(a, b)) * 31 + Double.hashCode(Math.max(a, b));
    }
}
