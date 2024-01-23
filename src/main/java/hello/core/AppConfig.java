package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 실제 동작에 필요한 구현 객체를 생성
@Configuration // 스프링에서는 설정 정보에 @Configuration를 적어줌
public class AppConfig {

    @Bean // 각 메서드에 @Bean을 적어주면 스프링 컨테이너에 등록됨
    public MemberService memberService() { // 역할
        return new MemberServiceImpl(memberRepository()); // 구현
    }

    @Bean
    public MemberRepository memberRepository() { // 역할
        return new MemoryMemberRepository(); // 구현
    }

    @Bean
    public OrderService orderService() { // 역할
        return new OrderServiceImpl(memberRepository(),discountPolicy()); // 구현
    }

    @Bean
    public DiscountPolicy discountPolicy() { // 역할
//        return new FixDiscountPolicy(); // 구현
        return new RateDiscountPolicy();
    }
}
