package com.example.enchere.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.exeption.RessourceException;
import com.example.enchere.modele.ImageEnchere;
import com.example.enchere.modele.V_Enchere;
import com.example.enchere.repository.ImageEnchereRepository;
import com.example.enchere.repository.V_EnchereRepository;
import com.example.enchere.retour.ErrorRetour;

@CrossOrigin("*")
@RestController
@RequestMapping("/detailenchere")
public class DetailEnchereController {

    @Autowired
    private V_EnchereRepository v_enchereRepository;

    @Autowired
    private ImageEnchereRepository imageEnchereRepository;
    
    @GetMapping("{idenchere}")
    public @ResponseBody Map<String, Object> getDetailEnchereById(@PathVariable int idenchere){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            V_Enchere enchere = v_enchereRepository.getEnchere(idenchere);
            List <ImageEnchere> images = imageEnchereRepository.getAll(idenchere);
            data.put("enchere", enchere);
            data.put("images",images);
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations", HttpStatus.BAD_REQUEST.value()));
        }
    }
    
}
