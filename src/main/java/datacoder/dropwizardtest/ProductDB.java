package datacoder.dropwizardtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
 
public class ProductDB {
     
    public static HashMap<Integer, Product> products = new HashMap<>();
    static{
    	products.put(1, new Product(1, "Lokesh", "Gupta", "India"));
    	products.put(2, new Product(2, "John", "Gruber", "USA"));
    	products.put(3, new Product(3, "Melcum", "Marshal", "AUS"));
    }
     
    public static List<Product> getProducts(){
        return new ArrayList<Product>(products.values());
    }
     
    public static Product getProduct(Integer id){
        return products.get(id);
    }
     
    public static void updateProduct(Integer id, Product Product){
    	products.put(id, Product);
    }
     
    public static void removeProduct(Integer id){
    	products.remove(id);
    }
}
