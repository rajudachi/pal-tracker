package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication {


        @Bean
        public TimeEntryRepository getTimeEntryRepository() {
            MysqlDataSource dataSource = new MysqlDataSource();
            String strConnection = System.getenv("SPRING_DATASOURCE_URL");
            System.out.println("=============SQL Connection======= "+ strConnection);
            dataSource.setUrl(strConnection);
            return new JdbcTimeEntryRepository(dataSource);
        }

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }
}
