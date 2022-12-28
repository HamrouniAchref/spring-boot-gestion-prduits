package tn.uma.isamm.spring.tp1.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tn.uma.isamm.spring.tp1.entities.Client;
import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.Produit;
import tn.uma.isamm.spring.tp1.entities.QueryCommande;
import tn.uma.isamm.spring.tp1.metier.MetierVentes;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlleurCommande {
	@Autowired
	private MetierVentes metierVentes;
	@RequestMapping("/user/commandes")
	public String Commandes(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size,
			@RequestParam(name = "errorMessage", defaultValue = "") String errorMessage,
			@RequestParam(name = "dateDebut" , defaultValue = "") String dateDebut,
			@RequestParam(name = "dateFin" , defaultValue = "") String dateFin){
		
		Page<QueryCommande> commandes;
       
        if (!dateDebut.equalsIgnoreCase("") && !dateFin.equalsIgnoreCase(""))
        {
        	
        	try {
				Date dateD = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateDebut.replace("T", " "));
				Date dateF = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateFin.replace("T", " "));
		        commandes = metierVentes.getAllCommandesSearch(dateD,dateF,page, size);
		        System.out.println("work");

			} catch (Exception e ) {
				commandes = metierVentes.getCommandesQuery(page, size);
				errorMessage= " erreur  date ";
				
			}  

        }
        else
        {
        	if((dateDebut.equalsIgnoreCase("") && !dateFin.equalsIgnoreCase("")) ||
        	   (!dateDebut.equalsIgnoreCase("") && dateFin.equalsIgnoreCase("")) )
        	{
        		errorMessage= "veuillez saisir la date debut et la de date de fin ";
        		
        	}
        	
        	 commandes = metierVentes.getCommandesQuery(page, size);
        }
      
		model.addAttribute("activePage", page);
		model.addAttribute("size", size);
		int[] taillePagination = IntStream.range(0, commandes.getTotalPages()).toArray();
		model.addAttribute("taillePagination", taillePagination);
		model.addAttribute("nbPages", commandes.getTotalPages());
		model.addAttribute("nbElements", commandes.getTotalElements());
		model.addAttribute("listeCommandes", commandes);
		model.addAttribute("errorMessage", errorMessage);
		
			
		
		return "commandes" ;
	}
	
	@RequestMapping("/user/commandes/{id}")
	public String commande(Model model, @PathVariable Long id)
	{
		
		Commande commande = metierVentes.getCommandeById(id);
		
		
		model.addAttribute(commande);	
		return "commande";
 	}
	@RequestMapping("/admin/ajouterCommande")
	public String ajouterCommande(Model model)
	{
		
		List<Produit> produits = metierVentes.getProduits();
		List<Client> clients = metierVentes.getClients();
		Commande commande = new Commande();
		for (Produit produit : produits) {
			commande.addLinge(new LigneCommande(produit));
			
		}
		
		model.addAttribute("commande",commande);	
		model.addAttribute("clients",clients);
		
		return "ajouterCommande";
 	}
	
	@PostMapping("/admin/ajouterCommande")
	public String sauvegarderCommande(@ModelAttribute("commande") Commande commande, Model model)
	{
		for (LigneCommande lign : commande.getLignes())
		{
			System.out.println(lign.getProduit().getDesigProduit());
			System.out.println(lign.getQte());
			
			
		}
		
		
		System.out.println(commande.getClient().getNomClient());
		metierVentes.saveCommande(commande);
		
		
		return "redirect:/user/commandes" ;
 	}
	

}
