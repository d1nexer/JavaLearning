package ru.edu.pr05;

import java.time.LocalDate;

public class BusinessTrip {
    private final String id;
    private final String employeeName; // добавил поле для проверки обязательных данных исходя из диаграммы классов
    private final LocalDate start;
    private final LocalDate end;
    private TripStatus status = TripStatus.DRAFT;
    private String rejectionReason;

    public BusinessTrip(String id, String employeeName, LocalDate start, LocalDate end) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID командировки не может быть пустым.");
        }
        if (employeeName == null || employeeName.isBlank()) {
            throw new IllegalArgumentException("Имя сотрудника не может быть пустым.");
        }
        if (start == null || end == null) {
            throw new IllegalArgumentException("Даты начала и окончания не могут быть null.");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Дата начала не может быть позже даты окончания.");
        }
        
        this.id = id;
        this.employeeName = employeeName;
        this.start = start;
        this.end = end;
    }

    public void submit() {
        if (this.status != TripStatus.DRAFT) {
            throw new IllegalStateException("Отправить заявку можно только из статуса DRAFT.");
        }
        if (this.id == null || this.employeeName == null || this.start == null || this.end == null) {
            throw new IllegalStateException("Заполнены не все обязательные данные для отправки.");
        }
        this.status = TripStatus.SUBMITTED;
    }

    public void approve() {
        if (this.status != TripStatus.SUBMITTED) {
            throw new IllegalStateException("Утвердить заявку можно только из статуса SUBMITTED.");
        }
        this.status = TripStatus.APPROVED;
    }

    public void reject(String reason) {
        if (this.status != TripStatus.SUBMITTED) {
            throw new IllegalStateException("Отклонить заявку можно только из статуса SUBMITTED.");
        }
        // модификатор на длину 10 из 1го варианта модификаторов
        if (reason == null || reason.trim().length() < 10) {
            throw new IllegalArgumentException("Причина отклонения обязательна и должна содержать минимум 10 символов.");
        }
        this.rejectionReason = reason;
        this.status = TripStatus.REJECTED;
    }

    public void cancel() {
        if (this.status == TripStatus.COMPLETED || this.status == TripStatus.CANCELLED || this.status == TripStatus.REJECTED) {
            throw new IllegalStateException("Отмена из текущего статуса (" + this.status + ") невозможна.");
        }
        this.status = TripStatus.CANCELLED;
    }

    public void complete(LocalDate today) {
        if (this.status != TripStatus.APPROVED) {
            throw new IllegalStateException("Завершить командировку можно только из статуса APPROVED.");
        }
        if (today == null) {
            throw new IllegalArgumentException("Дата проверки (today) не может быть null.");
        }
        if (!today.isAfter(end)) {
            throw new IllegalStateException("Завершение командировки возможно только после даты её окончания.");
        }
        this.status = TripStatus.COMPLETED;
    }

    public String getId() { return id; }
    public String getEmployeeName() { return employeeName; }
    public LocalDate getStart() { return start; }
    public LocalDate getEnd() { return end; }
    public TripStatus getStatus() { return status; }
    public String getRejectionReason() { return rejectionReason; }
}