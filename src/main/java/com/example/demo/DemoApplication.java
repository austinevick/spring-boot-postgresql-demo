package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class DemoApplication {
	private final CustomerRepository customerRepository;

	public DemoApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
    @GetMapping
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}
	record NewCustomerRequest(
			String name,
			String email,
			Integer age
	){}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName(request.name);
		customer.setAge(request.age);
		customer.setEmail(request.email);
		customerRepository.save(customer);
	}
	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
	}
	@GetMapping("{customerId}")
	public void getCustomerById(@PathVariable("{customerId}") Integer id){
		customerRepository.findById(id);
	}




}
