package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Shop;
import com.example.cocomarket.Interfaces.IShop;
import com.example.cocomarket.Repository.Shop_Repository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class Shop_Service implements IShop {
    @Autowired
Shop_Repository Shoprepo ;


    @Override
    public List<Shop> AfficherLesShop() {
        return
        Shoprepo.findAll();
    }

    @Override
    public void supprimerShop(int id) {
        Shoprepo.deleteById(id);
    }

    @Override
    public Shop RetriveByid(int id) {
        return Shoprepo.findById(id).orElse(null);
    }

    @Override
    public Shop AddNewshop(Shop shp ) {
/*
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 250, 250);
        MatrixToImageWriter.toBufferedImage(bitMatrix);
        shp.setUrl(url);

int width ;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter(url, BarcodeFormat.QR_CODE, 250, 250);
        ByteArrayOutputStream pngOutputStream =
                new ByteArrayOutputStream();
        MatrixToImageConfig con =
                new MatrixToImageConfig(0xFF000002, 0xFF04B4AE);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        byte[] pngData = pngOutputStream.toByteArray();

        return pngData;
        */
        return Shoprepo.save(shp);
    }

}
