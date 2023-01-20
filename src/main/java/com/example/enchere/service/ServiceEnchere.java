package com.example.enchere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.enchere.modele.Enchere;
import com.example.enchere.modele.EnchereVendu;
import com.example.enchere.modele.Notifications;
import com.example.enchere.modele.Offre;
import com.example.enchere.repository.EnchereRepository;
import com.example.enchere.repository.EnchereVenduRepository;
import com.example.enchere.repository.NotificationsRepository;
import com.example.enchere.repository.OffreRepository;

@Service
public class ServiceEnchere {
    
    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private EnchereVenduRepository enchereVenduRepository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private OffreRepository offreRepository;
    
    @Scheduled(fixedRate = 1000)
    public void finEnchere(){
        List <Enchere> liste = enchereRepository.getAllNonTermine();
        for( Enchere enchere : liste ){
            if( enchere.isTerminated() == true ){
                String n = "Votre enchere dont le nom est "+enchere.getNom()+" est terminee";
                Notifications notif = new Notifications(0, enchere.getIdUtilisateur(), n, 0);
                notificationsRepository.save(notif);
                Offre max = offreRepository.getOffreMax(enchere.getIdEnchere());
                EnchereVendu ev = new EnchereVendu(max.getIdEnchere(), max.getIdOffre());
                enchereVenduRepository.save(ev);
            }
        }
    }
}
