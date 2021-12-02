package com.hiyoon.springbatchjenkins.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Statistic {
    private String date;
    private long personId;
    private long totalPaymentAmount;
    private long totalPaymentCount;

    public Statistic(People people) {
        this.date = LocalDate.now().toString();
        this.personId = people.getPersonId();
        this.totalPaymentAmount = 0;
        this.totalPaymentCount = 0;
    }

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
