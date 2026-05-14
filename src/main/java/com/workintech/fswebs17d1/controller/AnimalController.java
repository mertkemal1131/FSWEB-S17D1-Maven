package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech")
public class AnimalController {

    // application.properties'den @Value ile çekilen değerler
    @Value("${server.port}")
    private String serverPort;

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerFullname;

    // In-memory Map: <Integer id, Animal>
    private Map<Integer, Animal> animals = new HashMap<>();

    // Getter — test tarafından çağrılabilmesi için gerekli
    public String getServerPort() {
        return serverPort;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDeveloperFullname() {
        return developerFullname;
    }

    // -------------------------------------------------------
    // [GET] /workintech/animal
    // Tüm animals map'inin value değerlerini List olarak döner
    // -------------------------------------------------------
    @GetMapping("/animal")
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    // -------------------------------------------------------
    // [GET] /workintech/animal/{id}
    // İlgili id'deki animal map'te varsa value değerini döner
    // -------------------------------------------------------
    @GetMapping("/animal/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animals.get(id);
    }

    // -------------------------------------------------------
    // [POST] /workintech/animal
    // integer id ve String name alır, animals map'ine ekler
    // -------------------------------------------------------
    @PostMapping("/animal")
    public Animal addAnimal(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return animal;
    }

    // -------------------------------------------------------
    // [PUT] /workintech/animal/{id}
    // İlgili id'deki map değerini Request Body'deki id ile günceller
    // -------------------------------------------------------
    @PutMapping("/animal/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal) {
        // Eski kaydı sil, yeni id ile ekle
        animals.remove(id);
        animals.put(animal.getId(), animal);
        return animal;
    }

    // -------------------------------------------------------
    // [DELETE] /workintech/animal/{id}
    // İlgili id değerini map'ten siler
    // -------------------------------------------------------
    @DeleteMapping("/animal/{id}")
    public Animal deleteAnimal(@PathVariable int id) {
        return animals.remove(id);
    }

    // -------------------------------------------------------
    // Opsiyonel: @Value değerlerini doğrulamak için bilgi endpoint'i
    // -------------------------------------------------------
    @GetMapping("/info")
    public String getInfo() {
        return "Course: " + courseName + " | Developer: " + developerFullname;
    }
}
