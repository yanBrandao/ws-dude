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
@Table(name = "ADDRESS")
public class Address implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADD_ID", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name="ADD_STREET")
    private String street;

    @NotNull
    @Column(name="ADD_NEIGHBORHOOD")
    private String neighborhood;

    @NotNull
    @Column(name="ADD_NUMBER")
    private String number;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADD_CIT_ID")
    private City city;

    public Address(Long id){
        this.id = id;
    }
}