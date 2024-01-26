package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // 메모리 회원 리포지토리와 고정 할인 정책을 구현체로 생성해서 사용중
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 만든 애노테이션 적용 @MainDiscountPolicy
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
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
