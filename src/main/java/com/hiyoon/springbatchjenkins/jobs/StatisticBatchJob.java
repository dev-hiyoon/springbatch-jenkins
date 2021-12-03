package com.hiyoon.springbatchjenkins.jobs;

import com.hiyoon.springbatchjenkins.entity.Payment;
import com.hiyoon.springbatchjenkins.entity.Statistic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StatisticBatchJob {

    private final EntityManagerFactory entityManagerFactory;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final int pageSize = 2;
    private static final int chunkSize = 2;

    @Bean
    public Job statisticJob() {
        log.info("################## statisticJob started!");
        return jobBuilderFactory.get("statisticJob")
                .start(statisticStep())
                .build();
    }

    @Bean
    @JobScope
    public Step statisticStep() {
        log.info("################## statisticStep started!");
        return stepBuilderFactory.get("statisticStep")
                .<Payment, Statistic>chunk(chunkSize)
//                .reader(peopleReader())
                .reader(paymentReader(null))
                .processor(paymentStatisticProcessor())
                .writer(writer())
                .build();
    }

//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Long> peopleReader() {
//        log.info("################## peopleReader started!");
//        return new JpaPagingItemReaderBuilder<Long>()
//                .name("statisticItemReader")
//                .queryString("select p.personId from People p group by p.personId")
//                .pageSize(pageSize)
//                .entityManagerFactory(entityManagerFactory)
//                .build();
//    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Payment> paymentReader(@Value("#{jobParameters['date']}") String date) {
        log.info("################## reader started! date: {}", date);
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        return new JpaPagingItemReaderBuilder<Payment>()
                .name("statisticItemReader")
                .queryString("SELECT p.date, p.personId, sum(p.paymentAmount) " +
                        "FROM Payment p " +
                        "WHERE date = :date " +
                        "GROUP BY p.date, p.personId")
                .parameterValues(params)
                .pageSize(pageSize)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Payment, Statistic> paymentStatisticProcessor() {
        return payment -> new Statistic(payment);
    }

    @Bean
    @StepScope
    public JpaItemWriter<Statistic> writer() {
        log.info("################## writer started!");
        JpaItemWriter<Statistic> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

//    @Bean
//    public JdbcBatchItemWriter<Statistic> writer() {
//        return new JdbcBatchItemWriterBuilder<Statistic>()
//                .dataSource(dataSource)
//                .sql("insert into pay2(amount, tx_name, tx_date_time) values (:amount, :txName, :txDateTime)")
//                .beanMapped()
//                .build();
//    }
}
