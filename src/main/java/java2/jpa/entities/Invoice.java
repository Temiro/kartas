//package java2.jpa.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity(name = "invoices")
//@NamedEntityGraph(
//        name = Invoice.GRAPH_LINES,
//        attributeNodes = @NamedAttributeNode(
//                value = "lines")
//)
//public class Invoice {
//
//    public static final String GRAPH_LINES = "invoise.lines";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String number;
//
//    private LocalDate date;
//
//    private BigDecimal sum;
//    //--------------------------------------
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "client_id", referencedColumnName = "id")
//    private Client client;
//
//    // .LAZY - paleidžiant ima tik invoice, bet neima visų InvLine, o paima tik tada, kai pašome (get...) List<InvLine>
//    // .EAGER - paleidžiant ima invoisceir visus InvLine
//    // CascadeType.ALL - sakome kad keičiant client > keičiasi ir visi surišti įrašai kitose lentelėse
//    @OneToMany
//            (mappedBy = "invoice",
//                    fetch = FetchType.LAZY,
//                    cascade = CascadeType.ALL, // klaida buvo čia: CascadeType.ALL ? koks čia parametrs
//                    orphanRemoval = true) // jeigu trinu Invoice(iš vidinės pusės), ką daryti su InvLines(su išorine puse)
//    // InvLine lauko @ManyToOne() Invoice invoice; pavadinimas
//    private List<InvLine> lines;
//    //--------------------------------------
//
//    public void addLine(InvLine lines) {
//        lines.setInvoice(this);
//        getLines().add(lines);
//    }
//
//    //------------------------------------
//    @Override
//    public String toString() {
//        return "\n  Invoice{" +
//                "id=" + id +
//                ", date=" + date +
//                ", number='" + number + '\'' +
//                ", sum=" + sum +
////                ", CLIENT=" + client +
//                ", LINES=" + lines +
//                '}';
//    }
//
//    public List<InvLine> getLines() {
//        return lines;
//    }
//
//    public void setLines(List<InvLine> lines) {
//        this.lines = lines;
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
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public BigDecimal getSum() {
//        return sum;
//    }
//
//    public void setSum(BigDecimal sum) {
//        this.sum = sum;
//    }
//
//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }
//}
