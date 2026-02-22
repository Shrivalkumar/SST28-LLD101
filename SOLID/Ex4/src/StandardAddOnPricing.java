public class StandardAddOnPricing implements AddOnPricing{
    @Override
    public Money addOnPrice(AddOn addOnType){
        double add =0.0;
        if(addOnType == AddOn.MESS){
            add += 1000.0;
        }else if(addOnType == AddOn.LAUNDRY){
            add += 500.0;
        }else if(addOnType == AddOn.GYM){
            add += 300.0;
        }
        return new Money(add);
    }
}
