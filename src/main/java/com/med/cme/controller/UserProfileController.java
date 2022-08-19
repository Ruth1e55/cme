package com.med.cme.controller;

import com.med.cme.model.Customer;
import com.med.cme.service.CustomerService;
import com.med.cme.service.DefaultCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DefaultCustomerService defaultCustomerService;

    @GetMapping(value = "/api/users/user/{id}",produces = "application/json")
    public Customer getUserDetail(@PathVariable Long id){
        return customerService.findById(id);
    }

    @PostMapping(value = "/register", produces = "application/json")
    public Customer createNewUser(Customer customer, BindingResult bindingResult) {

//        Customer customerExists = defaultCustomerService.findCustomerByEmail(customer.getEmail());

//        if (customerExists != null) {
//            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
//        }
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Invalid user data");
        }else{
//            System.out.println("Customer saved successfully");
//
//            System.out.println(" ********************************************* ");
//            System.out.println("CustomerService.saveCustomer() customer =" + customer.getEmail());
//            System.out.println(" ********************************************* ");

            defaultCustomerService.saveCustomer(customer);

        }
        return customerService.findById(customer.getId());
    }
}
