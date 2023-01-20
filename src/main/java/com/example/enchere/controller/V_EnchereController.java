package com.example.enchere.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.exeption.RessourceException;
import com.example.enchere.repository.V_EnchereRepository;
import com.example.enchere.retour.ErrorRetour;

@CrossOrigin("*")
@RestController
@RequestMapping("/v_enchere")
public class V_EnchereController {
    
    @Autowired
    private V_EnchereRepository enchereRepository;

    @GetMapping("/listeEncheres")
    public @ResponseBody Map<String, Object> getAllEnchere(){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", enchereRepository.findAll());
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations",HttpStatus.BAD_REQUEST.value()));
        }
    }
}
