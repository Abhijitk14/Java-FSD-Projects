package com.wipro.ms.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "account")
public class AccountsContactInfoDto {

	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;
}
