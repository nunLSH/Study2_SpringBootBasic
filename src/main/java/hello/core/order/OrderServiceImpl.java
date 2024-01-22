package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 메모리 회원 리포지토리와 고정 할인 정책을 구현체로 생성해서 사용중
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 주문 생성 요청이 오면 1. 회원 정보를 조회
        Member member = memberRepository.findById(memberId);
        // 2. 할인 정책에 회원과 가격을 넘김
        /* Order입장에서는 할인에 대해서는 discountPolicy가 알아서 처리해서 결과만 던져줌.
           SRP 단일 책임 원칙이 잘 지켜졌다고 볼 수 있음.
           할인에 대한 변경이 필요하면 주문 부분은 수정할 필요 없이 할인 부분만 수정하면 됨. */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 3. 최종 생성된 Order(주문을) 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
