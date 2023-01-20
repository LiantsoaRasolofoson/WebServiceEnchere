package com.example.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.enchere.modele.Offre;

public interface OffreRepository extends JpaRepository<Offre, Integer> {

    @Query(value = "SELECT * FROM Offre WHERE idEnchere = ?1 ORDER BY prixOffre DESC LIMIT 1", nativeQuery = true)
    public Offre getOffreMax(int idEnchere);
}