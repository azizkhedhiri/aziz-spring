package com.azizspring.aziz.controlleur;


import com.azizspring.aziz.dao.CategorieRepository;
import com.azizspring.aziz.entities.Car;
import com.azizspring.aziz.service.IServiceCar;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class CarControlleur {
    private IServiceCar serviceCar;
    private CategorieRepository categorieRepository;

    @GetMapping("/user/index")
    public String getAllCars(Model m,
                                 @RequestParam(name = "mc",defaultValue ="" ) String mc,
                                 @RequestParam(name="page",defaultValue = "0")int page,
                                 @RequestParam(name="size",defaultValue = "4")int size)
    {
        //List<Car> liste=serviceCar.getAllCars();
        Page<Car> liste=serviceCar.getCarsByMC(mc, PageRequest.of(page,size));
        m.addAttribute("mc",mc);
        m.addAttribute("cars",liste.getContent());
        m.addAttribute("currentpage",page);
        m.addAttribute("pages",new int[liste.getTotalPages()]);


        return "vue";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteCar(@PathVariable("id") Long idCar)
    {
        serviceCar.deleteCar(idCar);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formCar")
    public String formAjout(Model m)
    {m.addAttribute("categories",categorieRepository.findAll());
        m.addAttribute("car",new Car());
        return "ajouterCar";
    }

    @PostMapping("/admin/save")
    public String saveCar(@ModelAttribute Car c,@RequestParam("file") MultipartFile mf) throws IOException {
        serviceCar.saveCar(c,mf);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/update/{id}")
    public String updateCar(@PathVariable Long id,Model m)
    {
        Car c=serviceCar.getCar(id);
        m.addAttribute("car",c);
        m.addAttribute("categories",categorieRepository.findAll());
        return "ajouterCar";
    }

    @GetMapping("/")
    public String home()
    {
        return "redirect:/user/index";
    }

}
