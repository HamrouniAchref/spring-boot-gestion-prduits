package tn.uma.isamm.spring.tp1.entities;

import java.util.Date;

public class QueryCommande {
	private Date dateCommande ;
	private String nomClient;
	private Double somme ;
	private Long idCommande;
	private String adresseLivraison;
	public String getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public QueryCommande(Long idCommande, Date dateCommande, String nomClient,String adresseLivraison, Double somme) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.nomClient = nomClient;
		this.adresseLivraison=adresseLivraison;
		this.somme = somme;
	}
	
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public Double getSomme() {
		return somme;
	}
	public void setSomme(Double somme) {
		this.somme = somme;
	}
	public Long getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	

}
