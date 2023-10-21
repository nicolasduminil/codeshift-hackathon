package fr.simplex_software.codeshift.hackathon.orm;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

import java.util.*;

@Cacheable
@Entity
@Table(name = "BANK_ACCOUNTS")
public class BankAccountEntity
{
  @NotNull
  private Long id;
  @NotBlank
  @Size(max = 20)
  private String accountID;
  @NotNull
  private BankAccountType accountType;
  @NotBlank
  @Size(max = 10)
  private String sortCode;
  @NotBlank
  @Size(max = 30)
  private String accountNumber;
  @NotBlank
  @Size(max = 10)
  private String transCode;
  @NotNull
  private BankEntity bankEntity;

  public BankAccountEntity()
  {
  }

  public BankAccountEntity(@Valid BankEntity bankEntity, @NotBlank String accountID, @Valid BankAccountType accountType, @NotBlank String sortCode, @NotBlank String accountNumber, @NotBlank String transCode)
  {
    this.bankEntity = bankEntity;
    this.accountID = accountID;
    this.accountType = accountType;
    this.sortCode = sortCode;
    this.accountNumber = accountNumber;
    this.transCode = transCode;
  }

  @Id
  @SequenceGenerator(name = "bankAccountSequence", sequenceName = "bankAccountId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankAccountSequence")
  @Column(name = "BANK_ACCOUNT_ID", unique = true, nullable = false, updatable = false, length = 8)
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @Column(name = "ACCOUNT_ID", nullable = false)
  public String getAccountID()
  {
    return accountID;
  }

  public void setAccountID(String accountId)
  {
    this.accountID = accountId;
  }

  @Column(name = "ACCOUNT_TYPE")
  @Enumerated(EnumType.STRING)
  public BankAccountType getAccountType()
  {
    return accountType;
  }

  public void setAccountType(BankAccountType accountType)
  {
    this.accountType = accountType;
  }
  @Column(name = "SORT_CODE", length = 10)
  public String getSortCode()
  {
    return sortCode;
  }

  public void setSortCode(String sortCode)
  {
    this.sortCode = sortCode;
  }

  @Column(name = "ACCOUNT_NUMBER", length = 30)
  public String getAccountNumber()
  {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber)
  {
    this.accountNumber = accountNumber;
  }

  @Column(name = "TRANS_CODE", length = 10)
  public String getTransCode()
  {
    return transCode;
  }

  public void setTransCode(String transCode)
  {
    this.transCode = transCode;
  }

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "BANK_ID", updatable = false, insertable = false)
  public BankEntity getBankEntity()
  {
    return bankEntity;
  }

  public void setBankEntity(@Valid BankEntity bankEntity)
  {
    this.bankEntity = bankEntity;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BankAccountEntity that = (BankAccountEntity) o;
    return getId().equals(that.getId());
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(getId());
  }
}
