package fr.simplex_software.codeshift.hackathon.orm;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

import java.math.*;

@Cacheable
@Entity
@Table(name = "MONEY_TRANSFER_ORDERS")
public class MoneyTransferEntity extends MoneyTransfer
{
  @NotNull
  private Long id;
  @Valid
  private BankAccountEntity targetBankAccountEntity;
  @Valid
  private BankAccountEntity sourceBankAccountEntity;

  public MoneyTransferEntity()
  {
    super();
  }

  public MoneyTransferEntity(@NotBlank String reference, @Valid BankAccount sourceAccount, @Valid BankAccount targetAccount, @DecimalMin("1.0") BigDecimal amount)
  {
    super(reference, sourceAccount, targetAccount, amount);
  }

  @Id
  @SequenceGenerator(name = "moneyTransferSequence", sequenceName = "moneyTransferId_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moneyTransferSequence")
  public Long getId()
  {
    return id;
  }

  public void setId(@NotNull Long id)
  {
    this.id = id;
  }

  @Override
  @Column(name = "MONEY_TRANSFER_ORDER_REF", nullable = false)
  public String getReference()
  {
    return super.getReference();
  }

  @Override
  public void setReference(String reference)
  {
    super.setReference(reference);
  }

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  public BankAccountEntity getSourceBankAccountEntity()
  {
    return sourceBankAccountEntity;
  }

  public void setSourceBankAccountEntity(@Valid BankAccountEntity sourceBankAccountEntity)
  {
    this.sourceBankAccountEntity = sourceBankAccountEntity;
  }

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  public BankAccountEntity getTargetBankAccountEntity()
  {
    return targetBankAccountEntity;
  }

  public void setTargetBankAccountEntity(@Valid BankAccountEntity targetBankAccountEntity)
  {
    this.targetBankAccountEntity = targetBankAccountEntity;
  }

  @Override
  @Column(name = "MONEY_TRANSFER_AMOUNT", nullable = false)
  public BigDecimal getAmount()
  {
    return super.getAmount();
  }

  @Override
  public void setAmount(BigDecimal amount)
  {
    super.setAmount(amount);
  }
}
