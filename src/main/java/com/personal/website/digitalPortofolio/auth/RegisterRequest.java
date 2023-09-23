package com.personal.website.digitalPortofolio.auth;

import lombok.Data;


public record RegisterRequest(
	String firstName,
	String lastName,
	String email,
	String password
){

}
