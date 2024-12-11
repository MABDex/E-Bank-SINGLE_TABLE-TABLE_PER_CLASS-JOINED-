package digitalbankbackend.digitalbankved13.Repositories;

import digitalbankbackend.digitalbankved13.entities.Bankkonto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankkontoRep extends JpaRepository< Bankkonto , String> {

}
