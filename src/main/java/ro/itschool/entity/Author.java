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
public class Author {

    @Id //-Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // - AUTO_INCREMENT
    private Integer id;

    private String name;

    private Integer age;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors") //MappedBy inseamna ca e non-owning side
    private Set<Book> books = new HashSet<>();

    public Author(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void addBookToAuthor(Book book) {
        books.add(book);
    }

    public void removeBookFromAuthor(Book book) {
        books.remove(book);
    }

}
