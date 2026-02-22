import java.util.List;

public class DiscountRules implements DiscountCalculator {
    @Override
    public double calculateDiscount(String customerType, double subtotal, List<OrderLine> lines) {
        if(customerType.equals("student")) {
            if(subtotal >= 180.0){
                return 10.0;
            }
            return 0.0;
        }
        if(customerType.equals("staff")) {
            if(lines.size() >= 3){
                return 15.0;
            }
            return 5.0;
        }
        return 0.0;
    }
}