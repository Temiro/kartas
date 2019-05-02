package java2.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "carts")
@NamedEntityGraph(
        name = Cart.GRAPH_CART_LINES,
        attributeNodes = @NamedAttributeNode(value = "cartLines"))
public class Cart {

    public static final String GRAPH_CART_LINES = "graph.cart.lines";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartLine> cartLines;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    private UUID uuid;

    private BigDecimal totalSum;

    // ------------------------------------------------
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", sum=" + totalSum +
                ", cartLines=" + cartLines +
//                ", user=" + user +
//                ", uuid=" + uuid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(Set<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public void addLine(CartLine line) {
        // TODO: 2019-04-13 pataisyti
//        line.setCart(this);
//        getLines().add(line);
    }

    public BigDecimal getTotalSum() {

        BigDecimal cartSum = BigDecimal.ZERO;

        for (CartLine cartLine : cartLines) {

            if (cartLine.getProduct().getPrice() != null) {
                cartSum = cartSum.add(BigDecimal.valueOf(cartLine.getQty()).multiply(cartLine.getProduct().getPrice()));
            }
        }
        return cartSum;
    }

    public void setTotalSum(BigDecimal sum) {
        this.totalSum = sum;
    }
}
