package com.adoption.backend.controller;

import com.adoption.backend.model.Admin;
import com.adoption.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/all-admins")
    public List<Admin> getAllAdmin()
    {
        return adminService.getAllAdmins();
    }

    @RequestMapping(value="/admins/{id}")
    public Admin getAdmin(@PathVariable int id)
    {
        return adminService.getAdmin(id);
    }

    @RequestMapping(value="/add-admin", method= RequestMethod.POST)
    public Admin addAdmin(@RequestBody Admin admin)
    {
        return adminService.addAdmin(admin);
    }

    @RequestMapping(value="/update-admin", method=RequestMethod.PUT)
    public Admin updateAdmin(@RequestBody Admin admin)
    {
        return adminService.updateAdmin(admin);
    }
    @RequestMapping(value="/admins/{id}", method=RequestMethod.DELETE)
    public void deleteAdmin(@PathVariable int id)
    {
        adminService.deleteAdmin(id);
    }
}
