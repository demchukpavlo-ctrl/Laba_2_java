package task1;

import java.time.Year;
import java.util.Objects;

/**
 * Завдання 1, варіант 9.
 * Клас Payment (зарплата): прізвище, ім'я, оклад, рік поступлення на роботу,
 * відсоток надбавки, податок, кількість відпрацьованих днів у місяці,
 * кількість робочих днів у місяці, нарахована та утримана сума.
 */
public class Payment {

    /* статичні поля */
    /** Кількість створених об'єктів Payment. */
    private static int count;
    /** Податок за замовчуванням, %: ПДФО 18% + військовий збір 5%. */
    private static double defaultTaxPercent = 23.0;

    /* поля об'єкта */
    private String lastName; // прізвище
    private String firstName; // ім'я
    private double salary; // оклад, грн
    private int hireYear; // рік поступлення на роботу
    private double bonusPercent; // відсоток надбавки
    private double taxPercent; // податок, %
    private int workedDays; // відпрацьовано днів у місяці
    private int daysInMonth; // робочих днів у місяці
    private double accrued; // нарахована сума
    private double withheld; // утримана сума

    /* статичний ініціалізатор */
    static {
        count = 0;
    }

    /* нестатичний ініціалізатор (виконується перед кожним конструктором) */
    {
        lastName = "Невідомий";
        firstName = "Н.";
        salary = 8000.0; // мінімальна зарплата
        hireYear = Year.now().getValue();
        bonusPercent = 0.0;
        taxPercent = defaultTaxPercent;
        workedDays = 22;
        daysInMonth = 22;
        count++;
    }

    /* конструктори */

    /** Конструктор без параметрів: усі поля зі значеннями ініціалізатора. */
    public Payment() {
        calculate();
    }

    /** Конструктор з прізвищем, ім'ям та окладом (повний місяць, без надбавки). */
    public Payment(String lastName, String firstName, double salary) {
        setName(lastName, firstName);
        setSalary(salary);
        calculate();
    }

    /** Повний конструктор. */
    public Payment(String lastName, String firstName, double salary, int hireYear,
            double bonusPercent, double taxPercent, int workedDays, int daysInMonth) {
        setName(lastName, firstName);
        setSalary(salary);
        setHireYear(hireYear);
        setBonusPercent(bonusPercent);
        setTaxPercent(taxPercent);
        setDays(workedDays, daysInMonth);
        calculate();
    }

    /** Конструктор копіювання. */
    public Payment(Payment other) {
        this(other.lastName, other.firstName, other.salary, other.hireYear,
                other.bonusPercent, other.taxPercent, other.workedDays, other.daysInMonth);
    }

    /* основні методи */

    /**
     * Обчислення нарахованої суми: оклад пропорційно відпрацьованим дням +
     * надбавка.
     */
    public double calculateAccrued() {
        double base = salary * workedDays / daysInMonth;
        accrued = round2(base * (1 + bonusPercent / 100.0));
        return accrued;
    }

    /** Обчислення утриманої суми (податок від нарахованої). */
    public double calculateWithheld() {
        withheld = round2(calculateAccrued() * taxPercent / 100.0);
        return withheld;
    }

    /** Стаж роботи: кількість років від початку роботи до поточного року. */
    public int calculateExperience() {
        return experience(hireYear, Year.now().getValue());
    }

    /** Сума «на руки» = нараховано − утримано. */
    public double getNetAmount() {
        calculate();
        return round2(accrued - withheld);
    }

    /** Перерахунок нарахованої та утриманої сум. */
    private void calculate() {
        calculateWithheld();
    }

    /* статичні методи */

    public static int getCount() {
        return count;
    }

    public static double getDefaultTaxPercent() {
        return defaultTaxPercent;
    }

    public static void setDefaultTaxPercent(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Податок має бути в межах 0..100%");
        }
        defaultTaxPercent = percent;
    }

    /** Стаж між роком поступлення та заданим роком. */
    public static int experience(int hireYear, int currentYear) {
        return Math.max(0, currentYear - hireYear);
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /* гетери та сетери */

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getSalary() {
        return salary;
    }

    public int getHireYear() {
        return hireYear;
    }

    public double getBonusPercent() {
        return bonusPercent;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public int getWorkedDays() {
        return workedDays;
    }

    public int getDaysInMonth() {
        return daysInMonth;
    }

    public double getAccrued() {
        return accrued;
    }

    public double getWithheld() {
        return withheld;
    }

    public void setName(String lastName, String firstName) {
        if (lastName == null || lastName.isBlank() || firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Прізвище та ім'я не можуть бути порожніми");
        }
        this.lastName = lastName.trim();
        this.firstName = firstName.trim();
    }

    public void setSalary(double salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Оклад не може бути від'ємним");
        this.salary = salary;
        calculate();
    }

    public void setHireYear(int hireYear) {
        int now = Year.now().getValue();
        if (hireYear < 1950 || hireYear > now) {
            throw new IllegalArgumentException("Рік поступлення має бути в межах 1950.." + now);
        }
        this.hireYear = hireYear;
    }

    public void setBonusPercent(double bonusPercent) {
        if (bonusPercent < 0)
            throw new IllegalArgumentException("Надбавка не може бути від'ємною");
        this.bonusPercent = bonusPercent;
        calculate();
    }

    public void setTaxPercent(double taxPercent) {
        if (taxPercent < 0 || taxPercent > 100) {
            throw new IllegalArgumentException("Податок має бути в межах 0..100%");
        }
        this.taxPercent = taxPercent;
        calculate();
    }

    public void setDays(int workedDays, int daysInMonth) {
        if (daysInMonth < 1 || daysInMonth > 31) {
            throw new IllegalArgumentException("Кількість днів у місяці має бути 1..31");
        }
        if (workedDays < 0 || workedDays > daysInMonth) {
            throw new IllegalArgumentException("Відпрацьовані дні мають бути в межах 0.." + daysInMonth);
        }
        this.workedDays = workedDays;
        this.daysInMonth = daysInMonth;
        calculate();
    }

    /* перевизначені методи Object */

    @Override
    public String toString() {
        return String.format(
                "Payment{%s %s, оклад=%.2f, рік поступлення=%d, стаж=%d р., надбавка=%.1f%%, податок=%.1f%%, "
                        + "дні=%d/%d, нараховано=%.2f, утримано=%.2f, на руки=%.2f}",
                lastName, firstName, salary, hireYear, calculateExperience(), bonusPercent, taxPercent,
                workedDays, daysInMonth, accrued, withheld, round2(accrued - withheld));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Payment other))
            return false;
        return lastName.equals(other.lastName)
                && firstName.equals(other.firstName)
                && Double.compare(salary, other.salary) == 0
                && hireYear == other.hireYear
                && Double.compare(bonusPercent, other.bonusPercent) == 0
                && Double.compare(taxPercent, other.taxPercent) == 0
                && workedDays == other.workedDays
                && daysInMonth == other.daysInMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, salary, hireYear, bonusPercent, taxPercent,
                workedDays, daysInMonth);
    }
}
