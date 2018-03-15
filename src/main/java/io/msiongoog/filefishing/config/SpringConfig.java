package io.msiongoog.filefishing.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("io.msiongoog.filefishing")
public class SpringConfig {
	
	@Autowired
	DataSource dataSource;

}
