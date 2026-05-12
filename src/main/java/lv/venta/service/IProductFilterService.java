package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;
import lv.venta.model.ProductType;

public interface IProductFilterService {
//TODO
	//1. funkcija, kas izfiltre produktus pec cenas slieksna
	public abstract ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception;
	//2. funkcija, kas izfiltre prodfuktus pec tipa
	public abstract ArrayList<Product> filterByType(ProductType type) throws Exception;
	//3. funkcija, kas izfiltre produktus pec padoto keyword un skatas title un description
	public abstract ArrayList<Product> filterByKeyword(String keyword) throws Exception;
	//4. funkcija, kas aprekina video cenu visiem produktiem
	public abstract float calculateAvgPrice() throws Exception;
	
}
