package digitalbankbackend.digitalbankved13.entities;
import digitalbankbackend.digitalbankved13.entities.Bankkonto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Kunde {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vorname;
    private String email;

    @OneToMany(mappedBy = "kunde") // Beziehung zu Bankkonto
    private List<Bankkonto> bankkontos;

}
