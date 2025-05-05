package com.adoption.backend.repository;

import com.adoption.backend.model.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog,Integer>
{
}