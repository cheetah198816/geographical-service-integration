package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * Created by chetan on 22.12.2017.
 */
@Configuration
public class FileConfiguration {

    @Value("${file.path}")
    private String baseFilePath;

    public String getBaseFilePath() {
        return baseFilePath;
    }

    @Bean
    TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setConcurrencyLimit(100);
        return simpleAsyncTaskExecutor;
    }
}
