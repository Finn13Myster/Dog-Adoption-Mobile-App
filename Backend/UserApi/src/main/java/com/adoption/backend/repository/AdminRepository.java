package com.adoption.backend.repository;

import com.adoption.backend.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer>
{
}
