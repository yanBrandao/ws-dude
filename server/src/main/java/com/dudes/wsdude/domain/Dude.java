package domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DUDE")
public class Dude implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DUD_ID", unique = true, nullable = false)
    public Long id;

    @NotNull
    @Column(name="DUD_NAME")
    public String name;

    @NotNull
    @Column(name="DUD_LAST_NAME")
    public String lastName;

    @NotNull
    @Column(name="DUD_BORN_DATE")
    public LocalDateTime bornDate;

    @NotNull
    @Column(name="DUD_CPF")
    public String CPF;

    public Dude(Long id){
        this.id = id;
    }
}
