package Lib;
    /**
     * ADT ที่ไม่เปลี่ยนรูป (Immutable) สำหรับเก็บข้อมูลสินค้า
     * คลาสที่เป็น final เพื่อป้องกันการสืบทอดและรับประกัน Immutability
     */
public final class Product {

    /**----------------------------
     * Rep invariant (RI) :
     * - productId and productName must not NULL/BLANK.
     * - price must not negative.
     * ---------------------------
     * Abstraction Function (AF) :
     * - AF(productId , productName , price) = A with 
     *   the given ID , name , and price
     */
    private final String productId ;
    private final String productName ;
    private final double price ;

    /**
     * ตรวจสอบ ว่า Rep Invariant เป็นจริงหรือไม่
     */
    private void checkRep(){
        if (productId == null || productId.isBlank()) {
            throw new RuntimeException("RI violated : productId is NULL/BLANK.") ;
        }
        if (productName == null || productName.isBlank()) {
            throw new RuntimeException("RI violated : productId is NULL/BLANK.") ;
        }
        if (price < 0) {
            throw new RuntimeException("RI violated : price is negative.") ;
        }
    }

    /** สร้าง Constructor product
     * @param productId รหัสสินค้า ห้ามเป็นค่าว่าง 
     * @param productName ชื่อสินค้า ห้ามเป็นค่าว่าง
     * @param price ราคา ต้องไม่ติดลบ
     */
    public Product(String productId , String productName , double price) {
        this.productId = productId ;
        this.productName = productName ;
        this.price = price ;
        checkRep(); //ตรวจสอบความถูกต้องทุกครั้งที่สร้าง
    }

    public String getProductId() { return productId ;}

    public String getProductName() { return productName ;}

    public double getPrice() { return price ;}

    /**
     * เปรียบเทียบ Product สองชิ้นโดยใช้ productId
     * @param obj อ็อบเจกต์ที่ต้องการเปลียบเทียบ
     * @retrun true หาก productId เหมือนกัน
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productId.equals(product.productId);
      
    }

    
}
