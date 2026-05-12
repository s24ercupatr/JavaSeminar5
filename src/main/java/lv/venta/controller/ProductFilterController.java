package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.service.IProductFilterService;

@Controller
@RequestMapping("/product/filter")
public class ProductFilterController {
	
	@Autowired
	private IProductFilterService prodFilterService;
	
	@GetMapping("/price/{threshold}")//localhost:8080/product/filter/price/1
	public String getFilterProductByPrice(@PathVariable(name = "threshold") float threshold, Model model) {
		try
		{
			ArrayList<Product> productsFromDB =  prodFilterService.filterByPriceLessThan(threshold);
			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti, kuru cena ir zem " + threshold + " eur");
			return "show-all-products-page";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

}
