package fr.simplex_software.codeshift.hackathon.repository;

import fr.simplex_software.codeshift.hackathon.mapping.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import io.quarkus.hibernate.orm.panache.*;
import io.quarkus.panache.common.*;
import jakarta.enterprise.context.*;
import jakarta.transaction.*;

import java.util.*;

@ApplicationScoped
public class MoneyTransferRepository implements PanacheRepository<MoneyTransferEntity>
{
  public Optional<MoneyTransferEntity> findByReference (String reference)
  {
    return find("reference", reference).firstResultOptional();
  }

  @Transactional
  public void createMoneyTransferOrder (MoneyTransferEntity moneyTransferEntity)
  {
    persist(moneyTransferEntity);
  }

  public List<MoneyTransferEntity> getMoneyTransferOrders()
  {
    return listAll();
  }

  @Transactional
  public void deleteMoneyTransferOrder (Long id)
  {
    deleteById(id);
  }

  @Transactional
  public long deleteMoneyTransferOrderByReference (String ref)
  {
    return delete("reference", ref);
  }
}
