package com.dudes.wsdude.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COUNTRY")
public class Country implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CON_ID", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name="CON_NAME", unique = true)
    private String name;

    @NotNull
    @Column(name="CON_INITIALS", unique = true)
    private String initials;

    public Country(Long id){
        this.id = id;
    }
}
