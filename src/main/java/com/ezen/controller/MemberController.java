package com.ezen.controller;

import com.ezen.entity.Degree;
import com.ezen.entity.Member;
import com.ezen.entity.Role;
import com.ezen.persistence.MemberRepository;
import com.ezen.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("member")
@Log4j2
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "sign/login";
    }


    @PostMapping("/login")
    public String login(HttpServletRequest request, Member loginMember, Model model) {
        Member findMember = (Member) memberService.getMember(loginMember);

        log.info("findMember: " + findMember);

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        if (findMember != null && findMember.getPassword().equals(loginMember.getPassword())) {
            model.addAttribute("loginMember", findMember);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/home";
    }

    @GetMapping("/join")
    public String joinForm(Member member, Model model) {
        model.addAttribute("member", member);
        return "sign/join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute("member") Member member,
                       BindingResult result) {
        // username 중복 확인
        String username = member.getUsername();

        Optional<Member> memberId = memberRepository.findById(member.getUsername());

        if(memberId.isPresent()) {
            FieldError fieldError = new FieldError("member", "username", "이미 존재하는 아이디입니다.");
            result.addError(fieldError);
        }
        if(!member.getPassword().equals(member.getPasswordCheck())) {
            FieldError fieldError = new FieldError("member", "passwordCheck", "비밀번호가 일치하지 않습니다.");
            result.addError(fieldError);
        }
        if(!member.getAgree().equals("y")) {
            FieldError fieldError = new FieldError("member", "agree", "약관에 동의하셔야 가입이 가능합니다.");
            result.addError(fieldError);
        }
        if(result.hasErrors()) {
            log.info("회원가입 오류: " + result);
            return "sign/join";
        } else {
            member.setRole(Role.valueOf("MEMBER"));
            member.setDegree(Degree.valueOf("BRONZE"));
            log.info("회원 가입 정보 :" + member);
            memberService.insertMember(member);
            return "redirect:login";
        }
    }

    @GetMapping("/member")
    public String getMember(Member member, Model model) {
        Map<String, String> addrMap = new HashMap<>();
        String[] addressArr = null;

        Member member1 = memberService.getMember(member);
        addressArr = member1.getAddress().split(",");

        addrMap.put("addr1", addressArr[0]);
        addrMap.put("addr2", addressArr[1]);
        addrMap.put("addr3", addressArr[2]);

        model.addAttribute("member", member1);
        model.addAttribute("address", addrMap);

        return "sign/getMember";
    }

    @PostMapping("/member")
    public String updateMember(Member member, Model model) {
        model.addAttribute("member", memberRepository.save(member));

        return "redirect:/member";
    }

    @GetMapping("/findMember")
    public String memberCheck() {
        return "sign/findMember";
    }

    @PostMapping("/findId")
    public String findId(Member member, Model model) {
        Member memberId = memberService.findMemberId(member.getName(), member.getEmail());
        log.info("아이디 찾기: " + memberId);

        if(memberId == null) {
            model.addAttribute("find", 0);
        } else {
            model.addAttribute("find", 1);
            model.addAttribute("member", memberId);
        }
        return "/sign/findId";
    }

    @PostMapping("/findPwd")
    public String findPwd(Member member, Model model) {
        Member memberPwd = memberService.findMemberPwd(member.getUsername(), member.getEmail());
        log.info("비밀번호 찾기: " + memberPwd);

        if(memberPwd == null) {
            model.addAttribute("find", 0);
        } else {
            model.addAttribute("find", 1);
            model.addAttribute("member", memberPwd);
        }
        return "sign/findPwd";
    }

}
