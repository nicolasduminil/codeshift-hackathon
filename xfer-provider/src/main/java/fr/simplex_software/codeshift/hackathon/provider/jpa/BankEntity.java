package fr.simplex_software.codeshift.hackathon.provider.jpa;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

import java.util.*;

@Cacheable
@Entity
@Table(name = "BANKS")
public class BankEntity extends Bank
{
  @NotNull
  private Long id;
  @Valid
  private List<BankAddressEntity> bankAddressEntityList = new ArrayList<>();
  @Valid
  private List<BankAccountEntity> bankAccountEntityList = new ArrayList<>();

  public BankEntity()
  {
    super();
  }

  public BankEntity(@Valid List<BankAddress> bankAddresses, @NotBlank String bankName)
  {
    super(bankAddresses, bankName);
  }

  @Id
  @SequenceGenerator(name = "bankSequence", sequenceName = "bankId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSequence")
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @Override
  @Column(name = "BANK_NAME", nullable = false, updatable = false)
  public String getBankName()
  {
    return super.getBankName();
  }

  @Override
  public void setBankName(String bankName)
  {
    super.setBankName(bankName);
  }

  @OneToMany(mappedBy = "bankEntity", cascade = CascadeType.ALL, orphanRemoval = true)
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
    bankAddressEntity.setBankEntity(this);
  }

  public void removeBankAddressEntity(@Valid BankAddressEntity bankAddressEntity)
  {
    bankAddressEntityList.remove(bankAddressEntity);
    bankAddressEntity.setBankEntity(null);
  }

  @OneToMany(mappedBy = "bankEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<BankAccountEntity> getBankAccountEntities()
  {
    return bankAccountEntityList;
  }

  public void setBankAccountEntities(@Valid List<BankAccountEntity> bankAccountEntityList)
  {
    this.bankAccountEntityList = bankAccountEntityList;
  }

  public void addBankAccountEntity(@Valid BankAccountEntity bankAccountEntity)
  {
    bankAccountEntityList.add(bankAccountEntity);
    bankAccountEntity.setBankEntity(this);
  }

  public void removeBankAccountEntity(@Valid BankAccountEntity bankAccountEntity)
  {
    bankAccountEntityList.remove(bankAccountEntity);
    bankAccountEntity.setBankEntity(null);
  }
}
