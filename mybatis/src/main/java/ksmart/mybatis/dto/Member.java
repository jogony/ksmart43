package ksmart.mybatis.dto;

import java.util.List;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberLevel;
	private String memberName;
	private String memberEmail;
	private String memberAddr;
	private String memberRegDate;
	
	//DTO 객체 참조
	private MemberLevel levelInfo;
	
	private List<Goods> goodsList;
	
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	public MemberLevel getLevelInfo() {
		return levelInfo;
	}
	public void setLevelInfo(MemberLevel levelInfo) {
		this.levelInfo = levelInfo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberLevel=" + memberLevel
				+ ", memberName=" + memberName + ", memberEmail=" + memberEmail + ", memberAddr=" + memberAddr
				+ ", memberRegDate=" + memberRegDate + ", levelInfo=" + levelInfo + ", goodsList=" + goodsList + "]";
	}
	
}
