package com.med.cme.service;

import com.med.cme.model.Customer;
import com.med.cme.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("customerService")
public class DefaultCustomerService implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer(Customer customer){
        customer.setEmail(customer.getEmail());
        customer.setUserName(customer.getUserName());
        customer.setPhone(customer.getPhone());
        customer.setPassword(customer.getPassword());
        customer.setToken(UUID.randomUUID().toString());
        customerRepository.save(customer);
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    @Override
    public String login(String username, String password) {
        Optional<Customer> customer = customerRepository.login(username,password);
        if(customer.isPresent()){
            String token = UUID.randomUUID().toString();
            Customer custom= customer.get();
            custom.setToken(token);
            customerRepository.save(custom);
            return token;
        }
        return StringUtils.EMPTY;
    }

    @Override
    public Optional<User> findByToken(String token) {
        Optional<Customer> customer= customerRepository.findByToken(token);
        if(customer.isPresent()){
            Customer customer1 = customer.get();
            User user= new User(customer1.getUserName(), customer1.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer= customerRepository.findByEmail(email);
        if(customer!=null){
            User user= new User(customer.getUserName(), customer.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return user;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer= customerRepository.findById(id);
        return customer.orElse(null);
    }

}
