package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 실제 동작에 필요한 구현 객체를 생성
public class AppConfig {

    public MemberService memberService() { // 역할
        return new MemberServiceImpl(memberRepository()); // 구현
    }

    private static MemberRepository memberRepository() { // 역할
        return new MemoryMemberRepository(); // 구현
    }

    public OrderService orderService() { // 역할
        return new OrderServiceImpl(memberRepository(),discountPolicy()); // 구현
    }

    public DiscountPolicy discountPolicy() { // 역할
        return new FixDiscountPolicy(); // 구현
    }
}
