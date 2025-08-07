package Lib;
import java.util.ArrayList;

/**
 * ADT สำหรับจัดการตะกร้าสินค้า
 */
public class ShoppingCart {
    private final ArrayList<CartItem> items = new ArrayList<>();
    private final PricingService pricingService;
    private final ProductCatalog catalog;
    
    // Rep Invariant (RI):
    // - items ต้องไม่เป็น null
    // - ห้ามมี Product ที่ซ้ำกันใน CartItem ที่แตกต่างกัน
    // - pricingService และ catalog ต้องไม่เป็น null
    
    /**
     * ตรวจสอบว่า Rep Invariant เป็นจริงหรือไม่
     */
    private void checkRep() {
        if (items == null) {
            throw new RuntimeException("RI violated: items cannot be null");
        }
        if (pricingService == null) {
            throw new RuntimeException("RI violated: pricingService cannot be null");
        }
        if (catalog == null) {
            throw new RuntimeException("RI violated: catalog cannot be null");
        }
        
        // ตรวจสอบว่าไม่มี Product ที่ซ้ำกัน
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).getProduct().equals(items.get(j).getProduct())) {
                    throw new RuntimeException("RI violated: duplicate products in cart");
                }
            }
        }
    }
    
    /**
     * Constructor สำหรับสร้าง ShoppingCart
     * @param pricingService บริการคำนวณราคา
     * @param catalog แคตตาล็อกสินค้า
     */
    public ShoppingCart(PricingService pricingService, ProductCatalog catalog) {
        this.pricingService = pricingService;
        this.catalog = catalog;
        checkRep();
    }
    
    /**
     * เพิ่มสินค้าลงในตะกร้า
     * @param productId รหัสสินค้า
     * @param quantity จำนวนที่ต้องการเพิ่ม
     */
    public void addItem(String productId, int quantity) {
        if (quantity <= 0) {
            checkRep();
            return; // ไม่เพิ่มถ้าจำนวนไม่ถูกต้อง
        }
        
        Product product = catalog.findById(productId);
        if (product == null) {
            checkRep();
            return; // ไม่เพิ่มถ้าไม่พบสินค้า
        }
        
        // ตรวจสอบว่ามีสินค้านี้ในตะกร้าแล้วหรือไม่
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                checkRep();
                return;
            }
        }
        
        // ถ้าไม่มี ให้สร้าง CartItem ใหม่
        items.add(new CartItem(product, quantity));
        checkRep();
    }
    
    /**
     * ลบสินค้าออกจากตะกร้า
     * @param productId รหัสสินค้าที่ต้องการลบ
     */
    public void removeItem(String productId) {
        Product productToRemove = catalog.findById(productId);
        if (productToRemove == null) {
            checkRep();
            return; // ไม่ทำอะไรถ้าไม่พบสินค้า
        }
        
        CartItem itemToRemove = null;
        for (CartItem item : items) {
            if (item.getProduct().equals(productToRemove)) {
                itemToRemove = item;
                break;
            }
        }
        
        if (itemToRemove != null) {
            items.remove(itemToRemove);
        }
        
        checkRep();
    }
    
    /**
     * ล้างตะกร้าสินค้าทั้งหมด
     */
    public void clearCart() {
        items.clear();
        checkRep();
    }
    
    /**
     * คำนวณราคารวมของสินค้าในตะกร้า
     * @return ราคารวม
     */
    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += pricingService.calculateItemPrice(item);
        }
        return total;
    }
    
    /**
     * ได้จำนวนรายการสินค้าในตะกร้า (ไม่ใช่จำนวนชิ้น)
     * @return จำนวนรายการสินค้า
     */
    public int getItemCount() {
        return items.size();
    }
}