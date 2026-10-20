package ru.edu.pr03;
public class ApprovalRouter {
    public static final long FINANCE_LIMIT = 500_000L;
    public ApprovalRoute route(long amount, boolean urgent, RiskLevel risk, boolean containsPersonalData) {
        // TODO 1  Реализовать правило с правильным приоритетом условий
        return ApprovalRoute.STANDARD;
    }
}
