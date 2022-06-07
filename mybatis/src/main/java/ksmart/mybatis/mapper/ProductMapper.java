package ksmart.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.mybatis.dto.Goods;

@Mapper
public interface ProductMapper {
	//전체 상품 조회
	public List<Goods> getProductList();
	//상품등록
	public int addProduct(Goods goods);
}
