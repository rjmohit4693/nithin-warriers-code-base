package com.smartandroidians.andtest;

public class AppContacts {
	
	private String name;
	private String phoneNumber;
	private String email;
	private String photoUri;
	
	public AppContacts(String name, String phoneNumber, String email, String photoUri) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.photoUri = photoUri;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPhotoUri() {
		return photoUri;
	}

}
