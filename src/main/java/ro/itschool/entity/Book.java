package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "Tabela_Carti")
public class Book {

    @Id //-Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // - AUTO_INCREMENT
    private Integer id;

    //    @Column(name = "Scriitor", nullable = false, unique = true, length = 20)
//    private String author;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Title title;

    private Integer publishingYear;

    //    @Transient // nu creaza coloana in DB daca punem @Transient
    private Float price;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private PublishingHouse publishingHouse;


    public void addAuthorToBook(Author author) {
        authors.add(author);
    }

    public void removeAuthorFromBook(Author author) {
        authors.remove(author);
    }

}
