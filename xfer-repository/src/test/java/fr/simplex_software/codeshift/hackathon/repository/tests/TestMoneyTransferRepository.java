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
  @Inject
  BankRepository bankRepository;
  @Inject
  UserTransaction tx;

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
  //@Transactional
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
    moneyTransferRepository.findAll().list().forEach(mt -> System.out.println ("### List of IDs on start: " + mt.getId()));
    BankAddressEntity socgenAddress = new BankAddressEntity("av. de Paris", "18", "Cedex", "Montmorency", "95800", "France", null);
    BankAddressEntity ingAddress = new BankAddressEntity("blvd. Waterloo", "381", "INGPOBOX", "Waterloo", "B1000", "Belgium", null);
    BankAccountEntity sourceBankAccount = new BankAccountEntity(null, "SG01", BankAccountType.CHECKING, "04004", "00050525712", "03000");
    BankAccountEntity targetBankAccount = new BankAccountEntity(null, "ING01", BankAccountType.SAVINGS, "08080", "425091789", "7BRE0");
    BankEntity socgen = new BankEntity("Société Générale", List.of(socgenAddress), List.of(sourceBankAccount));
    BankEntity ing = new BankEntity("ING", List.of(ingAddress), List.of(targetBankAccount));
    sourceBankAccount.setBankEntity(socgen);
    targetBankAccount.setBankEntity(ing);
    tx.begin();
    bankRepository.persist(socgen);
    bankRepository.persist(ing);
    tx.commit();
    MoneyTransferEntity moneyTransferEntity = new MoneyTransferEntity("Tech Repairs 2", new BigDecimal("1000.00"), sourceBankAccount, targetBankAccount);
    sourceBankAccount.setSourceMoneyTransferEntityList(List.of(moneyTransferEntity));
    targetBankAccount.setTargetMoneyTransferEntityList(List.of(moneyTransferEntity));
    tx.begin();
    moneyTransferRepository.persist(moneyTransferEntity);
    tx.commit();
    moneyTransferEntity = new MoneyTransferEntity("Taxes and Licences 2", new BigDecimal("800.00"), sourceBankAccount, targetBankAccount);
    tx.begin();
    moneyTransferRepository.persist(moneyTransferEntity);
    tx.commit();
    moneyTransferRepository.findAll().list().forEach(mt -> System.out.println ("### ID: " + mt.getId()));
    moneyTransferEntity = new MoneyTransferEntity("Advertising 2", new BigDecimal("1234.00"), targetBankAccount, sourceBankAccount);
    tx.begin();
    moneyTransferRepository.persist(moneyTransferEntity);
    tx.commit();
    moneyTransferEntity = new MoneyTransferEntity("Meal and Entertainment 2", new BigDecimal("2000.00"), targetBankAccount, sourceBankAccount);
    tx.begin();
    moneyTransferRepository.persist(moneyTransferEntity);
    tx.commit();
    moneyTransferEntity = new MoneyTransferEntity("Other expanses 2", new BigDecimal("1500.00"), targetBankAccount, sourceBankAccount);
    tx.begin();
    moneyTransferRepository.persist(moneyTransferEntity);
    tx.commit();

    /*List<MoneyTransferEntity> moneyTransferEntityList = Arrays.asList(new MoneyTransferEntity("Tech Repairs 2", new BigDecimal("1000.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Taxes and Licences 2", new BigDecimal("800.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Advertising 2", new BigDecimal("1234.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Meal and Entertainment 2", new BigDecimal("2000.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Other expanses 2", new BigDecimal("1500.00"), targetBankAccount, sourceBankAccount));
    sourceBankAccount.setSourceMoneyTransferEntityList(moneyTransferEntityList);
    targetBankAccount.setTargetMoneyTransferEntityList(moneyTransferEntityList);
    moneyTransferEntityList.forEach(mte -> moneyTransferRepository.persist(mte));*/
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

  private void marshalMoneyTransfersToXmlFile(MoneyTransfers moneyTransfers)
  {
    try
    {
      Marshaller marshaller = JAXBContext.newInstance(MoneyTransfers.class).createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(moneyTransfers, new File("customer.xml"));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private MoneyTransfers unmarshalXmlFileToCustomer(File xml)
  {
    MoneyTransfers moneyTransfers = null;
    try
    {
      moneyTransfers = (MoneyTransfers) JAXBContext.newInstance(MoneyTransfers.class).createUnmarshaller().unmarshal(xml);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return moneyTransfers;
  }

  private List<MoneyTransferEntity> getMoneyTransferOrders()
  {
    BankAddressEntity socgenAddress = new BankAddressEntity("av. de Paris", "18", "Cedex", "Montmorency", "95800", "France", null);
    BankAddressEntity ingAddress = new BankAddressEntity("blvd. Waterloo", "381", "INGPOBOX", "Waterloo", "B1000", "Belgium", null);
    BankAccountEntity sourceBankAccount = new BankAccountEntity(null, "SG01", BankAccountType.CHECKING, "04004", "00050525712", "03000");
    BankAccountEntity targetBankAccount = new BankAccountEntity(null, "ING01", BankAccountType.SAVINGS, "08080", "425091789", "7BRE0");
    BankEntity socgen = new BankEntity("Société Générale", List.of(socgenAddress), List.of(sourceBankAccount));
    BankEntity ing = new BankEntity("ING", List.of(ingAddress), List.of(targetBankAccount));
    sourceBankAccount.setBankEntity(socgen);
    targetBankAccount.setBankEntity(ing);
    List<MoneyTransferEntity> moneyTransferEntityList = Arrays.asList(new MoneyTransferEntity("Tech Repairs 2", new BigDecimal("1000.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Taxes and Licences 2", new BigDecimal("800.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Advertising 2", new BigDecimal("1234.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Meal and Entertainment 2", new BigDecimal("2000.00"), targetBankAccount, sourceBankAccount),
      new MoneyTransferEntity("Other expanses 2", new BigDecimal("1500.00"), targetBankAccount, sourceBankAccount));
    return moneyTransferEntityList;
  }
}
