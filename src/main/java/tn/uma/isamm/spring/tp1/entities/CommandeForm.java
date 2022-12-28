package tn.uma.isamm.spring.tp1.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class CommandeForm {
	private Commande commande = new Commande() ;
	private Client client;
	private List<LigneCommande> lignes=new ArrayList<LigneCommande>();


	public void addLinge(LigneCommande book) {
        this.lignes.add(book);
    }
	

}
