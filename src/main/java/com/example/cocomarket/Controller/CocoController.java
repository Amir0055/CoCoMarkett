package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Catalogue;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Repository.Catalogue_Repository;
import com.example.cocomarket.Repository.Produit__Repository;
import com.example.cocomarket.Services.Catalogue_Service;
import com.example.cocomarket.Services.Produit__Service;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/catalogue")
public class CocoController {


    @Autowired
    private Catalogue_Service catalogueService;



    private final Produit__Service produitService;

    private final Produit__Repository produit__Repository;

    private final Catalogue_Repository catalogue_Repository;

    public CocoController(Produit__Service produitService,
                          Produit__Repository produit__Repository,
                          Catalogue_Repository catalogue_Repository) {
        this.produitService = produitService;
        this.produit__Repository = produit__Repository;
        this.catalogue_Repository = catalogue_Repository;
    }

    @GetMapping("")

    public List<Catalogue> getAllCatalogues() {
        // Récupérer tous les catalogues via votre service ou repository
        List<Catalogue> catalogues = catalogueService.getAllCatalogues();

        // Renvoyer la liste des catalogues récupérés
        return catalogues;
    }

    @GetMapping("/{id}")
    public Optional<Catalogue> getCatalogueById(@PathVariable Integer id) {
        return catalogueService.getCatalogueById(id);
    }

    @PostMapping("")
    public Catalogue addCatalogue(@RequestBody Catalogue catalogue) {
        return catalogueService.addCatalogue(catalogue);
    }

    @PutMapping("")
    public Catalogue updateCatalogue(@RequestBody Catalogue catalogue) {
        return catalogueService.updateCatalogue(catalogue);
    }

    @DeleteMapping("/{id}")
    public void deleteCatalogueById(@PathVariable Integer id) {
        catalogueService.deleteCatalogueById(id);
    }

    @DeleteMapping("")
    public void deleteAllCatalogues() {
        catalogueService.deleteAllCatalogues();
    }

    //@Scheduled(cron = "*/50 * * * * *")
    @DeleteMapping("/latestCatalogue")
    public void deleteLatestCatalogues() {
        int lastCatalogue ;
        int firstCatalogue;
        firstCatalogue= catalogue_Repository.findFirstCatalogueId();
        lastCatalogue= catalogue_Repository.findLastCatalogueId();


        for (int i = firstCatalogue; i < lastCatalogue-2; i++) {
            catalogueService.deleteCatalogueById(i);
        }


    }



    @PostMapping("/{catalogueId}/produits/{produitId}")
    public void addProduitToCatalogue(@PathVariable Integer catalogueId, @PathVariable Integer produitId) {
        //senderService.sendSimpleEmail("hamza.amdouni@esprit.tn", "top 50 created","hala");
        catalogueService.addProduitToCatalogue(catalogueId, produitId);
    }

    @DeleteMapping("/{catalogueId}/produits/{produitId}")
    public void removeProduitFromCatalogue(@PathVariable Integer catalogueId, @PathVariable Integer produitId) {
        catalogueService.removeProduitFromCatalogue(catalogueId, produitId);
    }

    //@Scheduled(cron = "*/10 * * * * *")
    @PostMapping("/top50catalogue")
    public ResponseEntity<Catalogue> createTop50Catalogue() {
        Catalogue catalogue = catalogueService.createTop50Catalogue();
        return ResponseEntity.ok(catalogue);
    }

    //@Scheduled(cron = "*/20 * * * * *")
    @PostMapping("/top-rated-products")
    public ResponseEntity<Catalogue> createTopRatedProductsCatalogue() {
        Catalogue catalogue = catalogueService.createTopRatedProductsCatalogue();
        return ResponseEntity.ok(catalogue);
    }


    //@Scheduled(cron = "*/30 * * * * *")
    @PostMapping("/latest-products")
    public ResponseEntity<Catalogue> createLatestProductsCatalogue() {
        Catalogue catalogue = catalogueService.createLatestProductsCatalogue();
        return ResponseEntity.ok(catalogue);
    }

    @PostMapping("/promo")
    public ResponseEntity<?> createPromoCatalogue() {
        Catalogue catalogue = catalogueService.createPromoCatalogue();
        return ResponseEntity.ok(catalogue);
    }


    @GetMapping("/catalogue/{catalogueId}/produits")
    public Set<Produit> getProduitsByCatalogueId(@PathVariable Long catalogueId) {
        Optional<Catalogue> catalogue = catalogueService.getCatalogueById(catalogueId.intValue());

        if (catalogue.isPresent()) {
            return catalogue.get().getProduits();
        } else {
            throw new ResourceNotFoundException("Catalogue with id " + catalogueId + " not found.");
        }

    }

    @GetMapping("/{id}/produits")
    public List<Produit> getProduitsByCatalogueIdAndFilter(@PathVariable Integer id, @RequestParam String filter) {
        return catalogueService.getProduitsByFilter(id, filter);
    }


    @GetMapping("/{catalogueId}/produits")
    public ResponseEntity<List<Produit>> getProduitsByFilter(@PathVariable Integer catalogueId,
                                                             @RequestParam(required = false) String filter) {
        List<Produit> produits = catalogueService.getProduitsByFilter2(catalogueId, filter);

        return ResponseEntity.ok(produits);
    }


}





















