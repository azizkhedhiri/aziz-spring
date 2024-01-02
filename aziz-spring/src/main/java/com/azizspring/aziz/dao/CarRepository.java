package com.azizspring.aziz.dao;

import com.azizspring.aziz.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car,Long>{
    public Page<Car> findByNomContains(String mc, Pageable c);
    @Query("select c from Car c where c.categorie.id=:x")
    public List<Car> getCarsByCat(@Param("x") Long idC);

}

