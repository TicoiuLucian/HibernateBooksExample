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
public class PublishingHouse {

    @Id //-Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // - AUTO_INCREMENT
    private Integer id;

    private String name;

    private String address;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publishingHouse")
    private Set<Book> books = new HashSet<>();

    public PublishingHouse(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addBookToPublishingHouse(Book book) {
        books.add(book);
        book.setPublishingHouse(this);
    }

    public void removeBookFromPublishingHouse(Book book) {
        books.remove(book);
        book.setPublishingHouse(this);
    }

}
