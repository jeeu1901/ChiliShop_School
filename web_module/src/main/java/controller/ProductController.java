package controller;

import interfaces.ProductLocal;
import model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    ProductLocal productSession;

    private List<Product> productList;
    private List<Product> cartList = new ArrayList<>();
    private List<Product> filteredProductList;
    private Product selectedProduct;
    private String searchInput;
    private int productQuantity;



    @PostConstruct
    public void init() {
        setProductList(productSession.loadAllProducts());
        filteredProductList = productList;
    }


    public void searchProduct(String searchInput) {
        filteredProductList = productSession.searchProduct(searchInput, productList);
    }

    // Testar mest för frontend - Ska fixas så ORDER - ORDERITEM finns i db
    public void addToCart(Long id) {
        Product productItem = productList.stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElse(null);
        cartList.add(productItem);
    }

    public void emptyCart() {
        cartList.clear();
    }

    public void deleteCartItem(Product product) {
        cartList.remove(product);
    }


    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getCartList() {
        return cartList;
    }

    public void setCartList(List<Product> cartList) {
        this.cartList = cartList;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public List<Product> getFilteredProductList() {
        return filteredProductList;
    }

    public void setFilteredProductList(List<Product> filteredProductList) {
        this.filteredProductList = filteredProductList;
    }
}
