package digitalbankbackend.digitalbankved13.Repositories;

import digitalbankbackend.digitalbankved13.entities.Bankkontenoperationen;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankOpeartionRep extends JpaRepository< Bankkontenoperationen , Long> {

}
