package fr.simplex_software.codeshift.hackathon.orm;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;

@Cacheable
@Entity
@Table(name = "BANK_ADDRESSES")
public class BankAddressEntity
{
  @NotNull
  private Long id;
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
  @Valid
  private BankEntity bankEntity;

  public BankAddressEntity()
  {
  }

  public BankAddressEntity(String streetName, String streetNumber, String poBox, String cityName, String zipCode, String countryName, BankEntity bankEntity)
  {
    this.streetName = streetName;
    this.streetNumber = streetNumber;
    this.poBox = poBox;
    this.cityName = cityName;
    this.zipCode = zipCode;
    this.countryName = countryName;
    this.bankEntity = bankEntity;
  }

  @Id
  @SequenceGenerator(name = "bankAddressSequence", sequenceName = "bankAddressId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankAddressSequence")
  @Column(name = "BANK_ADDRESS_ID", nullable = false, updatable = false)
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @Column(name = "STREET_NAME", length = 30)
  public String getStreetName()
  {
    return streetName;
  }

  public void setStreetName(String streetName)
  {
    this.streetName = streetName;
  }

  @Column(name = "STREET_NUMBER", length = 5)
  public String getStreetNumber()
  {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber)
  {
    this.streetNumber = streetNumber;
  }

  @Column(name = "PO_BOX", length = 10)
  public String getPoBox()
  {
    return poBox;
  }

  public void setPoBox(String poBox)
  {
    this.poBox = poBox;
  }

  @Column(name = "CITY_NAME", length = 20)
  public String getCityName()
  {
    return cityName;
  }

  public void setCityName(String cityName)
  {
    this.cityName = cityName;
  }

  @Column(name = "ZIP_CODE", length = 5)
  public String getZipCode()
  {
    return zipCode;
  }

  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
  }

  @Column(name = "COUNTRY_NAME", length = 20)
  public String getCountryName()
  {
    return countryName;
  }

  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="BANK_ID")
  public BankEntity getBankEntity()
  {
    return bankEntity;
  }

  public void setBankEntity (@Valid BankEntity bankEntity)
  {
    this.bankEntity = bankEntity;
  }
}
