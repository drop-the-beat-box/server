package store.dropthebeatbox.app.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.repository.FileRepository;

import javax.persistence.EntityManagerFactory;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final FileRepository fileRepository;

    @Bean
    public Job myJob(Step firstStep){
        return jobBuilderFactory.get("FileDelete")
                .incrementer(new RunIdIncrementer())
                .start(firstStep)
                .build();
    }

    @Bean
    public Step firstStep(ItemReader<File> fileItemReader, ItemProcessor<File, File> fileItemProcessor, ItemWriter<File> fileItemWriter){
        return stepBuilderFactory.get("firstStep")
                .<File, File>chunk(10)
                .reader(fileItemReader)
                .processor(fileItemProcessor)
                .writer(fileItemWriter)
                .build();
    }

    @Bean
    public ItemWriter<File> fileItemWriter(){
        return items -> {};
    }

    @Bean
    public ItemReader<File> fileItemReader(){
        log.info("Batch Reader ===>");
        List<File> allTrashFiles = fileRepository.findAllTrashFiles();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allTrashFiles.size(); i++)
            sb.append(" ").append(allTrashFiles.get(i).getId());

        log.info("휴지통 속 파일들 ===> {}", sb);
        return new ListItemReader<>(allTrashFiles);
    }

    @Bean
    public ItemProcessor<File, File> fileItemProcessor(){
        return file ->{
            Duration duration = Duration.between(file.getDeletedAt(), LocalDateTime.now());

            log.info("휴지통 속 파일의 아이디 => {}, 그녀석의 삭제 날 => {}", file.getId(), duration);

            if (duration.toDays() >= 30L){
                log.info("File deleted : {}", file.getName());
                fileRepository.delete(file);
            }
            return file;
        };
    }

}
