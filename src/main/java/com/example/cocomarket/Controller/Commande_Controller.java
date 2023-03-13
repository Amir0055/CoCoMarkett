package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Entity.Produit_Cart;
import com.example.cocomarket.Interfaces.ICommande;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Services.Commande_Service;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/commande")
public class Commande_Controller {
    @Autowired
    ICommande ic;
    @Autowired
    Commande_Service Cr ;
    @Autowired
    Commande_Repository commande_repository;

    @PutMapping("/add-assign-liv/{region}")
    @ResponseBody
    public Livraison addLivwithcommand(@RequestBody Livraison l,
                                                      @PathVariable("region") String region)
    {
        Livraison liv = ic.affectercamandtolivaison(region,l);
        return liv;
    }

    @PostMapping("/Confirm-Commande/{idcart}")
    public Commande confirm_Commande(@RequestBody Commande c,
                                     @PathVariable("idcart") Integer idcart)  {
        return Cr.Confirmer_Commande(c,idcart);

    }

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


    @GetMapping("/research")
    public List<Commande> rechercher(@RequestParam("q") String parametre) {
        return Cr.rechercher(parametre);
    }

    @GetMapping("/facture/{commandId}")
    public void generateInvoice(@PathVariable Integer commandId, HttpServletResponse response) throws Exception {
        // récupérer les informations de la commande à partir de votre base de données
        Commande order = commande_repository.findById(commandId).get();

        // création du document PDF
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        // ajouter les informations de la facture
        document.add(new Paragraph("Facture pour la commande #" + order.getId()));
        document.add(new Paragraph("Email et adresse du client: " + order.getBuyer_email() + ", " + order.getBuyer_address()));


        // ajouter les articles commandés
        PdfPTable table = new PdfPTable(3);
        table.addCell("Nom de l'article");
        table.addCell("Quantité");
        table.addCell("Prix");
        for (Produit_Cart item : order.getCommande_cart().getItems() ) {
            table.addCell(item.getProduit().getNom());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getProduit().getPrix()));
        }
        document.add(table);

        // ajouter le total de la facture
        document.add(new Paragraph("Total: " + order.getTotal_price()));

        document.close();

        // envoi du document PDF en réponse
        response.setContentType("facture/pdf");
        response.setContentLength(out.size());
        response.setHeader("Content-Disposition", "attachment; filename=\"facture.pdf\"");
        OutputStream outStream = response.getOutputStream();
        outStream.write(out.toByteArray());
        outStream.flush();
        outStream.close();
    }
}
