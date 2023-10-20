package fr.simplex_software.codeshift.hackathon.orm;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

import java.math.*;
import java.util.*;

@Cacheable
@Entity
@Table(name = "MONEY_TRANSFER_ORDERS")
public class MoneyTransferEntity
{
  @NotNull
  private Long id;
  @NotBlank
  @Size(max = 25)
  private String reference;
  @DecimalMin(value = "0")
  private BigDecimal amount;
  @NotNull
  private BankAccountEntity sourceBankAccountEntity;
  @NotNull
  private BankAccountEntity targetBankAccountEntity;

  public MoneyTransferEntity()
  {
  }

  public MoneyTransferEntity(String reference, BigDecimal amount)
  {
    this.reference = reference;
    this.amount = amount;
  }

  public MoneyTransferEntity(String reference, BigDecimal amount, BankAccountEntity sourceBankAccountEntity, BankAccountEntity targetBankAccountEntity)
  {
    this.reference = reference;
    this.amount = amount;
    this.sourceBankAccountEntity = sourceBankAccountEntity;
    this.targetBankAccountEntity = targetBankAccountEntity;
  }

  @Id
  @SequenceGenerator(name = "moneyTransferSequence", sequenceName = "moneyTransferId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moneyTransferSequence")
  @Column(name = "MONEY_TRANSFER_ID", nullable = false, updatable = false)
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @Column(name = "MONEY_TRANSFER_ORDER_REF", length = 25)
  public String getReference()
  {
    return reference;
  }

  public void setReference(String reference)
  {
    this.reference = reference;
  }

  @Column(name = "MONEY_TRANSFER_AMOUNT", nullable = false)
  public BigDecimal getAmount()
  {
    return amount;
  }

  public void setAmount(BigDecimal amount)
  {
    this.amount = amount;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SOURCE_BANK_ACCOUNT_ID")
  public BankAccountEntity getSourceBankAccountEntity()
  {
    return sourceBankAccountEntity;
  }

  public void setSourceBankAccountEntity(@NotNull BankAccountEntity sourceBankAccountEntity)
  {
    this.sourceBankAccountEntity = sourceBankAccountEntity;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TARGET_BANK_ACCOUNT_ID")
  public BankAccountEntity getTargetBankAccountEntity()
  {
    return targetBankAccountEntity;
  }

  public void setTargetBankAccountEntity(BankAccountEntity targetBankAccountEntity)
  {
    this.targetBankAccountEntity = targetBankAccountEntity;
  }
}
