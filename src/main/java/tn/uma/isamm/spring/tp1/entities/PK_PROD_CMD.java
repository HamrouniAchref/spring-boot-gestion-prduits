package tn.uma.isamm.spring.tp1.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@Embeddable
public class PK_PROD_CMD implements Serializable{
	@Column(name = "id_produit")
     private Long idProduit ;
	@Column(name = "id_commande")
     private Long idCommande ;
	
	

	public PK_PROD_CMD() {
		// TODO Auto-generated constructor stub
	}


	

}
