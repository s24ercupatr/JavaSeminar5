package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class JavaSeminar52026Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaSeminar52026Application.class, args);
	}
	
	@Bean
	public CommandLineRunner testRepo(IProductRepo prodRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				Product prod1 = new Product("Abols", 0.99f, 5, "Garsigs", ProductType.fruit);
				Product prod2 = new Product("Burkans", 0.49f, 2, "Oranzs", ProductType.vegetable);
				Product prod3 = new Product("Apelsins", 1.99f, 3, "Suligs", ProductType.fruit);
				
				prodRepo.save(prod1);
				prodRepo.save(prod2);
				prodRepo.save(prod3);
				
				
				
				//TODO
				
				//iegut, cik daudz rpodukti ir tabula
				// iegut visus produktus no tabulas
				// veikt update
				//veikt dzesanu
				
				//pameginat no moodles aprakstu par MySQL
				
			}
		};
		
	}
	
	

}
