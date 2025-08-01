package Lib;

public class DefaultPricingStrategy implements DiscountStrategy {

    @Override
    public double calculatePrice(CartItem item) {
         return item.getProduct().getPrice() * item.getQuantity();
    }
    
}
