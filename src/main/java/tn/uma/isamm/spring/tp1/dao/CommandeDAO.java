package tn.uma.isamm.spring.tp1.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.QueryCommande;

public interface CommandeDAO extends JpaRepository<Commande, Long>{
	@Query(value = "SELECT new tn.uma.isamm.spring.tp1.entities.QueryCommande(c.numCommande , c.dateCommande  , cl.nomClient ,c.adresseLivraison , SUM(p.prixProduit * l.qte) )  "
			+ "FROM Commande c  , Produit p ,LigneCommande l , Client cl "
			+ "where c.client = cl and c = l.commande and l.produit =p  "
			+ "group by (c) ")
	public Page<QueryCommande> commandeCustumer(Pageable pageable);
	
	@Query(value = "SELECT new tn.uma.isamm.spring.tp1.entities.QueryCommande(c.numCommande , c.dateCommande  , cl.nomClient ,c.adresseLivraison , SUM(p.prixProduit * l.qte) )  "
			+ "FROM Commande c  , Produit p ,LigneCommande l , Client cl "
			+ "where c.client = cl and c = l.commande and l.produit =p  "
			+"and c.dateCommande BETWEEN :dateDebut AND :dateFin "
			+ "group by (c) ")
	public Page<QueryCommande> getAllCommandesSearch(@Param("dateDebut")Date dateDebut,@Param("dateFin")Date dateFin,Pageable pageable);
	@Query(value = "SELECT new tn.uma.isamm.spring.tp1.entities.QueryCommande(c.numCommande , c.dateCommande  , cl.nomClient ,c.adresseLivraison , SUM(p.prixProduit * l.qte) )  "
			+ "FROM Commande c  , Produit p ,LigneCommande l , Client cl "
			+ "where c.client = cl and c = l.commande and l.produit =p  "
			+ "group by (c) "
			+ "order by SUM(p.prixProduit * l.qte) desc  ")
	public Page<QueryCommande> getAllSortCommandesBySomme(Pageable pageable);
	@Query(value = "SELECT new tn.uma.isamm.spring.tp1.entities.QueryCommande(c.numCommande , c.dateCommande  , cl.nomClient ,c.adresseLivraison , SUM(p.prixProduit * l.qte) )  "
			+ "FROM Commande c  , Produit p ,LigneCommande l , Client cl "
			+ "where c.client = cl and c = l.commande and l.produit =p  "
			+ "group by (c) "
			+ "order by c.dateCommande desc  ")
	public Page<QueryCommande> getAllSortCommandesByDate(Pageable pageable);
	
	@Query(value = "SELECT new tn.uma.isamm.spring.tp1.entities.QueryCommande(c.numCommande , c.dateCommande  , cl.nomClient ,c.adresseLivraison , SUM(p.prixProduit * l.qte) )  "
			+ "FROM Commande c  , Produit p ,LigneCommande l , Client cl "
			+ "where c.client = cl and c = l.commande and l.produit =p  "
			+ "and c.etatLivraison = :etat "
			+ "group by (c) ")
	public Page<QueryCommande> getAllSortCommandesByEtat(@Param("etat")String etat,Pageable pageable);
	
}
