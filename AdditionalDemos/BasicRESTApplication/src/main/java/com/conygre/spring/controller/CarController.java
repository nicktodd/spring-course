package com.conygre.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    private static Map<Integer, Car> cars;

    static {
        cars = new HashMap<>();
        cars.put(1, new Car("Peugeot", "306",1));
        cars.put(2, new Car("Renault", "Laguna", 2));
        cars.put(3, new Car("Citreon", "2CV", 3));

    }

    @RequestMapping(method= RequestMethod.GET)
    public Collection<Car> getCars() {
        return cars.values();
    }

    @RequestMapping(path="/{carid}", method=RequestMethod.GET)
    public ResponseEntity<Car> getCarById(@PathVariable("carid") int id) {
        Car carToReturn = cars.get(id);
        if (carToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(carToReturn, HttpStatus.OK);
        }
    }


    @RequestMapping(method=RequestMethod.POST, consumes = "application/json")
    public void addCar(@RequestBody Car c) {
        if (!cars.containsKey(c.getId())) {
            cars.put(c.getId(), c);
        }
    }

    @RequestMapping(method=RequestMethod.PUT, consumes = "application/json")
    public void updateCar(@RequestBody Car c) {
        if (cars.containsKey(c.getId())) {
            cars.put(c.getId(), c);
        }
    }

    @RequestMapping(path="/{carid}", method = RequestMethod.DELETE)
    public void removeCar(@PathVariable("carid") int id) {
        cars.remove(id);
    }

    @RequestMapping(path="/search", method = RequestMethod.GET)
    public Collection<Car> getCarsByMake(@RequestParam("make") String make) {
        return
                cars.values().
                        stream().
                        filter((c) -> c.getMake().equals(make)).
                        collect(Collectors.<Car>toList());
    }


}
class Car {
    private String make;
    private String model;
    private int id;

    public Car(String make, String model, int id) {
        this.make = make;
        this.model = model;
        this.id = id;
    }

    public Car(){}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}