package com.example.cocomarket.Interfaces;


import com.example.cocomarket.Entity.CART;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Produit_Cart;

import java.util.Set;

public interface ICart {
    public CART Add_Product_To_Cart(Integer idProduit, Integer idCart);

    public void Remove_Product(Integer idProduit,Integer idCart) ;

    public Produit retrive_one_Product(Integer idprodCart, Integer idProduit);

    public Set<Produit_Cart> Retrive_All_Product_in_cart(Integer idCart);

}
