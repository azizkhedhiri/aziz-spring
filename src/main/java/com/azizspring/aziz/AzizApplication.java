package com.azizspring.aziz;

import com.azizspring.aziz.dao.CategorieRepository;
import com.azizspring.aziz.dao.CarRepository;
import com.azizspring.aziz.entities.Categorie;
import com.azizspring.aziz.entities.Car;
import com.azizspring.aziz.security.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootApplication
@AllArgsConstructor
public class AzizApplication implements CommandLineRunner {
	 //@Autowired
	//private CarRepository carRepository;
	// @Autowired
	private CategorieRepository categorieRepository;

	public static void main(String[] args) {

		SpringApplication.run(AzizApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//categorieRepository.save(new Categorie(null,"audi",null));
		categorieRepository.save(Categorie.builder().nom("mercedes").build());
		  // carRepository.save(new Car(null,"audi",500,10,categorieRepository.findById(2L).get()));
		  //carRepository.save(new Car(null,"ferrari",50,100,categorieRepository.findById(1L).get()));
		  //carRepository.save(new Car(null,"bentley",50,100,categorieRepository.findById(1L).get()));
	}
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
    CommandLineRunner commandLineRunner(IAccountService accountService)
    {
        return  args -> {
                 accountService.addRole("USER");
                 accountService.addRole("ADMIN");
                 accountService.addUser("user","123","user@gmail.com");
                 accountService.addUser("admin","123","admin@gmail.com");
                 accountService.addroleToUser("user","USER");
                 accountService.addroleToUser("admin","ADMIN");
                 accountService.addroleToUser("admin","USER");



        };
    }

}
