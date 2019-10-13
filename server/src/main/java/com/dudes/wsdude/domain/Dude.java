package com.dudes.wsdude.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DUDE")
public class Dude implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DUD_ID", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name="DUD_NAME")
    private String name;

    @NotNull
    @Column(name="DUD_LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name="DUD_BORN_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bornDate;

    @NotNull
    @Column(name="DUD_CPF", unique = true, nullable = false)
    private String CPF;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DUD_GEN_ID")
    private Gender gender;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DUD_ADD_ID")
    private Address address;

    public Dude(Long id){
        this.id = id;
    }
}
