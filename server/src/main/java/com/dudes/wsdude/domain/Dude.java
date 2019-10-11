package com.dudes.wsdude.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime bornDate;

    @NotNull
    @Column(name="DUD_CPF", unique = true, nullable = false)
    private String CPF;

    public Dude(Long id){
        this.id = id;
    }
}
