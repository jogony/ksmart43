package ksmart.mybatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.mybatis.dto.Member;
import ksmart.mybatis.dto.MemberLevel;
import ksmart.mybatis.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	
	// DI (의존성 주입)
	// 1. 필드 주입 방식
	/*
		@Autowired
		private MemberMapper memberMapper;
	*/
	
	
	// 2. setter 메서드 주입 방식
	/*
		@Autowired
		private MemberMapper memberMapper;
	
		public void setMemberMapper(MemberMapper memberMapper) {
			this.memberMapper = memberMapper;
		}
	*/
	// 3. 생성자 메서드 주입방식
	private final MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	public List<Member> getSearchMemberList(String searchKey, String searchValue) {
		List<Member> searchMemberList = memberMapper.getSearchMemberList(searchKey, searchValue);
		return searchMemberList;
	}
	/*
	 * 로그인 이력 조회
	 * */
	public Map<String, Object> getLoginHistory(int currentPage) {
		//몇개 보여줄지?
		int rowPerPage = 5;
		//총 행의 개수
		double rowCount = memberMapper.getLoginHistoryCount();
		//마지막 페이지
		int lastPage = (int)Math.ceil(rowCount/rowPerPage);
		//페이징 처리
		int startPage = 1;
		int endPage = 9;
		if(currentPage > 6) {
			startPage = currentPage-4;
			endPage = currentPage+4;
		}
		if(endPage > lastPage) {
			endPage = lastPage;
			startPage = lastPage-8;
		}
	
		System.out.println(lastPage+"last!!!!!!!!page!!!!!!!!");
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("startPage", startPage);
		paramMap.put("endPage", endPage);
		
		List<Map<String, Object>> loginHistoryList = memberMapper.getLoginHistory(paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("loginHistoryList", loginHistoryList);
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		
		return resultMap;
	}
	public List<Member> getSellerInfoList() {
		List<Member> sellerInfoList = memberMapper.getSellerInfoList();
		return sellerInfoList;
	}
	/*
	 * 회원전체 정보 조회
	 * */
	public List<Member> getMemberInfoList() {
		List<Member> memberInfoList = memberMapper.getMemberInfoList();
		return memberInfoList;
	}
	/*
	 * 회원 탈퇴
	 * */
	public boolean removeMember(String memberId, String memberPw) {
		
		boolean memberCheck = false;
		
		Member member = memberMapper.getMemberInfoById(memberId);
		
		if(member != null) {
			String memberPwCheck = member.getMemberPw();
			String memberLevel	 = member.getMemberLevel();
			if(memberPw.equals(memberPwCheck)) {
				memberCheck = true;
				//삭제 로직
				//if(memberLevel != null) db에 null이 들어갈 수도 있음
				//등급별로 삭제를 달리 진행
				
				//판매자
				if("2".equals(memberLevel)) {
					//1.tb_order (상품코드에 연관된 튜플 삭제)
					memberMapper.removeOrderByGcode(memberId);
					//2.tb_goods (판매자가 등록한 상품목록 삭제)
					memberMapper.removeGoodsById(memberId);
				//구매자
				}
				if("3".equals(memberLevel)) {
					//1.tb_order (구매자 구매한 주문내역 삭제)
					memberMapper.removeOrderById(memberId);
				}
				//1.tb_login (회원이 로그인 이력 삭제)
				memberMapper.removeLoginhistoryById(memberId);
				//2.tb_member(회원 탈퇴)
				memberMapper.removeMemberById(memberId);
			}
			
		}
		return memberCheck;
	}
	/*
	 * 회원 수정
	 * */
	public int modifyMember(Member member) {
		
		int result = memberMapper.modifyMember(member);
		
		return result;
	}
	/*
	 * 회원 상세정보
	 * */
	public Member getMemberInfoById(String memberId) {
		Member member = memberMapper.getMemberInfoById(memberId);
		return member;
	}
	/*
	 * 회원 등급 목록 조회
	 * */
	public int addMember(Member member) {
		
		int result = memberMapper.addMember(member);
		
		return result;
	}
	
	public List<MemberLevel> getMemberLevelList() {
		
		List<MemberLevel> memberLevelList = memberMapper.getMemberLevelList();
		
		return memberLevelList;
	}
	
	public List<Member> getMemberList() {
		
		List<Member> memberList = memberMapper.getMemberList();
		/*
		if(memberList != null) {
			for(Member member : memberList) {
				String memberLevel = member.getMemberLevel();
				if(memberLevel != null) {
					if(memberLevel.equals("1")){
						member.setMemberLevel("관리자");
					} else if (memberLevel.equals("2")) {
						member.setMemberLevel("판매자");
					} else if (memberLevel.equals("3")) {
						member.setMemberLevel("구매자");
					} else {
						member.setMemberLevel("일반회원");
					}
				}
			}
		}
		*/
		
		return memberList;
	}
}
