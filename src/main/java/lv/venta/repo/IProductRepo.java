package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;
import lv.venta.model.ProductType;

public interface IProductRepo extends CrudRepository<Product, Long> {

	boolean existsByTitleAndPriceAndDescriptionAndProductType(String title, float price, String description,
			ProductType type);
	//TODO papildinat pec nepieciesamaibas citas funkcijas

	Product findByTitleAndPriceAndDescriptionAndProductType(String title, float price, String description,
			ProductType type);

	ArrayList<Product> findByPriceLessThan(float threshold);

	ArrayList<Product> findByProductType(ProductType type);

	ArrayList<Product> findByTitleContainingOrDescriptionContaining(String keyword, String keyword2);

	@Query(nativeQuery = true, value = "SELECT AVG(price) FROM product_table;")
	float calculateAvgPriceFromDB();

}
