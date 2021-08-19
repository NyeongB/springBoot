package hello.core.member;

public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //private MemberRepository memberRepository = new MemoryMemberRepository();

    // 멤버서비스 구현체가 멤버리포지토리 추상화에도 의존, 메모리멤버리포지토리인 구현체에도 의존
    // OCP 위배, DIP 위배 좋은 코드는 아니다.


    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용도
    public MemberRepository getMemberRepository()  {
        return memberRepository;
    }


}
