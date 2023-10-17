package fr.simplex_software.codeshift.hackathon.orm;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

@Cacheable
@Entity
@Table(name = "BANK_ACCOUNTS")
public class BankAccountEntity extends BankAccount
{
  @NotNull
  private Long id;
  @NotNull
  private BankEntity bankEntity;

  public BankAccountEntity()
  {
    super();
  }

  public BankAccountEntity(@Valid Bank bank, @NotBlank String accountID, @Valid BankAccountType accountType, @NotBlank String sortCode, @NotBlank String accountNumber, @NotBlank String transCode)
  {
    super(bank, accountID, accountType, sortCode, accountNumber, transCode);
  }

  @Id
  @SequenceGenerator(name = "bankAccountSequence", sequenceName = "bankAccountId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankAccountSequence")
  @Column(name = "ID", nullable = false, updatable = false)
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="BANK_ID")
  public BankEntity getBankEntity()
  {
    return bankEntity;
  }

  public void setBankEntity(@Valid BankEntity bankEntity)
  {
    this.bankEntity = bankEntity;
  }

  @Override
  @Column(name = "ACCOUNT_ID", nullable = false)
  public String getAccountID()
  {
    return super.getAccountID();
  }

  @Override
  public void setAccountID(String value)
  {
    super.setAccountID(value);
  }

  @Override
  @Column(name = "ACCOUNT_TYPE")
  @Enumerated(EnumType.STRING)
  public BankAccountType getAccountType()
  {
    return super.getAccountType();
  }

  @Override
  public void setAccountType(BankAccountType value)
  {
    super.setAccountType(value);
  }

  @Override
  @Column(name = "SORT_CODE",nullable = false)
  public String getSortCode()
  {
    return super.getSortCode();
  }

  @Override
  public void setSortCode(String value)
  {
    super.setSortCode(value);
  }

  @Override
  @Column(name = "ACCOUNT_NUMBER", nullable = false)
  public String getAccountNumber()
  {
    return super.getAccountNumber();
  }

  @Override
  public void setAccountNumber(String value)
  {
    super.setAccountNumber(value);
  }

  @Override
  @Column(name = "TRANS_CODE", nullable = false)
  public String getTransCode()
  {
    return super.getTransCode();
  }

  @Override
  public void setTransCode(String value)
  {
    super.setTransCode(value);
  }
}
