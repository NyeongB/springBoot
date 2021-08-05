package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 필드주입, 세터주입 생성자주입이 있는데 생성자 주입을 권장한다.

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


}
