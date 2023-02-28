package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Categorie;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Raiting_Product;
import com.example.cocomarket.Entity.Shop;
import com.example.cocomarket.Interfaces.ICategorie;
import com.example.cocomarket.Interfaces.IProduit;
import com.example.cocomarket.Interfaces.IRainting_Product;
import com.example.cocomarket.Interfaces.IShop;
import com.example.cocomarket.Repository.Produit__Repository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;


@RestController
public class SnoussiController {

    @Autowired

    IShop shopinterface;

    @Autowired
    ICategorie categorieinterface;

    @Autowired
    IProduit produitinterface;

    @Autowired
    IRainting_Product raitingprointerface;


    @GetMapping("shop")
    public List<Shop> AfficherLesShop() {
        return shopinterface.AfficherLesShop();
    }

    @PostMapping("/add-shop")
    public Shop AddNewshop(@RequestBody Shop shp) {

        Shop shop = shopinterface.AddNewshop(shp);
        return shop;
    }

    @PostMapping("/add-Categorie")
    public Categorie AddnewCategorie(@RequestBody Categorie cat) {
        categorieinterface.AddSubnewCategorie(cat);
        Categorie categorie = categorieinterface.AddnewCategorie(cat);
        return categorie;
    }


    @DeleteMapping("/supprimershop/{idShop}")
    void supprimerShop(@PathVariable("idShop") int id) {
        shopinterface.supprimerShop(id);

    }


    @PostMapping("/ajouterProduit/{idShop}/{idCateg}")
    public void AddProduitAffeASHopAndAffeAcategorie(@RequestBody Produit produit, @PathVariable Integer idShop, @PathVariable Integer idCateg) {
        produitinterface.AddnewProduit(produit);
        produitinterface.AddProduitAffeASHopAndAffeAcategorie(produit.getId(), idShop, idCateg);
    }

    @PostMapping("/ajouterRainting/{idProduit}")
    public void AddRaitingtoProduit(@RequestBody Raiting_Product raintingP, @PathVariable Integer idProduit) {
        System.out.println("--------I'm IN Function--------");
        raitingprointerface.AddNEwRaitingProduit(raintingP);
        produitinterface.AddRaitingtoProduit(raintingP.getId(),idProduit);


    }


    private final Produit__Repository produit__repository;

    public SnoussiController(Produit__Repository produit__repository) {
        this.produit__repository = produit__repository;
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Produit>> searchForProduit(@SearchSpec Specification<Produit> specs) {
        return new ResponseEntity<>(produit__repository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

    @GetMapping(path = "/getqrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage generateQRCodeImage(@RequestParam String url) throws Exception {
//QRcode generator logic
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 250, 250);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    @GetMapping("/SumRaint")
    public String sumProduit (Integer idproduit,  Integer idporduit2 )

    {
        return produitinterface.SumRatting(idproduit,idporduit2) ;
    }

}





