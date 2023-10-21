package fr.simplex_software.codeshift.hackathon.orm;

import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

import java.util.*;

@Cacheable
@Entity
@Table(name = "BANKS")
public class BankEntity
{
  @NotNull
  private Long id;
  @NotEmpty
  @Size(max = 40)
  private String bankName;
  @Valid
  private List<BankAddressEntity> bankAddressEntityList = new ArrayList<>();

  public BankEntity()
  {
  }

  public BankEntity(@NotBlank String bankName, @Valid List<BankAddressEntity> bankAddressEntityList)
  {
    this.bankName = bankName;
    this.bankAddressEntityList.clear();
    this.bankAddressEntityList.addAll(bankAddressEntityList);
  }

  @Id
  @SequenceGenerator(name = "bankSequence", sequenceName = "bankId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSequence")
  @Column(name = "BANK_ID", unique = true, nullable = false, updatable = false, length = 8)
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @Column(name = "BANK_NAME", nullable = false, length = 40)
  public String getBankName()
  {
    return bankName;
  }

  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }

  @ElementCollection
  @CollectionTable (name = "BANK_ADDRESSES", joinColumns=@JoinColumn(name = "BANK_ADDRESS_ID", referencedColumnName = "BANK_ID"))
  @AttributeOverride(name = "streetName", column = @Column (name = "STREET_NAME", nullable = false, length = 80))
  @AttributeOverride(name = "streetNumber", column = @Column (name = "STREET_NUMBER", nullable = false, length = 10))
  @AttributeOverride(name = "cityName", column = @Column (name = "CITY_NAME", nullable = false, length = 40))
  @AttributeOverride(name = "poBox", column = @Column (name = "PO_BOX", nullable = false, length = 10))
  @AttributeOverride(name = "zipCode", column = @Column (name = "ZIP_CODE", nullable = false, length = 10))
  @AttributeOverride(name = "countryName", column = @Column (name = "COUNTRY_NAME", nullable = false, length = 20))
  @Column(name = "BANK_ADDRESS_ID")
  public List<BankAddressEntity> getBankAddressEntities()
  {
    return bankAddressEntityList;
  }

  public void setBankAddressEntities(@Valid List<BankAddressEntity> bankAccountEntityList)
  {
    this.bankAddressEntityList = bankAddressEntityList;
  }

  public void addBankAddressEntity(@Valid BankAddressEntity bankAddressEntity)
  {
    bankAddressEntityList.add(bankAddressEntity);
  }

  public void removeBankAddressEntity(@Valid BankAddressEntity bankAddressEntity)
  {
    bankAddressEntityList.remove(bankAddressEntity);
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BankEntity that = (BankEntity) o;
    return getId().equals(that.getId());
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(getId());
  }
}
