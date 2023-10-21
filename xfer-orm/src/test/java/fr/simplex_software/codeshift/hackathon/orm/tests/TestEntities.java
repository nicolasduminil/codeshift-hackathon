package fr.simplex_software.codeshift.hackathon.orm.tests;

import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import io.quarkus.test.junit.*;
import jakarta.inject.*;
import jakarta.persistence.*;
import jakarta.transaction.*;
import org.junit.jupiter.api.*;

import java.math.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEntities
{
  @Inject
  private EntityManager em;

  @Test
  @Order(10)
  public void testGetAllBanks()
  {
    Query q = em.createQuery("select b from BankEntity b");
    List<BankEntity> banks = q.getResultList();
    banks.forEach(b -> System.out.println ("### Bank id, name: " + b.getId() + " " + b.getBankName()));
    assertThat(banks).isNotNull();
    assertThat(banks.size()).isEqualTo(2);
  }

  @Test
  @Order(20)
  public void testFindBankByName()
  {
    Query q = em.createQuery("select b from BankEntity b where bankName = 'Société Générale'");
    List<BankEntity> banks = q.getResultList();
    assertThat(banks).isNotNull();
    assertThat(banks.size()).isEqualTo(1);
    BankEntity bankEntity = banks.get(0);
    assertThat(bankEntity.getBankAddressEntities()).isNotNull();
    //assertThat(bankEntity.getBankAddressEntities().size()).isEqualTo(1);
  }

  @Test
  @Order(30)
  public void testGetMoneyTransfers()
  {
    Query q = em.createQuery("select mt from MoneyTransferEntity mt");
    List<MoneyTransferEntity> moneyTransferEntities = q.getResultList();
    assertThat(moneyTransferEntities).isNotNull();
    assertThat(moneyTransferEntities.size()).isEqualTo(5);
    MoneyTransferEntity moneyTransferEntity = moneyTransferEntities.get(0);
    assertThat(moneyTransferEntity.getAmount()).isEqualTo("1000.00");
    assertThat(moneyTransferEntity.getReference()).isEqualTo("Tech Repairs");
    assertThat(moneyTransferEntity.getSourceBankAccountEntity()).isNotNull();
    assertThat(moneyTransferEntity.getTargetBankAccountEntity()).isNotNull();
    BankAccountEntity bankAccountEntity = moneyTransferEntity.getSourceBankAccountEntity();
    assertThat((bankAccountEntity.getAccountType())).isEqualTo(BankAccountType.CHECKING);
    assertThat(bankAccountEntity.getSortCode()).isEqualTo("04004");
    assertThat(bankAccountEntity.getBankEntity()).isNotNull();
    assertThat(bankAccountEntity.getBankEntity().getBankName()).isEqualTo("Société Générale");
  }

  @Test
  @Order(40)
  @Transactional
  public void testCreateNewMoneyTransfer()
  {
    MoneyTransferEntity moneyTransferEntity = new MoneyTransferEntity("reference", new BigDecimal("100.00"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"));
    em.persist(moneyTransferEntity);
    Query q = em.createQuery("select mt from MoneyTransferEntity mt");
    List<MoneyTransferEntity> moneyTransferEntities = q.getResultList();
    assertThat(moneyTransferEntities).isNotNull();
    assertThat(moneyTransferEntities.size()).isEqualTo(6);

  }
}
