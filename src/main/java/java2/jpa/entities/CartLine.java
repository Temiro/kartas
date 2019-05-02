package java2.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "cart_lines")
public class CartLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int qty;

    // ------------------------------------------------
    @JsonIgnore
    @ManyToOne
    private Cart cart;

//    @JsonIgnore
    @ManyToOne
    private Product product;
    // ------------------------------------------------

    @Override
    public String toString() {
        return "\n  CartLine{" +
                "id=" + id +
                ", product=" + product +
//                ", cart=" + cart +
                ", qty=" + qty +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
