package interfaces;

import model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductLocal{

    List<Product> loadAllProducts();

    List<Product> searchProduct(String searchInput, List<Product> list);

    List<Product> updateProductPricing(List<Product> list);
}
