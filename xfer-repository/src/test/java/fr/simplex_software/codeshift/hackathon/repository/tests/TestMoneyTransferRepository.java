package fr.simplex_software.codeshift.hackathon.repository.tests;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.repository.*;
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

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMoneyTransferRepository
{
  @Inject
  MoneyTransferRepository moneyTransferRepository;


  /*@Test
  @Order(1)
  public void testDeleteAllMoneyTransferOrders() throws Exception
  {
    tx.begin();
    moneyTransferRepository.deleteAll();
    tx.commit();
  }*/

  @Test
  @Order(10)
  @Transactional
  public void testInsertMoneyTransferOrders() throws Exception
  {
    /*List<MoneyTransferEntity> moneyTransferEntities = getMoneyTransferOrders();
    //moneyTransferRepository.persist(moneyTransferEntities.stream());
   moneyTransferEntities.forEach(mt ->
    {
      try
      {
        System.out.println ("*** Persisting " + mt.getReference() + " with ID " + mt.getId());
        tx.begin();
        moneyTransferRepository.getEntityManager().merge(mt);
        tx.commit();
        System.out.println ("*** Persisted " + mt.getReference()+ " with ID " + mt.getId());
      }
      catch (Exception e)
      {
        System.out.println ("*** Could not persist " + mt.getReference() + " with ID " + mt.getId() + " because of " + e);
        try
        {
          tx.rollback();
        }
        catch (SystemException ex)
        {
          throw new RuntimeException(ex);
        }
      }
    });*/
    MoneyTransferEntity moneyTransferEntity = new MoneyTransferEntity("reference", new BigDecimal("100.00"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"),
      new BankAccountEntity(new BankEntity("bankName", List.of(new BankAddressEntity("streetName", "10",
        "poBox", "cityName", "zipCode", "countryName"))), "accountId", BankAccountType.CHECKING, "sortCode", "accountNumber", "transCode"));
  moneyTransferRepository.getEntityManager().persist(moneyTransferEntity);
  }

  @Test
  @Order(20)
  public void testFindAllMoneyTransferOrders()
  {
    List<MoneyTransferEntity> moneyTransferEntityList = moneyTransferRepository.findAll().list();
    assertThat(moneyTransferEntityList).isNotNull();
    assertThat(moneyTransferEntityList.size()).isEqualTo(7);
    moneyTransferEntityList.forEach(mte -> System.out.println("### mte: " + mte.getReference()));
  }
}
