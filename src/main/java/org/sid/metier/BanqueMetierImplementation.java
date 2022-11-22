package org.sid.metier;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

// pour Spring instancier cette classe au demarrage
@Service
// gerer les transaction au niveau de la couche metier
@Transactional
public class BanqueMetierImplementation implements  IBanque{
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Override
    public Optional<Compte> consulterCompte(String codeCpte) {
        Optional<Compte> cp = compteRepository.findById(codeCpte);
        if(cp==null) throw new RuntimeException("Compte Introuvable!");
        return cp;
    }
    @Override
    public Optional<Compte> findById(String codeCpte) {

        return compteRepository.findById(codeCpte);
    }

    @Override
    public void verser(String codeCpte, double montant) {
        if(findById(codeCpte).isPresent()){
            Optional<Compte> cp = findById(codeCpte);
            Versement v = new Versement(new Date() , montant, cp.get());
            operationRepository.save(v);
            cp.get().setSolde(cp.get().getSolde()+montant);
            compteRepository.save(cp.get());
        }

    }

    @Override
    public void retirer(String codeCpte, double montant) {
        Optional<Compte> cp = findById(codeCpte);

        if (findById(codeCpte).isPresent()) {
            if (cp.get() instanceof CompteCourant) {
                if (cp.get().getSolde() < montant) {
                    throw new RuntimeException("Solde insuffisant");

                } else {
                    Retrait r = new Retrait(new Date(), montant, cp.get());
                    operationRepository.save(r);
                    cp.get().setSolde(cp.get().getSolde() - montant);
                    compteRepository.save(cp.get());
                }
            }

        }
    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {
        if(codeCpte1.equals(codeCpte2)){
            throw  new RuntimeException("Operation Impossimble ");
        }
        else
        retirer(codeCpte1,2500);
        verser(codeCpte2,2500);

    }



    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int size) {
        return operationRepository.listOperation(codeCpte,PageRequest.of(page,size));
    }
}
