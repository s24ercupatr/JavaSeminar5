package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.model.ProductType;

@Controller
@RequestMapping("/simple")
public class SimpleController {
	
	@GetMapping("/page")//localhost:8080/simple/page
	public String getShowPage() {
		System.out.println("Mans pirmais kontrolieris ir izsaukts");
		return "show-page";//tiks paradita show-page.html lapa ieks web parluka
	}
	
	@GetMapping("/data")
	public String getDataInPage(Model model) {//localhost:8080/simple/data
		System.out.println("Izpildas datu kontrolieris");
		Random rand = new Random();
		String data = "@Karina " + rand.nextInt(2010, 2026);
		model.addAttribute("package", data);
		return "show-data-page";//tiks paradita show-data-page.html lapa
	}
	@GetMapping("/product")//localhost:8080/simple/product
	public String getProductInPage(Model model) {
		Product prod = new Product("Abols", 0.99f, 5, "Garsigs", ProductType.fruit);
		model.addAttribute("package", prod);
		return "show-one-product-page";//tiks paradita show-one-product-page.html lapa
		
	}
	
	
	@GetMapping("/products")//localhost:8080/simple/products
	public String getAllProductsInPage(Model model) {
		Product prod1 = new Product("Abols", 0.99f, 5, "Garsigs", ProductType.fruit);
		Product prod2 = new Product("Burkans", 0.49f, 2, "Oranzs", ProductType.vegetable);
		Product prod3 = new Product("Apelsins", 1.99f, 3, "Suligs", ProductType.fruit);
		
		ArrayList<Product> allProducts = new ArrayList<Product>(Arrays.asList(prod1, prod2, prod3));
		
		model.addAttribute("package", allProducts);
		return "show-all-products-page";//tiks paradita show-all-products-page.html lapa
	}
	
	@GetMapping("/add")//localhost:8080/simple/add
	public String getAddNewProduct(Model model) {
		//lai iveidotu jaunu produktu, iedodam noklsueto proukdu, kuru pec tam vares aizpildit html puse
		model.addAttribute("product", new Product());
		return "add-new-product-page";//tiks paradita add-new-product-page.html lapa
	}
	
	
	@PostMapping("/add")
	public String postAddNewProduct(Product product) {
		//TODO veic datu parbaydi un saglabasanu
		System.out.println(product);
		return "redirect:/simple/page";
	}
	
	
	@GetMapping("/update")//localhost:8080/simple/update
	public String getUpdateProduct(Model model) {
		//TODO izmantot kadu filtru, lai samekletu konkreto produktu, kuru updeito
		Product prod = new Product("Abols", 0.99f, 5, "Garsigs", ProductType.fruit);
		model.addAttribute("product", prod);
		return "update-product-page";//tiks paradita update-product-page.html lapa
	}
	
	
	@PostMapping("/update")
	public String postUpdateProduct(Product product) {
		//TODO veic datu parbaydi un saglabasanu redigetajam produktam
		System.out.println(product);
		return "redirect:/simple/page";
	}
	
	
}
