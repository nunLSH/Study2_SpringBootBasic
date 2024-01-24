package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    // 메모리 회원 리포지토리와 고정 할인 정책을 구현체로 생성해서 사용중
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        // AppConfig에서 MemberRepository,DiscountPolicy의 구현체에 뭐가 들어갈지를 생성자를 통해서 선택
        // 생성자를 통해 외부(AppConfig)에서 주입된 매개변수들을 받아서 해당 클래스의 멤버 변수에 할당
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 주문 생성 요청이 오면 1. 회원 정보를 조회
        Member member = memberRepository.findById(memberId);
        // 2. 할인 정책에 회원과 가격을 넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 3. 최종 생성된 Order(주문을) 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() { // 역할
        return memberRepository; // 구현
    }
}
