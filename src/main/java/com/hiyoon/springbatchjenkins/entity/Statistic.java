package com.hiyoon.springbatchjenkins.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(StatisticPK.class)
public class Statistic {
    private String date;
    private long personId;
    private Long totalPaymentAmount;
    private Long totalPaymentCount;

    public Statistic(Payment payment) {
        this.date = payment.getDate();
        this.personId = payment.getPersonId();
        this.totalPaymentAmount = payment.getPaymentAmount();
    }

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id
    @Column(name = "person_id")
    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "total_payment_amount")
    public Long getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(Long totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    @Basic
    @Column(name = "total_payment_count")
    public Long getTotalPaymentCount() {
        return totalPaymentCount;
    }

    public void setTotalPaymentCount(Long totalPaymentCount) {
        this.totalPaymentCount = totalPaymentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return personId == statistic.personId && Objects.equals(date, statistic.date) && Objects.equals(totalPaymentAmount, statistic.totalPaymentAmount) && Objects.equals(totalPaymentCount, statistic.totalPaymentCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, personId, totalPaymentAmount, totalPaymentCount);
    }
}
