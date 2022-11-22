package org.sid;
import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.*;
import org.sid.metier.IBanque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
public class VotreBanqueApplication implements CommandLineRunner {

    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public CompteRepository compteRepository;
    @Autowired
    public OperationRepository operationRepository;

    @Autowired
    public IBanque iBankMetier;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(VotreBanqueApplication.class, args);
    }
	// les test


    @Override
    public void run(String... args) throws Exception {
/*
        Client c1 = clientRepository.save(new Client("Soufiane","soufianehiba@gmail.com"));
        Client c2 = clientRepository.save(new Client("Hiba","hibasoufiane@gmail.com"));
        Client c3 = clientRepository.save(new Client("NIYA","niya@gmail.com"));

        Compte cp1  = compteRepository.save(new CompteCourant("code1",new Date(),9000,c1,6000 ));
        Compte cp2  = compteRepository.save(new CompteCourant("code2",new Date(),8000,c2,6000 ));
        Compte cp3  = compteRepository.save(new CompteCourant("code3",new Date(),7000,c3,6000 ));

        Compte cp4 = compteRepository.save(new CompteEpargne("code4",new Date(),5000,c1,5));
        Compte cp5 = compteRepository.save(new CompteEpargne("code5",new Date(),4000,c2,6));
        Compte cp6 = compteRepository.save(new CompteEpargne("code6",new Date(),3000,c3,7));

        Operation op1 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op2 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op3 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op4 = operationRepository.save(new Versement(new Date() , 5000 , cp1));
        Operation op5 = operationRepository.save(new Versement(new Date() , 4000 , cp1));
        Operation op6 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op7 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op8 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op9 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op10 = operationRepository.save(new Versement(new Date() , 3000 , cp1));
        Operation op11 = operationRepository.save(new Versement(new Date() , 2000 , cp2));
        Operation op12 = operationRepository.save(new Retrait(new Date() , 1000 , cp3));

//        iBankMetier.verser("code1",1000);
		System.out.println(iBankMetier.consulterCompte("code3").get().getSolde());
*/

    }
}

