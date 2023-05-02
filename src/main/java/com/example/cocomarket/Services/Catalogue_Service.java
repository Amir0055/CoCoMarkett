package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Catalogue;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Interfaces.ICatalogue;
import com.example.cocomarket.Repository.Catalogue_Repository;
import com.example.cocomarket.Repository.Produit__Repository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Catalogue_Service implements ICatalogue {

    @Autowired
    private Catalogue_Repository catalogueRepository;
    @Autowired

    private Produit__Repository produitRepository;





    public List<Catalogue> getAllCatalogues() {
        return catalogueRepository.findAll();
    }

    public Optional<Catalogue> getCatalogueById(Integer id) {
        return catalogueRepository.findById(id);
    }

    public Catalogue addCatalogue(Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }

    public Catalogue updateCatalogue(Catalogue catalogue) {
        return catalogueRepository.save(catalogue);
    }

    public void deleteCatalogueById(Integer id) {
        catalogueRepository.deleteById(id);
    }

    public void deleteAllCatalogues() {
        catalogueRepository.deleteAll();
    }

    public void addProduitToCatalogue(Integer catalogueId, Integer produitId) {
        Optional<Catalogue> optionalCatalogue = catalogueRepository.findById(catalogueId);
        Optional<Produit> optionalProduit = produitRepository.findById(produitId);
        if (optionalCatalogue.isPresent() && optionalProduit.isPresent()) {
            Catalogue catalogue = optionalCatalogue.get();
            Produit produit = optionalProduit.get();
            catalogue.getProduits().add(produit);
            produit.getCatalogues().add(catalogue);
            catalogueRepository.save(catalogue);
            produitRepository.save(produit);
        }
    }

    public void removeProduitFromCatalogue(Integer catalogueId, Integer produitId) {
        Optional<Catalogue> optionalCatalogue = catalogueRepository.findById(catalogueId);
        Optional<Produit> optionalProduit = produitRepository.findById(produitId);
        if (optionalCatalogue.isPresent() && optionalProduit.isPresent()) {
            Catalogue catalogue = optionalCatalogue.get();
            Produit produit = optionalProduit.get();
            catalogue.getProduits().remove(produit);
            produit.getCatalogues().remove(catalogue);
            catalogueRepository.save(catalogue);
            produitRepository.save(produit);
        }
    }


    public Catalogue createTop50Catalogue() {
        List<Produit> top50Produits = produitRepository.findTop50ByOrderByQuantiteVendueDesc();
        Catalogue catalogue = new Catalogue();
        catalogue.setNom("Top 50 Produits");
        catalogue.setDescription("Les 50 produits les plus vendus");

        Set<Produit> produits = new HashSet<>(top50Produits);
        catalogue.setProduits(produits);

        return catalogueRepository.save(catalogue);
    }

    public Catalogue createTopRatedProductsCatalogue() {
        // Récupérer les 5 produits les mieux notés

        List<Produit> topRatedProducts = produitRepository.findAll()
                .stream()
                .filter(p -> p.getRaiting_prod() != null && !p.getRaiting_prod().isEmpty())
                .sorted(Comparator.comparing(p -> -getAverageScore(p)))
                .limit(10)
                .collect(Collectors.toList());


        // Créer le catalogue contenant les 5 produits les mieux notés
        Catalogue topRatedProductsCatalogue = new Catalogue();
        topRatedProductsCatalogue.setNom("Top Rated Products");
        topRatedProductsCatalogue.setDescription("Catalogue contenant les 5 produits les plus notés.");
        topRatedProductsCatalogue.setImg("https://example.com/top-rated-products.png");
        topRatedProductsCatalogue.setProduits(new HashSet<>(topRatedProducts));

        // Enregistrer le catalogue dans la base de données
        return catalogueRepository.save(topRatedProductsCatalogue);
    }

    private Double getAverageScore(Produit produit) {
        return produit.getRaiting_prod().stream()
                .mapToInt(r -> r.getScore())
                .average()
                .orElse(0.0);
    }

    public Catalogue createLatestProductsCatalogue() {
        int last = catalogueRepository.findLastCatalogueId();
        int first = catalogueRepository.findFirstCatalogueId();
        Catalogue catalogue = new Catalogue();
        catalogue.setNom("Dernières nouveautés");
        catalogue.setDescription("Les derniers produits ajoutés au site "+ last + " "+ first );
        catalogue.setImg("file:///C:\\Users\\hamza\\Desktop\\New folder (2)\\FrontCoCo\\src\\assets\\FRONT\\images/visa.png");
        List<Produit> pList=produitRepository.findAll();
        //System.out.println("Status  :"+pList[0]);
        List<Produit> latestProducts = produitRepository.findTop10ByOrderByDatePublicationDesc();
        catalogue.setProduits(new HashSet<>(latestProducts));

        return catalogueRepository.save(catalogue);
    }

    public Catalogue createPromoCatalogue() {
        Catalogue catalogue = new Catalogue();
        catalogue.setNom("PromotionCatalogue");
        catalogue.setDescription("Les produits en promo");
        catalogue.setImg("https://example.com/images/latest-products.jpg");

        List<Produit> promoProducts = produitRepository.findByPourcentagePromotionGreaterThan(0);

        catalogue.setProduits(new HashSet<>(promoProducts));

        return catalogueRepository.save(catalogue);
    }

    /*public Catalogue createPromoCatalogue(String nom, String description, String img, Integer pourcentagePromotion) {

        Catalogue catalogue = new Catalogue();
        catalogue.setNom(nom);
        catalogue.setDescription(description);
        catalogue.setImg(img);

        //Récupérer tous les produits qui ont un pourcentage de promotion non nul
        List<Produit> produitsEnPromotion = produitRepository.findByPourcentagePromotionGreaterThan(0);

        //Associer les produits au catalogue
        for (Produit produit : produitsEnPromotion) {
            catalogue.getProduits().add(produit);
        }

        //Calculer le prix de promotion pour chaque produit
        for (Produit produit : catalogue.getProduits()) {
            Float nouveauPrix = produit.getPrix() - (produit.getPrix() * (pourcentagePromotion / 100f));
            produit.setPrixPromotion(nouveauPrix);
        }


        return catalogueRepository.save(catalogue);
    }
*/
    public List<Produit> getProduitsByFilter1(Integer catalogueId, String filter) {
        Optional<Catalogue> catalogue = catalogueRepository.findById(catalogueId);

        if (catalogue.isPresent()) {
            List<Produit> produits = (List<Produit>) catalogue.get().getProduits();
            List<Produit> filteredProduits = new ArrayList<>();

            // Appliquer le filtre sur les produits
            for (Produit produit : produits) {
                if (produit.getNom().toLowerCase().contains(filter.toLowerCase())) {
                    filteredProduits.add(produit);
                }
            }

            return filteredProduits;
        } else {
            throw new ResourceNotFoundException("Catalogue with id " + catalogueId + " not found.");
        }
    }

    public List<Produit> getProduitsByFilter(Integer catalogueId, String filter) {
        Catalogue catalogue = catalogueRepository.findById(catalogueId)
                .orElseThrow(() -> new ResourceNotFoundException("Catalogue with id " + catalogueId + " not found."));

        return catalogue.getProduits().stream()
                .filter(produit -> produit.getNom().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Produit> getProduitsByFilter2(int catalogueId, String filter) {
        Catalogue catalogue = catalogueRepository.findById(catalogueId).get();
        if(catalogue == null) {
            throw new EntityNotFoundException("Catalogue with id " + catalogueId + " not found");
        }
        List<Produit> produits = new ArrayList<>(catalogue.getProduits());
        List<Produit> filteredProduits = new ArrayList<>();
        for (Produit produit : produits) {
            // Vérifie si le produit contient le filtre dans son nom ou sa description
            if (produit.getNom().toLowerCase().contains(filter.toLowerCase()) ||
                    produit.getDescription().toLowerCase().contains(filter.toLowerCase())) {
                filteredProduits.add(produit);
            }
            // Vérifie si le produit contient le filtre dans une de ses caractéristiques
            else {
                boolean containsFilter = false;

                if (produit.getShopes() != null && produit.getShopes().getNom().toLowerCase().contains(filter.toLowerCase())) {
                    containsFilter = true;
                }
                if (produit.getPrix() != null && produit.getPrix().toString().toLowerCase().contains(filter.toLowerCase())) {
                    containsFilter = true;
                }
                if (produit.getQuantiteVendue() != null && produit.getQuantiteVendue().toString().toLowerCase().contains(filter.toLowerCase())) {
                    containsFilter = true;
                }
                if (produit.getPourcentagePromotion() != null && produit.getPourcentagePromotion().toString().toLowerCase().contains(filter.toLowerCase())) {
                    containsFilter = true;
                }

                if (containsFilter) {
                    filteredProduits.add(produit);
                }
            }
        }
        return filteredProduits;
    }
















}


