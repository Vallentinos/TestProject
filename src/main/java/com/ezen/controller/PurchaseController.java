package com.ezen.controller;

import com.ezen.entity.Funding;
import com.ezen.entity.Member;
import com.ezen.entity.Purchase;
import com.ezen.service.FundingService;
import com.ezen.service.MemberService;
import com.ezen.service.PurchaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FundingService fundingService;

    @GetMapping("/purchase")
    public String insertPurchaseForm(@SessionAttribute("member") Member member, Funding funding, Model model) {
        Map<String, String> addrMap = new HashMap<>();
        String[] addressArr = null;

        Funding findFunding = fundingService.getFunding(funding);
        Member findMember = memberService.getMember(member);
        addressArr = findMember.getAddress().split(",");

        addrMap.put("addr1", addressArr[0]);
        addrMap.put("addr2", addressArr[1]);
        addrMap.put("addr3", addressArr[2]);

        model.addAttribute("funding", findFunding);
        model.addAttribute("member", findMember);
        model.addAttribute("address", addrMap);

        return "purchase/insertPurchase";
    }

    @PostMapping("/purchase")
    public String insertPurchase() {
        return "redirect:/";
    }
}
