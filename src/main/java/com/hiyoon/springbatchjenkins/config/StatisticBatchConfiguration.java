package com.hiyoon.springbatchjenkins.config;

import com.hiyoon.springbatchjenkins.entity.People;
import com.hiyoon.springbatchjenkins.entity.Statistic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StatisticBatchConfiguration {

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
                .<People, Statistic>chunk(chunkSize)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<People> reader() {
        log.info("################## reader started!");
        return new JpaPagingItemReaderBuilder<People>()
                .name("statisticItemReader")
                .queryString("SELECT p FROM People p")
                .pageSize(pageSize)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<People, Statistic> processor() {
        return item -> new Statistic(item);
    }

    @Bean
    @StepScope
    public JpaItemWriter<Statistic> writer() {
        log.info("################## writer started!");
        JpaItemWriter<Statistic> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
}
