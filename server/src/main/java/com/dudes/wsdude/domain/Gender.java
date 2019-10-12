package com.dudes.wsdude.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GENDER")
public class Gender implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GEN_ID", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name="GEN_NAME")
    private String name;

    public Gender(Long id){
        this.id = id;
    }
}