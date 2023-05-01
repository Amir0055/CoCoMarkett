package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Produit_Cart;
import com.example.cocomarket.Interfaces.StripeService;
import com.example.cocomarket.Repository.Commande_Repository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.stripe.exception.StripeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/stripe)")
@Slf4j
public class StripeController {


    @Autowired
    private StripeService stripeService;

    @Autowired
    private Commande_Repository cr ;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/paiement")
    public ResponseEntity<String> effectuerPaiement(@RequestParam("commandeId") Integer commandeId,
                                                    @RequestParam("devise") String devise,
                                                    @RequestParam("token") String token ,HttpServletResponse response) throws StripeException, IOException {
        Commande order = cr.findById(commandeId).get();
        stripeService.effectuerPaiement(order.getTotal_price(), devise, token);
       // System.out.println("paiement effectué avec succés, vous pouvez télécharger votre facture");
        // création du document PDF
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Image logo = Image.getInstance("src/main/java/com/example/cocomarket/Images/logo.jpg");
        logo.scaleToFit(70, 70);
        logo.setAbsolutePosition(document.getPageSize().getWidth() - 100, document.getPageSize().getHeight() - 80);
        logo.setIndentationLeft(-50f);
        document.add(logo);

        Paragraph title = new Paragraph("CoCo Market", new Font(Font.TIMES_ROMAN, 16, Font.BOLD));
        title.setAlignment(Element.ALIGN_LEFT);
        title.add(new Chunk(Chunk.NEWLINE));
        title.add(new Chunk(Chunk.NEWLINE));
        title.add(new Chunk("Facture", new Font(Font.TIMES_ROMAN, 16, Font.BOLD)));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        title.add(new Chunk(Chunk.NEWLINE));
        title.add(new Chunk(Chunk.NEWLINE));
        title.add(new Chunk(Chunk.NEWLINE));
        title.add(new Chunk(Chunk.NEWLINE));

        // ajouter les informations de la facture
        document.add(new Paragraph("Facture pour la commande N° :" + order.getId()));
        document.add(new Paragraph("Email du client: " + order.getBuyer_email() ));
        document.add(new Paragraph(" adresse du client: " + order.getBuyer_address() ));

        // ajouter les articles commandés
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Nom de l'article");
        table.addCell("Quantité");
        table.addCell("Prix");
        for (Produit_Cart item : order.getCommande_cart().getItems() ) {
            table.addCell(item.getProduit().getNom());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getProduit().getPrix()));

        }

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(table);

        // ajouter le total de la facture
        document.add(new Paragraph("TTC: " + order.getTotal_price()));

        document.close();

        // envoi du document PDF en réponse
        response.setContentType("facture/pdf");
        response.setContentLength(out.size());
        response.setHeader("Content-Disposition", "attachment; filename=\"facture.pdf\"");
        OutputStream outStream = response.getOutputStream();
        outStream.write(out.toByteArray());
        outStream.flush();
        outStream.close();


        return new ResponseEntity<>("paiement effectué avec succés, vous pouvez télécharger votre facture", HttpStatus.OK);
    }
}


  //'http://localhost:8088/stripe)/paiement?commandeId=12&devise=USD&token=tok_visa'