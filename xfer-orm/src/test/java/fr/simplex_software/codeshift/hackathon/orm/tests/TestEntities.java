package fr.simplex_software.codeshift.hackathon.orm.tests;

import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import io.quarkus.test.junit.*;
import jakarta.inject.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@QuarkusTest
public class TestEntities
{
  @Inject
  private EntityManager em;

  @Test
  public void testGetAllBanks()
  {
    Query q = em.createQuery("select b from BankEntity b");
    List<BankEntity> banks = q.getResultList();
    banks.forEach(b -> System.out.println ("### Bank id, name: " + b.getId() + " " + b.getBankName()));
    assertThat(banks).isNotNull();
    assertThat(banks.size()).isEqualTo(2);
  }

  @Test
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
}
