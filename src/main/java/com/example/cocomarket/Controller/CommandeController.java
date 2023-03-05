package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ICommande;
import com.example.cocomarket.Services.Commande_Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@AllArgsConstructor
@RequestMapping("/Commande")

public class CommandeController {

    @Autowired
    Commande_Service Cr ;
    @Autowired
    ICommande ic;

    @PostMapping("/Confirm-Commande/{idcart}")
    public Commande confirm_Commande(@RequestBody Commande c,
                                     @PathVariable("idcart") Integer idcart) {
        return Cr.Confirmer_Commande(c,idcart);

    }


  /*  @PutMapping("/update-Commande/{Commande-id}")
    public Commande updateCommande(@PathVariable("Commande-id") Integer IdCommande) {
        return  Cr.Modifier_Commande(IdCommande);

    }*/


    @DeleteMapping("/remove-Commande/{Commande-id}")
    public void removeCommande(@PathVariable("Commande-id") Integer IdCommande) {
        Cr.Annuler_Commande(IdCommande);
    }



    @GetMapping("/retrieve-Commande/{Commande-id}")
    public Commande retrieveCommande(@PathVariable("Commande-id") Integer IdCommande) {
        return Cr.Afficher_Commandes(IdCommande);
    }


    @GetMapping("/retrieve-all-Commandes")
    public List<Commande> getCommandes() {
        return Cr.Afficher_AllCommandes();

    }

    @PutMapping(value="/affecter-commande-moderator/{commandId}/{userId}")
    public void affecterEtudiantToDepartement(@PathVariable("commandId") Integer Idcommande,
                                              @PathVariable("userId")Integer Iduser){
        Cr.send_order_to_moderator(Idcommande, Iduser);
    }
}
