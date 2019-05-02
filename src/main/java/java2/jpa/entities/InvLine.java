//package java2.jpa.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@Entity(name = "inv_lines")
//public class InvLine {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private int qty;
//    private BigDecimal price;
//
//    //--------------------------------------
//    @JsonIgnore // nurodome kad json objektas ignoruotų šito lauko foreignKey
//    @ManyToOne
//    private Invoice invoice;
//
//    @ManyToOne
//    private Product product;
//    //--------------------------------------
//
//    @Override
//    public String toString() {
//        return "\n   InvLine{" +
//                "id=" + id +
//                ", P R O D U C T=" + product +
//                ", qty=" + qty +
//                ", price=" + price +
////                ", invoice=" + invoice +
//                '}';
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Invoice getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(Invoice invoice) {
//        this.invoice = invoice;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public int getQty() {
//        return qty;
//    }
//
//    public void setQty(int qty) {
//        this.qty = qty;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//}
