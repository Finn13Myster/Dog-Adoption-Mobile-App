package com.adoption.backend.services;

import com.adoption.backend.model.Dog;
import com.adoption.backend.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAllDogs()
    {
        List<Dog>dogRecords = new ArrayList<>();
        dogRepository.findAll().forEach(dogRecords::add);
        return dogRecords;
    }
    public Dog addDog(Dog dog)
    {
        return dogRepository.save(dog);
    }
    public Dog updateDog(Dog dog)
    {
        return dogRepository.save(dog);
    }
    public Dog getDog(int id)
    {
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent()) {
            return dog.get();
        }
        else
            return null;
    }
    public void deleteDog(int id)
    {
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent()) {
            dogRepository.delete(dog.get());
        }
    }
}
