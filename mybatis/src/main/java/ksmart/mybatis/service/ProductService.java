package ksmart.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.mybatis.dto.Goods;
import ksmart.mybatis.mapper.ProductMapper;

@Service
@Transactional
public class ProductService {

	private final ProductMapper productMapper;
	
	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	public List<Goods> getProductList() {
		List<Goods> goodsList = productMapper.getProductList();
		System.out.println(goodsList);
		return goodsList;
	}
	public int addProduct(Goods goods) {
		int result = productMapper.addProduct(goods);
		return result;
	}
}
