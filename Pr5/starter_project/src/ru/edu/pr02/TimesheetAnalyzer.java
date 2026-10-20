package ru.edu.pr02;

public class TimesheetAnalyzer {

    public static int[] parseHours(String line) {
        if (line == null || line.isEmpty()) {
            System.out.println("Ошибка: cтрока пустая или отсутствует");
            return new int[0];
        }
        String[] parts = line.trim().split("[,;\\s]+");
        // \\s+ - один или несколько пробельных символов
        if (parts.length != 5) {
            System.out.println("Ошибка: должно быть ровно 5 значений, а получено: " + parts.length);
            return new int[0];
        }
        int[] hours = new int[5];
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].trim();
            if (!part.matches("\\d+")) {
                System.out.println("Ошибка в элементе на позиции " + (i + 1) + ": '" + part + "' не является числом");
                return new int[0];
            }
            int val = Integer.parseInt(part);
            if (val < 0 || val > 15) {
                System.out.println("Ошибка в элементе на позиции " + (i + 1) + ": значение " + val + " должно быть от 0 до 15");
                return new int[0];
            }
            hours[i] = val;
        }
        return hours;
    }

    public static int total(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i];
        }
        return sum;
    }

    public static double average(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0.0;
        }
        int sum = total(hours);
        return (double) sum / hours.length;
    }

    public static int overtimeDays(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                count++;
            }
        }
        return count;
    }

    public static int weeklyOvertime(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }

        int sum = total(hours);
        if (sum > 40) {
            return sum - 40;
        } else {
            return 0;
        }
    }
}