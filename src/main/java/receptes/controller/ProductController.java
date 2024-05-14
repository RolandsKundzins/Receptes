package receptes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import receptes.model.ProductModel;
import receptes.type.ProductType;


@SuppressWarnings("unused")
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductModel productModel;	

	@GetMapping("/list")
	public String showproductModel(Model model) {
        // Load recipe data and add it to the model
		System.out.println("showproductModel");
        model.addAttribute("products", productModel.getAllProducts());
        return "product-list"; // src/main/templates/recipe-list.html
    }
	
	@GetMapping("/object")
	public String showProductSingle(@RequestParam("produktsId") int produktsID, Model model) {
		System.out.println(String.format("showProductSingle(produktsId: %s))", produktsID));
        model.addAttribute("product", productModel.getProductById(produktsID));
        return "product-single"; 
    }
	
	@GetMapping("/create")
	public String ShowCreatePage(Model model) {
		System.out.println(String.format("product-create"));
		ProductType product = new ProductType();
		model.addAttribute("product", product);
        return "product-create"; 
	}
	
	@PostMapping("/create")
	public String addProduct(@ModelAttribute("product") ProductType product) {
	    productModel.addProduct(product);
	    return "redirect:/product/list";
	}

	
	@GetMapping("/edit")
	public String ShowEditPage(@RequestParam("produktsId") int produktsID, Model model) {
		System.out.println(String.format("showProductSingle(produktsId: %s))", produktsID));
        model.addAttribute("product", productModel.getProductById(produktsID));
        return "product-edit"; 
    }
	

	@PostMapping("/update")
	public String updateProduct(@ModelAttribute("product") ProductType product) {
	    productModel.updateProduct(product);
	    return "redirect:/product/list";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("produktsId") int produktsID) {
	    System.out.println(String.format("Deleting product with ID: %s", produktsID));
	    productModel.deleteProduct(produktsID);
	    return "redirect:/product/list"; 
	}
	
}
