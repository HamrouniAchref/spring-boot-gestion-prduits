package tn.uma.isamm.spring.tp1.metier;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import tn.uma.isamm.spring.tp1.entities.AppRole;
import tn.uma.isamm.spring.tp1.entities.AppUser;
import tn.uma.isamm.spring.tp1.entities.Categorie;
import tn.uma.isamm.spring.tp1.entities.Client;
import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.Produit;
import tn.uma.isamm.spring.tp1.entities.QueryCommande;

public interface MetierVentes {
	public List<Produit> getProduits();
	public void saveProduit(Produit p);
	public Produit getProduitById(long id);
	public Page<Produit> getProduitsPageable(int page, int size);
	public List<Categorie> getCategories();
	public void deleteProduit(Long id);
	public AppUser saveAppUser(AppUser appUser);
	public AppRole saveAppRole(AppRole appRole);
	public void addRoleToUser(String login, String nomRole);
	public AppUser getUserByLogin(String login);
	public List<AppUser> getAppUsers();
	public Page<Commande> getCommandesPageable(int page, int size);

	public List<Commande> getCommandes();
	public Page<QueryCommande> getCommandesQuery(int page, int size);
	
	public Page<QueryCommande> getAllCommandesSearch(Date dateDebut ,Date dateFin , int page, int size );
	public Commande getCommandeById(long id);
	public void saveLigneCommande(LigneCommande ligne);
	public Commande saveCommande(Commande commande);

	public List<Client> getClients();
	public Commande saveCommandeUpdate(Commande cm) ;
	public Commande setLigneCommande(Commande commande) ;
	void deleteCommande(Long id);
	Page<QueryCommande> getAllSortCommandesBySomme(int page, int size);
	Page<QueryCommande> getAllSortCommandesByDate(int page, int size);
	Page<QueryCommande> getAllSortCommandesByEtat(String etat, int page, int size);


	
	
	//Page<Produit> getProduitsPageableByDesignation(int page, int size, String mc);
	

	

}
