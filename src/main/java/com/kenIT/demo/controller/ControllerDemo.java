package com.kenIT.demo.controller;

import com.kenIT.demo.model.Customer;
import com.kenIT.demo.service.CustomerService;
import com.kenIT.demo.service.CustomerServiceIpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerDemo {
    private CustomerService customerService = new CustomerServiceIpl();

    @GetMapping("/")
    public String index(Model model) {
        List<Customer> customerList = customerService.findALl();
        model.addAttribute("customers", customerList);
        return "/index";
    }

    @GetMapping("/customer/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/customer/save")
    public String save(Customer customer) {
        customer.setId((int) (Math.random() * 100));
        customerService.save(customer);

        return "redirect:/";
    }

    @GetMapping("/customer/{id}/edit")
    private String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "edit";
    }

    @PostMapping("/customer/update")
    private String update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/";
    }

    @PostMapping("/customer/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/view")
    private String view(@PathVariable int id,Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "/view";
    }

    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/delete";
    }
}