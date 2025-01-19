package com.wipro.ms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customer_id;
	@NotBlank(message="Name can not be a null or empty")
	private String name;
	@NotBlank(message="Email address can not be a null or empty")
	private String email;
	@Size(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
//    @Pattern(regexp = "\\d{10}", message = "Mobile number must be numeric")
	@Column(unique = true, nullable = false)
    private String mobileNumber;
	
//	@Autowired
//	AccountRepository accountRepository;
////	private Account account;
//
//	@Override
//	public String toString() {
//		return "Customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", mobileNumber="
//				+ mobileNumber + ", account=" + accountRepository.findById(customer_id) + "]";
//	}
	
	
	
	
}
