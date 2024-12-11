package digitalbankbackend.digitalbankved13.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@DiscriminatorValue("Sk") // Hadi seulment si on Utilise Single Table


public class Sparkonten extends Bankkonto {

   private double zinsen;

}
