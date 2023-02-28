package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Shop;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface IShop {
    List<Shop> AfficherLesShop() ;
    void supprimerShop(int id);
 Shop RetriveByid ( int id ) ;

    Shop AddNewshop (Shop shp  ) ;

}
