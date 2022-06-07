package ksmart.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmart.mybatis.dto.Member;
import ksmart.mybatis.dto.MemberLevel;

@Mapper
public interface MemberMapper {
	//로그인 이력 조회(페이징)
	public List<Map<String, Object>> getLoginHistory(Map<String, Object> paramMap);
	//로그인 이력테이블 중 row(튜플)수
	public int getLoginHistoryCount();
	//회원목록조회 (복잡한 결과 매핑 has one 관계 : association)
	public List<Member> getMemberInfoList();
	//판매자목록조회 (복잡한 결과 매핑 has many 관계 : collection)
	public List<Member> getSellerInfoList();
	
	//회원검색
	public List<Member> getSearchMemberList(String searchKey, String searchValue);
	//1.tb_order (상품코드에 연관된 주문내역 삭제)
	public int removeOrderByGcode(String memberId);
	//2.tb_goods (판매자가 등록한 상품목록 삭제)
	public int removeGoodsById(String memberId);
	//3.tb_order (구매자 구매한 주문내역 삭제)
	public int removeOrderById(String memberId);
	//4.tb_login (회원이 로그인 이력 삭제)
	public int removeLoginhistoryById(String memberId);
	//5.tb_member(회원 탈퇴)
	public int removeMemberById(String memberId);
	//회원수정
	public int modifyMember(Member member);
	
	//회원 상세정보 조회
	public Member getMemberInfoById(String memberId);
	
	//회원등록
	public int addMember(Member member);
	
	//회원목록조회
	public List<Member> getMemberList();
	
	//회원등급조회
	public List<MemberLevel> getMemberLevelList();
}
