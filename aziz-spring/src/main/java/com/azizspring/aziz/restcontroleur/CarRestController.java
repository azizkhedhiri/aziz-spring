package com.azizspring.aziz.restcontroleur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.azizspring.aziz.entities.Car;
import com.azizspring.aziz.service.IServiceCar;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CarRestController {
    IServiceCar serviceCar;
    @GetMapping("/all")
    public List<Car> getAllProducts(@RequestParam(name = "mc",defaultValue ="" ) String mc,
                                        @RequestParam(name="page",defaultValue = "0")int page,
                                        @RequestParam(name="size",defaultValue = "5")int size)
    {
        //List<Car> liste=serviceCar.getAllCars();
        Page<Car> liste=serviceCar.getCarsByMC(mc, PageRequest.of(page,size));
        //m.addAttribute("mc",mc);
        //m.addAttribute("cars",liste.getContent());
        //m.addAttribute("pages",new int[liste.getTotalPages()]);
        //m.addAttribute("current",page);
        // System.out.println("Yessss");

        return liste.getContent();
    }

    @DeleteMapping ("/remove/{id}")
    public void deleteCars(@PathVariable("id") Long idCar)
    {
        serviceCar.deleteCar(idCar);

    }


    @PostMapping("/save")
    public void addCar(@RequestParam("car")String car,@RequestParam("file")MultipartFile mf) throws IOException {
        Car c=new ObjectMapper().readValue(car,Car.class);
        serviceCar.saveCar(c,mf);
    }





    @GetMapping(path = "/image/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return serviceCar.getImage(id);
    }

}
