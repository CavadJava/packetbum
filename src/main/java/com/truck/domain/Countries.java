package com.truck.domain;

public class Countries
{
	private int id;
	private String country_code;
	private String country_name;

	public String getCountry_code()
	{
		return country_code;
	}

	public void setCountry_code(String country_code)
	{
		this.country_code = country_code;
	}

	public String getCountry_name()
	{
		return country_name;
	}

	public void setCountry_name(String country_name)
	{
		this.country_name = country_name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
