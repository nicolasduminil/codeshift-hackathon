package fr.simplex_software.codeshift.hackathon.orm;

import jakarta.json.bind.annotation.*;
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
  @Valid
  private List<BankAccountEntity> bankAccountEntityList = new ArrayList<>();

  public BankEntity()
  {
  }

  public BankEntity(@NotBlank String bankName, @Valid List<BankAddressEntity> bankAddressEntityList, @Valid List<BankAccountEntity> bankAccountEntityList)
  {
    this.bankName = bankName;
    this.bankAddressEntityList.clear();
    this.bankAddressEntityList.addAll(bankAddressEntityList);
    this.bankAccountEntityList.clear();
    this.bankAccountEntityList.addAll(bankAccountEntityList);
  }

  @Id
  @SequenceGenerator(name = "bankSequence", sequenceName = "bankId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSequence")
  @Column(name = "BANK_ID", nullable = false, updatable = false)
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

  @OneToMany(mappedBy = "bankEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonbTransient
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
  @JsonbTransient
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
