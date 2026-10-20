//Тесты сгенерированы ИИ заведомо.
package ru.edu.pr02;

public class SelfCheck {
    public static void main(String[] args) {
        boolean allPassed = true;
        System.out.println("=== Запуск граничных проверок (Вариант 2: верхняя граница 15) ===");

        // 1. Корректное типовое значение
        int[] r1 = TimesheetAnalyzer.parseHours("8,8,8,8,8");
        if (r1.length == 5 && TimesheetAnalyzer.total(r1) == 40) {
            System.out.println("[PASS] 1. Типовое значение (8,8,8,8,8)");
        } else {
            System.out.println("[FAIL] 1. Типовое значение");
            allPassed = false;
        }

        // 2. Нижняя граница (ноль часов)
        int[] r2 = TimesheetAnalyzer.parseHours("0,0,0,0,0");
        if (r2.length == 5 && TimesheetAnalyzer.total(r2) == 0) {
            System.out.println("[PASS] 2. Нижняя граница (0,0,0,0,0)");
        } else {
            System.out.println("[FAIL] 2. Нижняя граница");
            allPassed = false;
        }

        // 3. Новая верхняя граница для Варианта 2 (15 часов)
        int[] r3 = TimesheetAnalyzer.parseHours("15;15;15;15;15");
        if (r3.length == 5 && TimesheetAnalyzer.total(r3) == 75) {
            System.out.println("[PASS] 3. Верхняя граница (15,15,15,15,15)");
        } else {
            System.out.println("[FAIL] 3. Верхняя граница");
            allPassed = false;
        }

        // 4. Пустая строка (контролируемая ошибка)
        System.out.print("Ожидается ошибка для пустой строки -> ");
        int[] r4 = TimesheetAnalyzer.parseHours("");
        if (r4.length == 0) {
            System.out.println("[PASS] 4. Пустая строка обработана корректно");
        } else {
            allPassed = false;
        }

        // 5. Выход за новую верхнюю границу (значение 16 или 17)
        System.out.print("Ожидается ошибка для значения 16 (превышает лимит 15) -> ");
        int[] r5 = TimesheetAnalyzer.parseHours("8,8,16,8,8");
        if (r5.length == 0) {
            System.out.println("[PASS] 5. Превышение лимита (16) обработано корректно");
        } else {
            allPassed = false;
        }

        // 6. Неверный формат (текст вместо числа)
        System.out.print("Ожидается ошибка для текста -> ");
        int[] r6 = TimesheetAnalyzer.parseHours("8,abc,8,8,8");
        if (r6.length == 0) {
            System.out.println("[PASS] 6. Неверный формат (текст) обработан корректно");
        } else {
            allPassed = false;
        }

        System.out.println("=========================================================");
        if (allPassed) {
            System.out.println("ИТОГ: ВСЕ ПРОВЕРКИ УСПЕШНО ПРОШЛИ (PASS)");
        } else {
            System.out.println("ИТОГ: ЕСТЬ ОШИБКИ (FAIL)");
        }
    }
}