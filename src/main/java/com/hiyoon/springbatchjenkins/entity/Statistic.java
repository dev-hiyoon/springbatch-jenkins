package com.hiyoon.springbatchjenkins.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Statistic {
    private String date;
    private long personId;
    private long totalPaymentAmount;
    private long totalPaymentCount;

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
    @Column(name = "total_payment_amount")
    public long getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(long totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    @Basic
    @Column(name = "total_payment_count")
    public long getTotalPaymentCount() {
        return totalPaymentCount;
    }

    public void setTotalPaymentCount(long totalPaymentCount) {
        this.totalPaymentCount = totalPaymentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return personId == statistic.personId && totalPaymentAmount == statistic.totalPaymentAmount && totalPaymentCount == statistic.totalPaymentCount && Objects.equals(date, statistic.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, personId, totalPaymentAmount, totalPaymentCount);
    }
}
