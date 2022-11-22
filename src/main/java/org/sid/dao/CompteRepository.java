package org.sid.dao;

import org.sid.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CompteRepository  extends   JpaRepository<Compte,String> {



    //Compte findOne(String primaryKey);

}
