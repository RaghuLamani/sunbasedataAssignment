package org.sunbasedata.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunbasedata.dto.Customer;
import org.sunbasedata.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("/customers")
	public String createNewCustomer(@RequestBody Customer customer)
	{
		customerRepository.save(customer);
		return "Customer table created in database";
	}
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		List<Customer> list=new ArrayList<>();
		customerRepository.findAll().forEach(list::add);
		return new ResponseEntity<List<Customer>>(list,HttpStatus.OK);
	}
	@DeleteMapping("/customers/{id}")
	public String deleteCustomerById(@PathVariable int id)
	{
		customerRepository.deleteById(id);
		
		   boolean deleted = true;

	        if (deleted) {

		return "Successfully deleted";
	        }else
	        {
			return "Error Not deleted";
	        }
	}
	
	@GetMapping("/customers/{id}")
	public String updateCustomerById(@PathVariable int id,@RequestBody Customer customer)
	{
		Optional<Customer> custOptional=customerRepository.findById(id);
		if(custOptional.isPresent())
		{
			Customer existCustomer=custOptional.get();
			existCustomer.setFirstName(customer.getFirstName());
			existCustomer.setLastName(customer.getLastName());
			existCustomer.setStreet(customer.getStreet());
			existCustomer.setAddress(customer.getAddress());
			existCustomer.setCity(customer.getCity());
			existCustomer.setState(customer.getState());
			existCustomer.setEmail(customer.getEmail());
			existCustomer.setPhone(customer.getPhone());
			customerRepository.save(existCustomer);
			boolean updated = true;

			if (updated) {

				return "Successfully updated";
			
			
		}
		}
		return "Body is empty";
}
}