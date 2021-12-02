package com.hiyoon.springbatchjenkins.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Payment {
    private String date;
    private long personId;
    private long paymentAmount;
    private LocalDateTime regDate;

    @Id
    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "person_id")
    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "payment_amount")
    public long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Basic
    @Column(name = "reg_date")
    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return personId == payment.personId && paymentAmount == payment.paymentAmount && Objects.equals(date, payment.date) && Objects.equals(regDate, payment.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, personId, paymentAmount, regDate);
    }
}
