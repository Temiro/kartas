//package java2.jpa.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity(name = "clients") // jeigu nenurodome Entity name = ... tai bus naudjamas klasė vardas Client
//@Table // lentelės duomenų bazėje pavadinima
////@Entity(name = "clients") // keičiamas entity vardas iš Client į client
//// 1 nurodom grafo vardą name =...
//// 2 nurodom kokius entiti ištrauktu kartu su šiuo enti'čiu attributeNodes =...
////@NamedEntityGraph(name = "graph.Client.invoices",
////        attributeNodes = @NamedAttributeNode(value = "invoices", subgraph = "invoices"),
////        subgraphs = @NamedSubgraph(name = "invoices", attributeNodes = @NamedAttributeNode("lines")))
//// Graph - nurodo kelia kokia tvarka eiti nuo vieno taško prie kito, nuo vieno entity prie kito entity
//@NamedEntityGraphs({
//        @NamedEntityGraph(
//                name = Client.CLIENTS_INVOICES_ONLY,
//                attributeNodes = @NamedAttributeNode(
//                        value = "invoices")
//        ),
//        @NamedEntityGraph(
//                name = Client.CLIENTS_INVOICES_INVLINES_PRODUCTS,
//                attributeNodes = @NamedAttributeNode(
//                        value = "invoices",
//                        subgraph = "invoices_lines"),
//                // subgraphs yra sudedamoji graph dalis
//                subgraphs = {
//                        @NamedSubgraph(
//                                name = "invoices_lines",
//                                attributeNodes = @NamedAttributeNode(
//                                        value = "lines",
//                                        subgraph = "line-product")
//                        ),
//                        @NamedSubgraph(
//                                name = "line-product",
//                                attributeNodes = @NamedAttributeNode(value = "product")
//                        )
//                }
//        )
//})
//@NamedQueries({
//        @NamedQuery(
//                name = Client.QUERY_BY_CITY,
//                query = "SELECT c FROM clients c WHERE c.city = :city"
//        )
//})
//public class Client {
//
//    public final static String CLIENTS_INVOICES_ONLY = "clients-invoices-only";
//    public final static String CLIENTS_INVOICES_INVLINES_PRODUCTS = "clients-invoices-invlines-products";
//    public final static String QUERY_BY_CITY = "query.clients.clients";
//    public final static String QUERY_INVOICES_ONLY = "query.invoices";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private String city;
//    private int discount;
//
//    @Column(length = 10)
//    private String post;
//    //--------------------------------------
//    // .LAZY - paleidžiant ima tik invoices, bet neima visų InvLine, o paima tik tada, kai pašome (get...) List<InvLine>
//    // .EAGER - paleidžiant ima invoisceir visus InvLine
//    @JsonIgnore
//    @OneToMany(
//            mappedBy = "client", //  -> Invoice.Client client
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true) //
////    @Expose // nauojamas tk su Gson objektais
//    private List<Invoice> invoices; //  -> attributeNodes = @NamedAttributeNode(value = "invoices"...)
//    //--------------------------------------
//
//    @Override
//    public String toString() {
//        return "\n  Client{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", city='" + city + '\'' +
//                ", discount=" + discount +
//                '}';
//    }
//
//    public String toStringClientInvoices() {
//        return "Client{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", city='" + city + '\'' +
//                ", discount=" + discount +
//                ", post='" + post + '\'' +
//                ", invoices=" + invoices +
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public int getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(int discount) {
//        this.discount = discount;
//    }
//
//    public String getPost() {
//        return post;
//    }
//
//    public void setPost(String post) {
//        this.post = post;
//    }
//
//    public List<Invoice> getInvoices() {
//        return invoices;
//    }
//
//    public void setInvoices(List<Invoice> invoices) {
//        this.invoices = invoices;
//    }
//}
