package com.ipiecoles.java.java240;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private ProduitManager pm;

    @Resource(name = "bitcoinServiceCacheProperty")
    private BitcoinService bitcoinServiceWithoutCache;


    public void run(String... args) throws IOException {
        // Point d'entrée de votre application en ligne de commande.


        System.out.println("Bienvenue !");
        while (true) {
            System.out.println("Vous souhaitez : ");
            System.out.println("1 - Connaître le cours du bitcoin");
            System.out.println("2 - Ajouter un produit au catalogue");
            System.out.println("3 - Voir tous les produits du catalogue");
            System.out.println("4 - Voir les détails d'un produit");
            //System.out.println("5 - Initialiser le catalogue");
            System.out.println("0 - Quitter");

            Scanner scanner = new Scanner(System.in);
            int saisie = scanner.nextInt();
            switch (saisie) {
                case 1:
                    //BitcoinService bitcoinService = new BitcoinService();
                    System.out.println("1 BTC = " + bitcoinServiceWithoutCache.getBitcoinRate() + " €");
                    break;
                case 2:
                    pm.ajouterProduit();
                    break;
                case 3:
                    pm.afficherTousLesProduits();
                    break;
                case 4:
                    System.out.println("Quel numéro de produit ?");
                    pm.afficherDetailProduit(scanner.nextInt());
                    break;
                /*case 5:
                    pm.initialiserCatalogue();
                    break;*/
                case 0:
                    System.out.println("Au revoir !");
                    return;
            }

        }
    }
}