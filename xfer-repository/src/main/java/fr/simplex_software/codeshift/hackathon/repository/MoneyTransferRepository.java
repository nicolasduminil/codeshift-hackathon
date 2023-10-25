package fr.simplex_software.codeshift.hackathon.repository;

import fr.simplex_software.codeshift.hackathon.mapping.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import io.quarkus.hibernate.orm.panache.*;
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
  public void updateMoneyTransferOrder (Long id, MoneyTransferEntity moneyTransferEntity)
  {
    MoneyTransferEntity mteToUpdate = findById(id);
    mteToUpdate = MoneyTransferMapper.INSTANCE.copyMoneyTransferEntity(moneyTransferEntity);
    getEntityManager().merge(mteToUpdate);
  }

  @Transactional
  public void deleteMoneyTransferOrder (Long id)
  {
    //MoneyTransferEntity moneyTransferEntity = findById(id);
    deleteById(id);
  }
}
