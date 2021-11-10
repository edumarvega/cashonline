package com.broker.cashonline;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.broker.cashonline.entity.Loan;
import com.broker.cashonline.entity.User;
import com.broker.cashonline.repository.UserRepository;

@SpringBootApplication
public class CashonlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashonlineApplication.class, args);
	}
	
	
	@Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
        	
        	User user = new User();
        	user.setEmail("test@app.com.ar");
        	user.setFirstName("Pepe");
        	user.setLastName("Argento");
        	
        	Random random = new Random();   
        	  
        	for (int i = 0; i < 1500; i++) {
        		int numberRandom = random.nextInt(100000); 
        		Loan loan = new Loan();
            	loan.setTotal(new BigDecimal(Integer.toString(numberRandom)));
            	loan.setUser(user);
            	user.setLoans(new ArrayList());
            	user.getLoans().add(loan);
            	
            	repository.save(user);
			}
        	
            
        	
            
        };
    }

}
