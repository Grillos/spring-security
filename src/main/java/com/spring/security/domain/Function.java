package com.spring.security.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Function implements Serializable {
	
	private static final long serialVersionUID = 4585918616485798177L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "description cannot be empty")
	private String description;
	
	@NotBlank(message = "url cannot be empty")
	private String url;
	
	@NotBlank(message = "method cannot be empty")
	private String method;
	
}
