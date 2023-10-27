package fr.simplex_software.codeshift.hackathon.repository.tests;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.repository.*;
import io.quarkus.test.common.*;
import io.quarkus.test.h2.*;
import io.quarkus.test.junit.*;
import jakarta.inject.*;
import jakarta.transaction.*;
import jakarta.xml.bind.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMoneyTransferRepository
{
  @Inject
  MoneyTransferRepository moneyTransferRepository;

  @Test
  @Order(10)
  @Transactional
  public void testInsertMoneyTransferOrders() throws Exception
  {
    MoneyTransferEntity moneyTransferEntity = new MoneyTransferEntity("reference", new BigDecimal("100.00"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"));
    assertDoesNotThrow(() -> moneyTransferRepository.getEntityManager().persist(moneyTransferEntity));
  }

  @Test
  @Order(20)
  public void testFindAllMoneyTransferOrders()
  {
    List<MoneyTransferEntity> moneyTransferEntityList = moneyTransferRepository.findAll().list();
    assertThat(moneyTransferEntityList).isNotNull();
    assertThat(moneyTransferEntityList.size()).isEqualTo(6);
    MoneyTransferEntity moneyTransferEntity = moneyTransferEntityList.get(0);
    assertThat(moneyTransferEntity.getAmount()).isEqualTo("100.00");
    assertThat(moneyTransferEntity.getReference()).isEqualTo("reference");
    assertThat(moneyTransferEntity.getSourceBankAccountEntity()).isNotNull();
    assertThat(moneyTransferEntity.getTargetBankAccountEntity()).isNotNull();
    BankAccountEntity bankAccountEntity = moneyTransferEntity.getSourceBankAccountEntity();
    assertThat((bankAccountEntity.getAccountType())).isEqualTo(BankAccountType.CHECKING);
    assertThat(bankAccountEntity.getSortCode()).isEqualTo("sortCode");
    assertThat(bankAccountEntity.getBankEntity()).isNotNull();
    assertThat(bankAccountEntity.getBankEntity().getBankName()).isEqualTo("bankName");
  }
}
