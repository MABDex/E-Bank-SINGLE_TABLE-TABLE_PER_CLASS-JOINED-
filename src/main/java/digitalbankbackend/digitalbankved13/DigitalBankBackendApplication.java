package digitalbankbackend.digitalbankved13;

import digitalbankbackend.digitalbankved13.Enum.KontoStatus;
import digitalbankbackend.digitalbankved13.Enum.OperationType;
import digitalbankbackend.digitalbankved13.Repositories.BankOpeartionRep;
import digitalbankbackend.digitalbankved13.Repositories.BankkontoRep;
import digitalbankbackend.digitalbankved13.Repositories.KundeRep;
import digitalbankbackend.digitalbankved13.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.UUID;


@SpringBootApplication
public class DigitalBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankkontoRep bankkontoRep) {
        return args -> {
            Bankkonto Bkobj = bankkontoRep.findById("34d2e5b5-50d0-4cf1-94fb-569e08f77cd3").orElse(null);

            if (Bkobj != null) {
                System.out.println("##########################################################");
                System.out.println(Bkobj.getId());
                System.out.println(Bkobj.getBalance());
                System.out.println(Bkobj.getStatus());
                System.out.println(Bkobj.getErstellungsdatum());
                System.out.println(Bkobj.getKunde().getVorname());
                System.out.println(Bkobj.getClass().getSimpleName());

                if (Bkobj instanceof Girokonten) {
                    System.out.println("Überziehung" + ((Girokonten) Bkobj).getUeberziehung());
                } else if (Bkobj instanceof Sparkonten) {
                    System.out.println("Zinsen" + ((Sparkonten) Bkobj).getZinsen());
                } else {
                    System.out.println("Is Not Available");
                }

                /*Ila Makntich dayr fetch=EAGER f OneToMany Ra mahaykhdmch lik hadchi li lte7t Hit EAGER
                 haystocker ta les autres Atributs ms Problem dyal Eager ha ystocker bzaf f memoire.

                 Autre Posibilite : Nkhliw Fetch = Lazy , Hit kaydir le chargement a La demande si On a Besoin
                 Ms il faut que L on modifier dans La couche Service
                */

                Bkobj.getBankkontenoperatons().forEach(op -> {
                            System.out.println("Operationen ---------");
                            System.out.println(op.getType());
                            System.out.println(op.getAmount());
                            System.out.println(op.getOperationsDatum());
                        }

                );

            }
        };
    }



    // @Bean
    CommandLineRunner commandLineRunner(BankkontoRep bankkonto, KundeRep kundeRep, BankOpeartionRep bankOpeartionRep, BankkontoRep bankkontoRep) {
        return args -> {



            // Beispiel-Daten in die Kunde-Tabelle speichern
            kundeRep.save(new Kunde(null, "Hassan", "hassan@gmail.com", null));
            kundeRep.save(new Kunde(null, "JanaM", "janam@gmail.com", null));
            kundeRep.save(new Kunde(null, "MedMab", "medmab@gmail.com", null));
            kundeRep.save(new Kunde(null, "AlexM", "alexm@gmail.com", null));



             // ---------------> Creation de Comptes pour chaque Client [GiroKonto]
            kundeRep.findAll().forEach(objkundgk -> {
                Girokonten g1 = new Girokonten();
                g1.setId(UUID.randomUUID().toString());
                g1.setBalance(Math.random() * 1500);
                g1.setErstellungsdatum(new Date());
                g1.setStatus(KontoStatus.CREATED);
                g1.setKunde(objkundgk);
                g1.setUeberziehung(605);
                bankkontoRep.save(g1);



            });

            //-----------------> Creation de Comptes pour chaque Client [SparKonto]
            kundeRep.findAll().forEach(objkundsk -> {
                Sparkonten s1 = new Sparkonten();
                s1.setId(UUID.randomUUID().toString());
                s1.setBalance(Math.random() * 1500);
                s1.setErstellungsdatum(new Date());
                s1.setStatus(KontoStatus.CREATED);
                s1.setKunde(objkundsk);
                s1.setZinsen(3.5);
                bankkontoRep.save(s1);





                //-----> Operations Des Comptes
                bankkontoRep.findAll().forEach(objOperation -> {

                    for (int i=0 ; i<10 ; i++) {
                        Bankkontenoperationen bkoper = new Bankkontenoperationen();
                        bkoper.setOperationsDatum(new Date());
                        bkoper.setAmount(Math.random() * 125);
                        bkoper.setType(Math.random()> 0.5 ? OperationType.CREDIT:OperationType.DEBIT);
                        bkoper.setBankkonto(objOperation);
                        bankOpeartionRep.save(bkoper);
                    }


                });
            });







        };




            }

}
