package com.azizspring.aziz.dao;

import com.azizspring.aziz.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository  extends JpaRepository<Categorie,Long>{
}
