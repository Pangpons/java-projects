package com.example.spring_batch.config;

import java.util.Arrays;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Bean
    public Job simpleJob(JobRepository jobRepository, Step simpleStep) {
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStep)
                .build();
    }
    
    @Bean
    public Step simpleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("simpleStep", jobRepository)
                .<String, String> chunk(2, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(items -> items.forEach(System.out::println))
                .build();
    }

    @Bean
    public ListItemReader<String> reader() {
        return new ListItemReader<>(Arrays.asList("apple", "banana", "cherry"));
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return String::toUpperCase;
    }
}
