package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int dicountFixAccount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        // VIP면 1000원 할인, 아니면 할인 없음
        if (member.getGrade() == Grade.VIP) {
            return dicountFixAccount;
        } else {
            return 0;
        }
    }
}
