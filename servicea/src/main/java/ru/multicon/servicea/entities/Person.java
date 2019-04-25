package ru.multicon.servicea.entities;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity(name = "Person")
@Table(name = "person")
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_gen")
    @SequenceGenerator(
            name = "person_gen",
            sequenceName = "person_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
