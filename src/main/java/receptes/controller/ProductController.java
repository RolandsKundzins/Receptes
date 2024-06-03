package receptes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import receptes.model.ProductModel;
import receptes.type.ProductType;


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
        return "product/list";
    }
	
	@GetMapping("/object")
	public String showProductSingle(@RequestParam("produktsID") int produktsID, Model model) {
		System.out.println(String.format("showProductSingle(produktsID: %s))", produktsID));
        model.addAttribute("product", productModel.getProductById(produktsID));
        return "product/single"; 
    }
	
	@GetMapping("/create")
	public String ShowCreatePage(Model model) {
		System.out.println("ShowCreatePage");
		ProductType product = new ProductType();
		model.addAttribute("product", product);
        return "product/create"; 
	}
	
	@PostMapping("/create")
	public String addProduct(@ModelAttribute("product") ProductType product) {
		System.out.println("addProduct");
	    productModel.addProduct(product);
	    return "redirect:/product/list";
	}

	
	@GetMapping("/edit")
	public String ShowEditPage(@RequestParam("produktsID") int produktsID, Model model) {
		System.out.println(String.format("ShowEditPage(produktsID: %s))", produktsID));
        model.addAttribute("product", productModel.getProductById(produktsID));
        return "product/edit"; 
    }
	

	@PostMapping("/update")
	public String updateProduct(@ModelAttribute("product") ProductType product) {
		System.out.println("updateProduct");
	    productModel.updateProduct(product);
	    return "redirect:/product/list";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("produktsID") int produktsID) {
	    System.out.println(String.format("deleteProduct: %s", produktsID));
	    productModel.deleteProduct(produktsID);
	    return "redirect:/product/list"; 
	}
	
	//atgriež produktu saraksta skatu ar izfiltrētajiem produktiem
    /*@GetMapping("/search")
    public String searchProducts(@RequestParam("name") String name, Model model) {
        List<ProductType> products = productModel.getProductsByName(name);
        model.addAttribute("products", products);
        return "product/list"; 
    }*/
    
	//atgriež izfiltrētos produktus JSON formātā
    @GetMapping("/search")
    @ResponseBody
    public List<ProductType> searchProducts(@RequestParam("name") String name) {
        return productModel.getProductsByName(name);
    }
	
}
