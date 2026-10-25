package ru.edu.pr03;
public class ApprovalRouter {
    public static final long FINANCE_LIMIT = 550_000L; //добавил модификатор 10% к лимиту
    public ApprovalRoute route(long amount, boolean urgent, RiskLevel risk, boolean containsPersonalData) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        if (containsPersonalData) {
            return ApprovalRoute.SECURITY_REVIEW;
        }
        else if (risk == RiskLevel.HIGH) {
            return ApprovalRoute.DIRECTOR_REVIEW;
        }
        else if (amount >= FINANCE_LIMIT) {
            return ApprovalRoute.FINANCE_REVIEW;
        }
        else if (urgent) {
            return ApprovalRoute.FAST_TRACK;
        }
        return ApprovalRoute.STANDARD;
    }
}
