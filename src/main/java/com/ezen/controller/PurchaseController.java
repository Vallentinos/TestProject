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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/purchase")
    public String insertPurchaseForm(@SessionAttribute("member") Member member, Funding funding,
                                     @RequestParam("p_quantity") int p_quantity, Model model) {

        Map<String, String> addrMap = new HashMap<>();
        String[] addressArr = null;

        Funding findFunding = fundingService.getFunding(funding);
        Member findMember = memberService.getMember(member);
        addressArr = findMember.getAddress().split(",");

        addrMap.put("addr1", addressArr[0]);
        addrMap.put("addr2", addressArr[1]);
        addrMap.put("addr3", addressArr[2]);

        int amount = funding.getPrice()*p_quantity;

        model.addAttribute("funding", findFunding);
        model.addAttribute("p_quantity", p_quantity);
        model.addAttribute("amount", amount);
        model.addAttribute("member", findMember);
        model.addAttribute("address", addrMap);

        return "purchase/insertPurchase";
    }

    @PostMapping("/insertPurchase")
    public void insertPurchase(Purchase purchase) {

    }
}
