package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IBanque {
    public Optional<Compte> consulterCompte(String codeCpte);
    public void verser(String codeCpte,double montant);
    public void retirer(String codeCpte,double montant);
    public void virement(String codeCpte1,String codeCpte2,double montant);
    public Page<Operation> listOperation(String codecpt,int page,int size);
    Optional<Compte> findById(String id);



}
