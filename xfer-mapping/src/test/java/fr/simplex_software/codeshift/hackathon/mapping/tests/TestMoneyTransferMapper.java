package fr.simplex_software.codeshift.hackathon.mapping.tests;

import fr.simplex_software.codeshift.hackathon.mapping.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.junit.jupiter.api.*;

import java.math.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TestMoneyTransferMapper
{
  @Test
  public void testMapMoneyTransferToMoneyTransferEntity()
  {
    MoneyTransfer moneyTransfer = new MoneyTransfer("reference",
      new BankAccount(new Bank(List.of(new BankAddress("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName")), "bankName"),
        "accountID", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"),
      new BankAccount(new Bank(List.of(new BankAddress("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName")), "bankName"),
        "accountID", BankAccountType.CHECKING, "sortCode", "accountNumber",
        "transCode"), new BigDecimal("100.00"));
    MoneyTransferEntity moneyTransferEntity = MoneyTransferMapper.INSTANCE.toEntity(moneyTransfer);
    assertThat(moneyTransferEntity).isNotNull();
    assertThat(moneyTransferEntity.getReference()).isEqualTo("reference");
    assertThat(moneyTransferEntity.getAmount().doubleValue()).isEqualTo(100.00);
    BankAccountEntity bankAccountEntity = moneyTransferEntity.getSourceBankAccountEntity();
    assertThat(bankAccountEntity).isNotNull();
    BankEntity bankEntity = bankAccountEntity.getBankEntity();
    assertThat(bankEntity).isNotNull();
    assertThat(bankEntity.getBankName()).isEqualTo("bankName");
    List<BankAddressEntity> addressEntities = bankEntity.getBankAddressEntities();
    assertThat(addressEntities).isNotNull();
    assertThat(addressEntities.size()).isEqualTo(1);
    BankAddressEntity bankAddressEntity = addressEntities.get(0);
    assertThat(bankAddressEntity).isNotNull();
    assertThat(bankAddressEntity.getCityName()).isEqualTo("cityName");
    assertThat(bankAddressEntity.getCountryName()).isEqualTo("countryName");
    bankAccountEntity = moneyTransferEntity.getTargetBankAccountEntity();
    assertThat(bankAccountEntity).isNotNull();
    bankEntity = bankAccountEntity.getBankEntity();
    assertThat(bankEntity).isNotNull();
    assertThat(bankEntity.getBankName()).isEqualTo("bankName");
    addressEntities = bankEntity.getBankAddressEntities();
    assertThat(addressEntities).isNotNull();
    assertThat(addressEntities.size()).isEqualTo(1);
    bankAddressEntity = addressEntities.get(0);
    assertThat(bankAddressEntity).isNotNull();
    assertThat(bankAddressEntity.getCityName()).isEqualTo("cityName");
    assertThat(bankAddressEntity.getCountryName()).isEqualTo("countryName");
  }

  @Test
  public void testMapMoneyTransferEntityToMoneyTransfer()
  {
    MoneyTransferEntity moneyTransferEntity = new MoneyTransferEntity("reference", new BigDecimal("100.00"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"));
    MoneyTransfer moneyTransfer = MoneyTransferMapper.INSTANCE.fromEntity(moneyTransferEntity);
    assertThat(moneyTransfer).isNotNull();
    assertThat(moneyTransfer.getReference()).isEqualTo("reference");
    assertThat(moneyTransfer.getAmount().doubleValue()).isEqualTo(100.00);
    BankAccount bankAccount = moneyTransfer.getSourceAccount();
    assertThat(bankAccount).isNotNull();
    assertThat(bankAccount.getAccountNumber()).isEqualTo("accountNumber");
    assertThat(bankAccount.getAccountID()).isEqualTo("accountId");
    assertThat(bankAccount.getAccountType()).isEqualTo(BankAccountType.CHECKING);
    bankAccount = moneyTransfer.getTargetAccount();
    assertThat(bankAccount).isNotNull();
    assertThat(bankAccount.getAccountNumber()).isEqualTo("accountNumber");
    assertThat(bankAccount.getAccountID()).isEqualTo("accountId");
    assertThat(bankAccount.getAccountType()).isEqualTo(BankAccountType.CHECKING);
  }
}
