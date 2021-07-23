package com.springdemo.controller;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@EnableAspectJAutoProxy
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @RequestMapping("/list")
    public String listCustomers(Model theModel) {
        //get customers
        List<Customer> customerList = customerService.getCustomers();

        theModel.addAttribute("customers", customerList);


        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service
        customerService.saveCustomer(theCustomer);


        return "redirect:/customer/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id,
                                    Model model) {
        Customer customer = customerService.getCustomer(id);

        model.addAttribute("customer", customer);

        return "customer-form";


    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id) {

        customerService.delete(id);

        return "redirect:/customer/list";


    }
}
