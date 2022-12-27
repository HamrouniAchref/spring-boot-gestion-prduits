package tn.uma.isamm.spring.tp1.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Entity
public class LigneCommande implements Serializable{
	
	private int qte;
	
	@EmbeddedId
	private PK_PROD_CMD pk;
	@ManyToOne()
	@JoinColumn(name="id_produit")
    @MapsId("idProduit")

	@JsonBackReference
	Produit produit;
	
	
	@ManyToOne()
	@JoinColumn(name="id_commande")
    @MapsId("idCommande")
	@JsonBackReference

	Commande commande;

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public PK_PROD_CMD getPk() {
		return pk;
	}

	public void setPk(PK_PROD_CMD pk) {
		this.pk = pk;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public LigneCommande() {
		// TODO Auto-generated constructor stub
	}

	public LigneCommande(PK_PROD_CMD pk,int qte, Produit produit, Commande commande) {
		super();
		this.qte = qte;
		this.produit = produit;
		this.commande = commande;
		this.pk=pk;
	}
	public Double getPrixTotalProduit()
	{
		return this.produit.getPrixProduit()*this.qte;
		
		
	}



}
