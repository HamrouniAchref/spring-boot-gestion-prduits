package tn.uma.isamm.spring.tp1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import antlr.collections.List;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.PK_PROD_CMD;

public interface LigneCommandeDAO extends JpaRepository<LigneCommande, PK_PROD_CMD>{
	
}
