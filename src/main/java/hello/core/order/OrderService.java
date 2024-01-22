package hello.core.order;

public interface OrderService {

    /**
     * 클라이언트는 주문 생성을 할 때, 회원id, 상품명, 상품 가격을 파라미터로 넘겨야 함.
     * 그러면 return으로 주문 결과를 반환한다.
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
