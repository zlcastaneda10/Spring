package com.springchallenge.springbatchreports.configurations;

import com.springchallenge.springbatchreports.models.AnimeDTO;
import com.springchallenge.springbatchreports.processors.AnimeItemProcessor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;



@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public AnimeItemProcessor processor(){
        return new AnimeItemProcessor();
    }

    private Resource outputResource = new FileSystemResource("output/outputData.csv");

    @Bean
    public FlatFileItemWriter<AnimeDTO> writer()
    {
        //Create writer instance
        FlatFileItemWriter<AnimeDTO> writer = new FlatFileItemWriter<>();

        //Set output file location
        writer.setResource(outputResource);

        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);

        //Name field values sequence based on object properties
        writer.setLineAggregator(new DelimitedLineAggregator<AnimeDTO>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<AnimeDTO>() {
                    {
                        setNames(new String[] { "id", "firstName", "lastName" });
                    }
                });
            }
        });
        return writer;
    }

}
