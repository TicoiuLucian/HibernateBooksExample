package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String author;

    private String title;

    private Integer publishingYear;

    //    @Transient // nu creaza coloana in DB daca punem @Transient
    private Float price;

    private String publishingHouse;

}
