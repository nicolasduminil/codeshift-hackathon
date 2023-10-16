package fr.simplex_software.codeshift.hackathon.orm;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

@Cacheable
@Entity
@Table(name = "BANK_ADDRESSES")
public class BankAddressEntity extends BankAddress
{
  @NotNull
  private Long id;
  @Valid
  private BankEntity bankEntity;

  public BankAddressEntity()
  {
    super();
  }

  public BankAddressEntity(@NotBlank String streetName, @NotBlank String streetNumber, @NotBlank String poBox, @NotBlank String cityName, @NotBlank String zipCode, @NotBlank String countryName)
  {
    super(streetName, streetNumber, poBox, cityName, zipCode, countryName);
  }

  @Id
  @SequenceGenerator(name = "bankAddressSequence", sequenceName = "bankAddressId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankAddressSequence")
  @Column(name = "ID", nullable = false, updatable = false)
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @Override
  @Column(name = "STREET_NAME", nullable = false)
  public String getStreetName()
  {
    return super.getStreetName();
  }

  @Override
  public void setStreetName(String value)
  {
    super.setStreetName(value);
  }

  @Override
  @Column(name = "STREET_NUMBER", nullable = false)
  public String getStreetNumber()
  {
    return super.getStreetNumber();
  }

  @Override
  public void setStreetNumber(String value)
  {
    super.setStreetNumber(value);
  }

  @Override
  @Column(name = "PO_BOX", nullable = false, updatable = false)
  public String getPoBox()
  {
    return super.getPoBox();
  }

  @Override
  public void setPoBox(String value)
  {
    super.setPoBox(value);
  }

  @Override
  @Column(name = "CITY_NAME", nullable = false)
  public String getCityName()
  {
    return super.getCityName();
  }

  @Override
  public void setCityName(String value)
  {
    super.setCityName(value);
  }

  @Override
  @Column(name = "ZIP_CODE", nullable = false)
  public String getZipCode()
  {
    return super.getZipCode();
  }

  @Override
  public void setZipCode(String value)
  {
    super.setZipCode(value);
  }

  @Override
  @Column(name = "COUNTRY_NAME", nullable = false)
  public String getCountryName()
  {
    return super.getCountryName();
  }

  @Override
  public void setCountryName(String value)
  {
    super.setCountryName(value);
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public BankEntity getBankEntity()
  {
    return bankEntity;
  }

  public void setBankEntity (@Valid BankEntity bankEntity)
  {
    this.bankEntity = bankEntity;
  }
}
