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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmart.mybatis.dto.Member;
import ksmart.mybatis.dto.MemberLevel;
import ksmart.mybatis.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@GetMapping("/sellerInfoList")
	public String getSellerInfoList(Model model) {
		List<Member> sellerInfoList = memberService.getSellerInfoList();
		model.addAttribute("sellerInfoList", sellerInfoList);
		log.info("sellerInfoList : {}", sellerInfoList);
		return "member/memberSellerList";
	}
	@GetMapping("/memberInfoList")
	public String getMemberInfoList(Model model) {
		List<Member> memberInfoList = memberService.getMemberInfoList();
		model.addAttribute("memberInfoList", memberInfoList);
		return "member/memberInfoList";
	}
	@PostMapping("/memberList")
	public String getSearchMemberList(@RequestParam(name="searchKey") String searchKey
									 ,@RequestParam(name="searchValue", required = false) String searchValue
									 ,Model model) {
		log.info("searchKey   : {}", searchKey);
		log.info("searchValue : {}", searchValue);
		if("memberId".equals(searchKey)) {
			searchKey="m.m_id";
		} else if("memberLevel".equals(searchKey)) {
			searchKey="m.m_level";
		} else if("memberName".equals(searchKey)) {
			searchKey="m.m_name";
		} else {
			searchKey="m.m_email";
		}
		
		List<Member> searchMemberList = memberService.getSearchMemberList(searchKey, searchValue);
		
		if(searchMemberList != null) {
			model.addAttribute("memberList", searchMemberList);
		}
		return "member/memberList";
	}
	@PostMapping("/removeMember")
	public String removeMember(@RequestParam(name="memberId") String memberId
							  ,@RequestParam(name="memberPw") String memberPw
							  ,RedirectAttributes reAttr) {

		
		boolean isRemove = memberService.removeMember(memberId, memberPw);;
		
		// false: ???????????? ????????????
		if(isRemove) {
			return "redirect:/member/memberList";
		} else {
			reAttr.addAttribute("result", "???????????? ????????? ????????? ???????????? ????????????.");
		}
		
		reAttr.addAttribute("memberId",memberId);
		
		return "redirect:/member/removeMember";
	}
	@GetMapping("/removeMember")
	public String remove(Model model
						,@RequestParam(name="memberId", required = false) String memberId
						,@RequestParam(name="result", required = false) String result) {
		log.info("memberId : {}", memberId );
		model.addAttribute("memberId", memberId);
		if(result != null) model.addAttribute("result", result);
		return "member/removeMember";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="memberId") String memberId) {
		log.info("????????? ???????????? : {}", memberId);
		
		//true : ????????? ??????x, false: ????????? ??????o
		boolean isIdCheck = false;
		Member member = memberService.getMemberInfoById(memberId);
		
		if(member != null) {
			isIdCheck = true;
		}
		
		
		
		return isIdCheck;
	}
	
	@PostMapping("/modifyMember")
	public String modifyMember(Member member) {
		log.info("?????????????????? ???????????? data:{}", member);
		memberService.modifyMember(member);
		return "redirect:/member/memberList";
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(@RequestParam(name="memberId", required = false) String memberId
											,Model model) {
		
		log.info("???????????? ???????????? data : {}", memberId);
		Member member = memberService.getMemberInfoById(memberId);
		List<MemberLevel> memberLevelList = memberService.getMemberLevelList();
		model.addAttribute("memberLevelList", memberLevelList);
		log.info("????????????????????? : {}", memberLevelList);
		model.addAttribute("member", member);
		log.info("?????? : {}", member);
		return "member/modifyMember";
		//return"redirect:/";
	}
	
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		List<Member> memberList = memberService.getMemberList();
		log.info("?????? ?????? ??????: {}", memberList);
		model.addAttribute("memberList", memberList);
		return "member/memberList";
	}
	
	@GetMapping("/addMember")
	public String addMember(Model model) {
		List<MemberLevel> memberLevelList = memberService.getMemberLevelList();
		model.addAttribute("memberLevelList", memberLevelList);
		return "member/addMember";
	}
	/*
	 * ??????????????? : http?????? ?????? data(key, value) => DTO(??????????????? ?????????) ???????????? ??????????????? ??????
	 * String memberid = request.getParameter("memberId")
	 * Member member = new Member();
	 * member.setMemberId(memberID);
	 * @Param Member member(???????????????)
	 * @return Controller (String) "redirect:/" == response.sendRedirect("/")
	 * */
	@PostMapping("/addMember")
	public String addMember(Member member
							,@RequestParam(name="memberId", required = false, defaultValue = "id001") String memberId) {
		log.info("???????????????????????? ????????? data : {}", member);
		log.info("???????????? ????????? data : {}", member);
		
		memberService.addMember(member);
		
		return "redirect:/member/memberList";
	}
	
}
