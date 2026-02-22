public class TaxRules implements TaxCalculator {
    @Override
    public double calculateTax(String customerType, double subtotal){
        return subtotal * (getTaxPercent(customerType) / 100.0);
    }

    @Override
    public double getTaxPercent(String customerType) {
        if (customerType.equals("student")){
            return 5.0;
        }
        if (customerType.equals("staff")){
            return 2.0;
        }
        return 8.0;
    }
}