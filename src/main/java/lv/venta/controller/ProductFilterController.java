package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.service.IProductFilterService;

@Controller
@RequestMapping("/product/filter")
public class ProductFilterController {

	@Autowired
	private IProductFilterService prodFilterService;

	@GetMapping("/price/{threshold}")
	public String getFilterProductByPrice(@PathVariable(name = "threshold") float threshold, Model model) {
		try {
			ArrayList<Product> productsFromDB = prodFilterService.filterByPriceLessThan(threshold);
			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti, kuru cena ir zem " + threshold + " eur");
			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/type/{type}")
	public String getFilterProductByType(@PathVariable(name = "type") ProductType type, Model model) {
		try {
			ArrayList<Product> productsFromDB = prodFilterService.filterByType(type);
			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti ar tipu: " + type);
			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/keyword")
	public String getFilterProductByKeyword(@RequestParam(name = "keyword") String keyword, Model model) {
		try {
			ArrayList<Product> productsFromDB = prodFilterService.filterByKeyword(keyword);
			model.addAttribute("package", productsFromDB);
			model.addAttribute("myHeader", "Produkti, kuru nosaukums vai apraksts satur: " + keyword);
			return "show-all-products-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/avg")
	public String getAveragePrice(Model model) {
		try {
			float avgPrice = prodFilterService.calculateAvgPrice();
			model.addAttribute("package", "Vidējā produktu cena: " + avgPrice + " eur");
			return "avg-price-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}