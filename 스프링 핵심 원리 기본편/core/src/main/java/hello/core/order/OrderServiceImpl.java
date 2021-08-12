package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 오더 서비스 임플은 할인에 관한걸 모르고 할인의 변경이 필요하면 디스카운트폴리시만 바꾸면된다. 단일원칙 지켜짐
        // 주문쪽 까지 갈필요가없음, 만약 잘못됐으면 할인을 오더 서비스에서 고쳐야되고 하는 문제가 생김

        return new Order(memberId, itemName, itemPrice, discountPrice);


    }
}
