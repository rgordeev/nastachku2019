package ru.multicon.serviceb.entities;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity(name = "Good")
@Table(name = "goods")
@NoArgsConstructor
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_gen")
    @SequenceGenerator(
            name = "goods_gen",
            sequenceName = "goods_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 255)
    private String title;

    public Good(String title) {
        this.title = title;
    }
}
