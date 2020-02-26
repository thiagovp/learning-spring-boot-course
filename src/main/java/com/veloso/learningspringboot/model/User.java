package com.veloso.learningspringboot.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private final UUID userUid;

	@NotNull
	private final String firstName;

	@NotNull
	private final String lastName;

	@NotNull
	private final Gender gender;

	@NotNull
	@Max(value = 112)
	@Min(value = 0)
	private final Integer age;

	@NotNull
	@Email
	private final String email;

	public User(@JsonProperty("userUid") UUID userUid, @JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName, @JsonProperty("gender") Gender gender,
			@JsonProperty("age") Integer age, @JsonProperty("email") String email) {
		super();
		this.userUid = userUid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public int getDateOfBirth() {
		return LocalDate.now().minusYears(age).getYear();
	}

//	@JsonProperty("id")
	public UUID getUserUid() {
		return userUid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public static User newUser(UUID userUid, User user) {
		return new User(userUid, user.getFirstName(), user.getLastName(), user.getGender(), user.getAge(),
				user.getEmail());
	}

	@Override
	public String toString() {
		return "User [userUid=" + userUid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + ", email=" + email + "]";
	}

	public enum Gender {
		MALE, FEMALE;
	}

}
