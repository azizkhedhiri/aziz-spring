package com.azizspring.aziz.service;

import com.azizspring.aziz.dao.CarRepository;
import com.azizspring.aziz.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceCar implements IServiceCar {
    private CarRepository carRepository;
    @Override
    public void saveCar(Car c,MultipartFile mf) throws IOException {
        if(!mf.isEmpty())
        {
            String image=saveImage(mf);
            c.setPhoto(image);
        }
        carRepository.save(c);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Page<Car> getCarsByMC(String mc, Pageable c) {
        return carRepository.findByNomContains(mc,c);
    }

    @Override
    public List<Car> getCarsBCat(Long idCat) {
        return carRepository.getCarsByCat(idCat);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car getCar(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public byte[] getImage(Long id) throws IOException {
        File f=new ClassPathResource("static/photos").getFile();
        String chemin=f.getAbsolutePath();
        Path p= Paths.get(chemin,getCar(id).getPhoto());
        return Files.readAllBytes(p);
    }


    private String saveImage(MultipartFile mf) throws IOException {
        String nomphoto=mf.getOriginalFilename();
        String tab[]=nomphoto.split("\\.");
        String newName=tab[0]+"."+tab[1];
        File f=new ClassPathResource("static/photos").getFile();
        String chemin=f.getAbsolutePath();
        Path p= Paths.get(chemin,newName);
        Files.write(p,mf.getBytes());
        return newName;
    }

}
