package task3;

/**
 * Завдання 3, варіант 3.
 * Клас «календар»: за порядковим номером дня в році та роком визначає
 * день місяця, місяць і день тижня.
 * Приклад: (32, 2002) -> 1 лютого 2002, п'ятниця.
 */
public class Calendar {

    /* ---------- статичні поля ---------- */
    private static final String[] MONTHS = {
            "січня", "лютого", "березня", "квітня", "травня", "червня",
            "липня", "серпня", "вересня", "жовтня", "листопада", "грудня"
    };
    private static final String[] WEEK_DAYS = {
            "понеділок", "вівторок", "середа", "четвер", "п'ятниця", "субота", "неділя"
    };
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /** Кількість створених об'єктів. */
    private static int count;

    /* ---------- поля об'єкта ---------- */
    private int dayOfYear;     // порядковий номер дня в році (1..365/366)
    private int year;          // рік
    private int month;         // місяць 1..12 (обчислюється)
    private int dayOfMonth;    // день місяця 1..31 (обчислюється)
    private int dayOfWeek;     // день тижня 1 = понеділок .. 7 = неділя (обчислюється)

    static {
        count = 0;
    }

    /* нестатичний ініціалізатор: 1 січня 2000 року */
    {
        dayOfYear = 1;
        year = 2000;
        count++;
    }

    /* ---------- конструктори ---------- */

    /** За замовчуванням — 1-й день 2000 року. */
    public Calendar() {
        compute();
    }

    /** За номером дня в році та роком. */
    public Calendar(int dayOfYear, int year) {
        set(dayOfYear, year);
    }

    /** З рядка у форматі "32 2002" або "32, 2002". */
    public Calendar(String input) {
        String[] parts = input.trim().split("[\\s,;.]+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Очікується рядок вигляду \"32, 2002\"");
        }
        try {
            set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некоректні числа у рядку: \"" + input + "\"");
        }
    }

    /** Конструктор копіювання. */
    public Calendar(Calendar other) {
        this(other.dayOfYear, other.year);
    }

    /* ---------- основні методи ---------- */

    /** Встановлює номер дня та рік і перераховує дату. */
    public final void set(int dayOfYear, int year) {
        if (year < 1) {
            throw new IllegalArgumentException("Рік має бути додатним");
        }
        if (dayOfYear < 1 || dayOfYear > daysInYear(year)) {
            throw new IllegalArgumentException(
                    "Номер дня для " + year + " року має бути в межах 1.." + daysInYear(year));
        }
        this.dayOfYear = dayOfYear;
        this.year = year;
        compute();
    }

    /** Обчислення місяця, дня місяця та дня тижня. */
    private void compute() {
        int rest = dayOfYear;
        int m = 1;
        while (rest > daysInMonth(m, year)) {
            rest -= daysInMonth(m, year);
            m++;
        }
        month = m;
        dayOfMonth = rest;
        dayOfWeek = dayOfWeek(dayOfMonth, month, year);
    }

    /** Наступний день (з переходом на новий рік). */
    public Calendar next() {
        return dayOfYear < daysInYear(year) ? new Calendar(dayOfYear + 1, year) : new Calendar(1, year + 1);
    }

    /** Попередній день (з переходом на попередній рік). */
    public Calendar previous() {
        return dayOfYear > 1 ? new Calendar(dayOfYear - 1, year) : new Calendar(daysInYear(year - 1), year - 1);
    }

    public boolean isWeekend() {
        return dayOfWeek >= 6;
    }

    public String getMonthName() {
        return MONTHS[month - 1];
    }

    public String getDayOfWeekName() {
        return WEEK_DAYS[dayOfWeek - 1];
    }

    /* ---------- статичні методи ---------- */

    public static int getCount() {
        return count;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static int daysInYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    public static int daysInMonth(int month, int year) {
        if (month < 1 || month > 12) throw new IllegalArgumentException("Місяць має бути 1..12");
        return (month == 2 && isLeapYear(year)) ? 29 : DAYS_IN_MONTH[month - 1];
    }

    /**
     * День тижня за григоріанським календарем (алгоритм Сакамото).
     * @return 1 = понеділок ... 7 = неділя
     */
    public static int dayOfWeek(int day, int month, int year) {
        int[] t = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        int y = month < 3 ? year - 1 : year;
        int w = (y + y / 4 - y / 100 + y / 400 + t[month - 1] + day) % 7; // 0 = неділя
        return w == 0 ? 7 : w;
    }

    /** Обернена операція: номер дня в році за датою. */
    public static int toDayOfYear(int day, int month, int year) {
        int n = day;
        for (int m = 1; m < month; m++) n += daysInMonth(m, year);
        return n;
    }

    /* ---------- гетери ---------- */

    public int getDayOfYear() { return dayOfYear; }
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDayOfMonth() { return dayOfMonth; }
    public int getDayOfWeek() { return dayOfWeek; }

    /* ---------- перевизначені методи Object ---------- */

    @Override
    public String toString() {
        return dayOfMonth + " " + getMonthName() + " " + year + ", " + getDayOfWeekName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Calendar other)) return false;
        return dayOfYear == other.dayOfYear && year == other.year;
    }

    @Override
    public int hashCode() {
        return 31 * year + dayOfYear;
    }
}
