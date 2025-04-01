import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date!");
        }
    }

    public boolean isValidDate(int day, int month, int year) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updateDate(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            System.out.println("Invalid date update attempt!");
        }
    }

    public String getDayOfWeek() {
        LocalDate date = LocalDate.of(year, month, day);
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public int calculateDifference(Date otherDate) {
        LocalDate thisDate = LocalDate.of(this.year, this.month, this.day);
        LocalDate thatDate = LocalDate.of(otherDate.year, otherDate.month, otherDate.day);
        return Math.abs((int) (thisDate.toEpochDay() - thatDate.toEpochDay()));
    }

    public void printDate() {
        LocalDate date = LocalDate.of(year, month, day);
        System.out.println(date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + day + ", " + year);
    }

    @Override
    public int compareTo(Date other) {
        return Comparator.comparingInt((Date d) -> d.year)
                .thenComparingInt(d -> d.month)
                .thenComparingInt(d -> d.day)
                .compare(this, other);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(5, 3, 2022));
        dates.add(new Date(15, 8, 2021));
        dates.add(new Date(1, 1, 2023));

        System.out.println("Before sorting:");
        for (Date date : dates) date.printDate();

        Collections.sort(dates);

        System.out.println("\nAfter sorting:");
        for (Date date : dates) date.printDate();

        Date d1 = new Date(10, 5, 2020);
        Date d2 = new Date(25, 12, 2020);

        System.out.println("\nDifference between dates: " + d1.calculateDifference(d2) + " days");
        System.out.println("Day of the week for d1: " + d1.getDayOfWeek());
    }
}
