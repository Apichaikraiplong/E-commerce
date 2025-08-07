package Lib;
    /**
     * ADT คลาสทำหน้าทีเป็นแคตตาล็อคสินค้า (Repository)
     */

import java.util.ArrayList;

public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();

    /**----------------------------
     * Rep invariant (RI) :
     * - products list is not NULL , contains no NULL elements , 
     *   and no duplicate products.
     * ---------------------------
     * Abstraction Function (AF) :
     * - AF(products) = A catalog of all available products.
     */

     private void checkRep() {
        if (products == null) {
            throw new RuntimeException("RI violate products is NULL.");
        }
        // check for duplicate products
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    throw new RuntimeException("RI violate product is duplicate.");
                }
            }
        }
     }

     public ProductCatalog() {
        checkRep();
     }
     
     /**
      * เพิ่มสินค้ามาใหม่เข้าสู่แคตตาล็อก
      * @param product สินค้าที่ต้องการเพิ่ม
      */
     public void addProduct(Product product) {
        if (product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
     }
     /**
      * ค้นหาสินค้าจากรหัสสินค้า
      * @param productId รหัสสินค้าที่ต้องการค้นหา
      * @return อ็อบเจ็กต์ Product หากพบ หรือ ไม่พบ null 
      */
    public Product findById(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }
}       
     
