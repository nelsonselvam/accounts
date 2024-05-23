package com.throttle.accounts.configuration;

public import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.item.ItemReader; // Import the necessary class

@Configuration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    public AccountRepository accountRepository;
    
    @Bean
    public Job readAccountsJob() {
        return jobBuilderFactory.get("readAccountsJob")
                .start(readAccountsStep())
                .build();
    }

    @Bean
    public Step readAccountsStep() {
        return new StepBuilder("readAccountsStep")
                .<Account, Account>chunk(10)
                // .reader(reader())
                .writer(list -> list.forEach(System.out::println)) // simple writer that prints the accounts
                .build();
    }
 
    
}
