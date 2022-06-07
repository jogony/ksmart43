package ksmart.mybatis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.mybatis.dto.Goods;
import ksmart.mybatis.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/addProduct")
	public String addProduct(Goods goods) {
		log.info("goods: {}", goods);
		productService.addProduct(goods);
		return "redirect:/product/productInfoList";
	}
	@GetMapping("/addProduct")
	public String addProduct() {
		log.info("memberId");
		return "/product/addProduct";
	}
	
	@GetMapping("/productInfoList")
	public String productInfoList(Model model) {
		List<Goods> goodsList = productService.getProductList();
		log.info("goods : {}", goodsList);
		model.addAttribute("goodsList", goodsList);
		return "/product/productInfoList";
	}
}
