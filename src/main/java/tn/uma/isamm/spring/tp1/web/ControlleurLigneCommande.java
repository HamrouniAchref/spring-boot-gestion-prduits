package tn.uma.isamm.spring.tp1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.isamm.spring.tp1.entities.Categorie;
import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.PK_PROD_CMD;
import tn.uma.isamm.spring.tp1.entities.Produit;
import tn.uma.isamm.spring.tp1.entities.ProduitAlimentaire;
import tn.uma.isamm.spring.tp1.metier.MetierVentes;

@Controller
public class ControlleurLigneCommande {
	@Autowired
	private MetierVentes metierVentes;
	@GetMapping("/admin/ajouterLigneCommande")
	public String ajouterLigneCommande(@RequestParam(name="id")Long id, 
	    @RequestParam(name = "errorMessage", defaultValue = "") String errorMessage,Model model) {
		List<Produit> produits = metierVentes.getProduits();
		Commande commande = metierVentes.getCommandeById(id);
		System.out.println(commande.getAdresseLivraison());
		model.addAttribute("produits", produits);
		model.addAttribute("ligneCommande", new LigneCommande());
		model.addAttribute("errorMessage", errorMessage);

		model.addAttribute("id",id);
		return "ligneCommande";

	}
	@PostMapping("/admin/ajouterLigneCommande")
	
	public String enregistrerLigneCommande(LigneCommande ligne, @RequestParam(name="id")Long id ) {
		if(ligne.getProduit()==null || ligne.getQte()<1)
		{
			return  "redirect:/admin/ajouterLigneCommande?id="+id+"&errorMessage=erreur de saisie ";
			
		}
		try 
		{
			Commande commande = metierVentes.getCommandeById(id);
			ligne.setCommande(commande);
			ligne.setPk(new PK_PROD_CMD());
	        System.out.println(ligne.getCommande());
			metierVentes.saveLigneCommande(ligne);
			
		}
		catch(Exception e)
		{
			return  "redirect:/admin/ajouterLigneCommande?id="+id+"&errorMessage=erreur sauvegarde ";
			
			
		}
		
		return  "redirect:/user/commandes/"+id;

	}
}
