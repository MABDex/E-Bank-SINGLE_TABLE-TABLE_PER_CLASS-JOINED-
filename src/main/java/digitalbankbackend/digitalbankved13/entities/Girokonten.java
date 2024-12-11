package digitalbankbackend.digitalbankved13.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@DiscriminatorValue("Gk") //Hadi seulment si on Utilise Single Table


public class Girokonten extends Bankkonto{

    private double ueberziehung; // t9der twsl bih l minus max howa

}
