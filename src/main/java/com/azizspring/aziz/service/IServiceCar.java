package com.azizspring.aziz.service;

import com.azizspring.aziz.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceCar {
    public void saveCar(Car c, MultipartFile mf) throws IOException;
    public List<Car> getAllCars();
    public Page<Car> getCarsByMC(String mc, Pageable c);
    public List<Car> getCarsBCat(Long idCat);
    public void deleteCar(Long id);
    public Car getCar(Long id);
    public byte[] getImage(Long id) throws IOException;
}
