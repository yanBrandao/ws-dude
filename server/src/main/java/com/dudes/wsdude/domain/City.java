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
@Table(name = "CITY")
public class City implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CIT_ID", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name="CIT_NAME")
    private String name;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CIT_STT_ID")
    private State state;

    public City(Long id){
        this.id = id;
    }
}
