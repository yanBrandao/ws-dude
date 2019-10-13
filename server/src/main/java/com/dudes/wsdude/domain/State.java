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
@Table(name = "STATE")
public class State implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STT_ID", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name="STT_NAME")
    private String name;

    @NotNull
    @Column(name="STT_INITIALS", unique = true)
    private String initials;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STT_CON_ID")
    private Country country;

    public State(Long id){
        this.id = id;
    }
}