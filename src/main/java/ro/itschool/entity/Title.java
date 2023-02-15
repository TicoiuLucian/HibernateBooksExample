package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // - AUTO_INCREMENT
    private Integer id;

    @Column(name = "title_name", unique = true, nullable = false)
    private String name;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "title")
    private Book book;

    public Title(String name) {
        this.name = name;
    }
}
