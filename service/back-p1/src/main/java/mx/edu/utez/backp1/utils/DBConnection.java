package mx.edu.utez.backp1.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConnection {

    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USER;
    @Value("${spring.datasource.password}")
    private String DB_PASS;

    @Bean
    public DataSource getConnection(){
        DriverManagerDataSource src = new DriverManagerDataSource();
        src.setUrl(String.format(DB_URL));
        src.setDriverClassName("com.mysql.cj.jdbc.Driver");
        src.setUsername(DB_USER);
        src.setPassword(DB_PASS);
        return src;
    }
}
