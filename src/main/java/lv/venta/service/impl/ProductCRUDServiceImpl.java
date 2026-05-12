package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDService;

@Service
public class ProductCRUDServiceImpl implements IProductCRUDService{
	
	@Autowired
	private IProductRepo prodRepo;
	
	
	@Override
	public void create(String title, float price, int quantity, String description, ProductType type) throws Exception {
		if(title == null || !title.matches("[A-Z]{1}[a-z ]{2,30}")
				|| price < 0 || price > 1000
				|| quantity < 0 || quantity > 100
				|| description == null || !description.matches("[A-Za-z 0-9]{0,400}")
				|| type == null) {
			throw new Exception("Kads no ievades artgumentiem nav atbilstoss");
		}
		
		//parbaudam, vai tads produkts jau eksiste, ja ta, tad papildinama krajumus
		if(prodRepo.existsByTitleAndPriceAndDescriptionAndProductType(title, price, description, type )) {
			Product productfromDB = prodRepo.findByTitleAndPriceAndDescriptionAndProductType
					(title, price, description, type);
			
			int newQuantity = productfromDB.getQuantity() + quantity;
			productfromDB.setQuantity(newQuantity);
			prodRepo.save(productfromDB);//izpildas UPDATE vaicajums
			
		}
		else
		{
			Product newProduct = new Product(title, price, quantity, description, type);
			prodRepo.save(newProduct);//izpildas INSERT INTO vaicajums
		}
		
		
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(prodRepo.count()==0) {
			throw new Exception("produktu tabula DB ir tuksa");
		}
		
		ArrayList<Product> allProductsFromDB = (ArrayList<Product>) prodRepo.findAll();
		
		return allProductsFromDB;
	}

	@Override
	public Product retrieveById(long id) throws Exception {
		if(id <= 0) {
			throw new Exception("Id nevar but negatvis vai 0");
		}
		if(!prodRepo.existsById(id))
		{
			throw new Exception("Produkts ar id " + id + " neeksiste");
		}
		
		Product productFromDB = prodRepo.findById(id).get();
		
				
		return productFromDB;
	}

	@Override
	public void updateById(long id, String title, float price, int quantity, String description, ProductType type)
			throws Exception {
		Product productFromDB = retrieveById(id);
		
		if(title == null || !title.matches("[A-Z]{1}[a-z ]{2,30}")
				|| price < 0 || price > 1000
				|| quantity < 0 || quantity > 100
				|| description == null || !description.matches("[A-Za-z 0-9]{0,400}")
				|| type == null) {
			throw new Exception("Kads no ievades artgumentiem nav atbilstoss");
		}
		
		if(!productFromDB.getTitle().equals(title))
		{
			productFromDB.setTitle(title);
		}
		
		if(productFromDB.getPrice() != price)
		{
			productFromDB.setPrice(price);
		}
		
		
		if(productFromDB.getQuantity() != quantity)
		{
			productFromDB.setQuantity(quantity);
		}
		
		
		if(!productFromDB.getDescription().equals(description))
		{
			productFromDB.setDescription(description);
		}
		
		
		if(!productFromDB.getProductType().equals(type))
		{
			productFromDB.setProductType(type);
		}
		
		prodRepo.save(productFromDB);
		
	}

	@Override
	public void deleteById(long id) throws Exception {
		Product productFromDb = retrieveById(id);
		prodRepo.delete(productFromDb);
		
	}

}
