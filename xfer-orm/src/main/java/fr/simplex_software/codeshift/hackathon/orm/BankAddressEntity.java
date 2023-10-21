package fr.simplex_software.codeshift.hackathon.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Embeddable
public class BankAddressEntity
{
  @NotBlank
  @Size(max = 30)
  private String streetName;
  @NotBlank
  @Size(max = 5)
  private String streetNumber;
  @NotBlank
  @Size(max = 10)
  private String poBox;
  @NotBlank
  @Size(max = 20)
  private String cityName;
  @NotBlank
  @Size(max = 5)
  private String zipCode;
  @NotBlank
  @Size(max = 20)
  private String countryName;

  public BankAddressEntity()
  {
  }

  public BankAddressEntity(String streetName, String streetNumber, String poBox, String cityName, String zipCode, String countryName)
  {
    this.streetName = streetName;
    this.streetNumber = streetNumber;
    this.poBox = poBox;
    this.cityName = cityName;
    this.zipCode = zipCode;
    this.countryName = countryName;
  }

  public String getStreetName()
  {
    return streetName;
  }

  public void setStreetName(String streetName)
  {
    this.streetName = streetName;
  }

  public String getStreetNumber()
  {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber)
  {
    this.streetNumber = streetNumber;
  }

  public String getPoBox()
  {
    return poBox;
  }

  public void setPoBox(String poBox)
  {
    this.poBox = poBox;
  }

  public String getCityName()
  {
    return cityName;
  }

  public void setCityName(String cityName)
  {
    this.cityName = cityName;
  }

  public String getZipCode()
  {
    return zipCode;
  }

  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
  }

  public String getCountryName()
  {
    return countryName;
  }

  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
}
