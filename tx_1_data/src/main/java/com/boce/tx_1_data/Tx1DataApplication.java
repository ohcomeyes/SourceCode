package com.boce.tx_1_data;

import com.boce.tx_1_data.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class)
public class Tx1DataApplication {
	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tx1DataApplication.class, args);
	}
}
