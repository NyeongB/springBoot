package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository1 = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired
    //@Autowired(required = false) 선택적으로 사용 가능
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy1 = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Autowired // 하나도없으면 생성자 //1번 호출 
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository2 = " + memberRepository);
        System.out.println("discountPolicy2 = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    
    
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*
    * 인터페이스도 구체적인것도 의존하는 경우임 DIP위반
    * 예를들어 정책을 fix -> rate로 바꾸는 순간 
    * 의존관계로인해 OrderServiceImpl의 소스코드도 함께 변경되어야 함
    * OCP위반
    * */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 오더 서비스 임플은 할인에 관한걸 모르고 할인의 변경이 필요하면 디스카운트폴리시만 바꾸면된다. 단일원칙 지켜짐
        // 주문쪽 까지 갈필요가없음, 만약 잘못됐으면 할인을 오더 서비스에서 고쳐야되고 하는 문제가 생김

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
