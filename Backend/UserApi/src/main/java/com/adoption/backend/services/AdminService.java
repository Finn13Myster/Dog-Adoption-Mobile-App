package com.adoption.backend.services;

import com.adoption.backend.model.Admin;
import com.adoption.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins()
    {
        List<Admin>adminRecords = new ArrayList<>();
        adminRepository.findAll().forEach(adminRecords::add);
        return adminRecords;
    }
    public Admin addAdmin(Admin admin)
    {
        return adminRepository.save(admin);
    }
    public Admin updateAdmin(Admin admin)
    {
        return adminRepository.save(admin);
    }
    public Admin getAdmin(int id)
    {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            return admin.get();
        }
        else
            return null;
    }
    public void deleteAdmin(int id)
    {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            adminRepository.delete(admin.get());
        }
    }
}
