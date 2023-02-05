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

import java.util.Optional;

@Controller
@SessionAttributes("member")
@Log4j2
public class LoginController {

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
        model.addAttribute("member", memberService.getMember(member));

        return "sign/getMember";
    }

    @PostMapping("/member")
    public String updateMember(Member member, Model model) {
        model.addAttribute("member", memberRepository.save(member));

        return "redirect:/member";
    }
}
