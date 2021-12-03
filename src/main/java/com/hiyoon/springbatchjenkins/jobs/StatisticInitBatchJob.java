package com.hiyoon.springbatchjenkins.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StatisticInitBatchJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job initStatisticBatchJob() {
        log.info("################## statisticInitBatchJob started!");
        return jobBuilderFactory.get("statisticInitBatchJob")
                .start(initStatisticBatchStep())
                .build();
    }

    @Bean
    @JobScope
    public Step initStatisticBatchStep() {
        log.info("################## statisticInitBatchStep started!");
        return stepBuilderFactory.get("statisticInitBatchStep")
                .tasklet(initStatisticByDate(null))
                .build();
    }

    @Bean
    @StepScope
    public Tasklet initStatisticByDate(@Value("#{jobParameters['date']}") String date) {
        log.info("################## initStatisticByDate started!. date: " + date);
        return (contribution, chunkContext) -> {
            new JdbcTemplate(this.dataSource)
                    .execute("DELETE FROM batch.statistic WHERE date=" + date);
            return RepeatStatus.FINISHED;
        };
    }
}
