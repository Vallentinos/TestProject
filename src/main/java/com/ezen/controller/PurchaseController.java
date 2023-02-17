package com.ezen.controller;

import com.ezen.entity.Board;
import com.ezen.entity.Funding;
import com.ezen.entity.Member;
import com.ezen.entity.Purchase;
import com.ezen.service.FundingService;
import com.ezen.service.MemberService;
import com.ezen.service.PurchaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

        model.addAttribute("funding", findFunding);
        model.addAttribute("p_quantity", p_quantity);
        model.addAttribute("member", findMember);
        model.addAttribute("address", addrMap);

        return "purchase/insertPurchase";
    }

    @PostMapping("/insertPurchase")
    public @ResponseBody void insertPurchase(@RequestBody Map<String, String> map, Purchase purchase) {
        System.out.println("주문자 정보: " + map.entrySet());

        Funding funding = new Funding();
        funding.setFunding_seq(Long.valueOf(map.get("funding_seq")));
        Member member = new Member();
        member.setUsername(map.get("username"));

        System.out.println("펀딩: " + funding);
        System.out.println("멤버: " + member);

        purchase.setName(map.get("name"));
        purchase.setPhone(map.get("phone"));
        purchase.setEmail(map.get("email"));
        purchase.setZip_num(map.get("zip_num"));
        purchase.setAddress(map.get("address"));
        purchase.setPurchase_uid(map.get("purchase_uid"));
        purchase.setPrice(Integer.parseInt(map.get("price")));
        purchase.setFunding(funding);
        purchase.setMember(member);
        purchaseService.insertPurchase(purchase);
    }

    @GetMapping("/success")
    public String successPurchase() {
        return "purchase/successPurchase";
    }

    @GetMapping("/fail")
    public String failPurchase() {
        return "purchase/failPurchase";
    }

    @RequestMapping("/purchaseList")
    public String myPurchaseList(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @SessionAttribute("member") Member member, Model model) {

        Page<Purchase> purchaseList = purchaseService.findByMember_Username(page, member.getUsername());

        model.addAttribute("purchaseList", purchaseList);

        return "purchase/myPurchaseList";
    }
}
