package com.example.inventoryapp.controller;

import com.example.inventoryapp.entity.Inventory;
import com.example.inventoryapp.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class InventoryController {
    @Autowired
    private InventoryRepo inventoryRepo;

    @GetMapping({"/showInventory","/","/list"})
    private ModelAndView showInventory(){
        ModelAndView mav = new ModelAndView("list-inventory");
        List<Inventory> list =inventoryRepo.findAll();
        mav.addObject("inventory",list);
        return mav;
    }

    @GetMapping("/addInventoryForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-inventory-form");
        Inventory inventory = new Inventory();
        mav.addObject("inventory",inventory);
        return mav;
    }

    @PostMapping("/saveInventory")
    public String saveInventory(@ModelAttribute Inventory inventory){
         inventoryRepo.save(inventory);
         return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long inventoryId) {
        ModelAndView mav = new ModelAndView("add-inventory-form");
        Inventory inventory = inventoryRepo.findById(inventoryId).get();
        mav.addObject("inventory", inventory);
        return mav;
    }

    @GetMapping("/deleteInventory")
    public String deleteEmployee(@RequestParam Long inventoryId) {
        inventoryRepo.deleteById(inventoryId);
        return "redirect:/list";
    }



}
