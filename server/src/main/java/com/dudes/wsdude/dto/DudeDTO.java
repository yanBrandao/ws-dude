package dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DudeDTO extends BaseDTO {
    public Long id;
    public String name;
    public String lastName;
    public LocalDateTime bornDate;
    public String CPF;

    public DudeDTO(Long id){
        this.id = id;
    }
}
