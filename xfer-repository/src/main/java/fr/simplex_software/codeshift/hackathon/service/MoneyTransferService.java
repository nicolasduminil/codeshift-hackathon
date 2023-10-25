package fr.simplex_software.codeshift.hackathon.service;

import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.repository.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;

import java.util.*;

@ApplicationScoped
public class MoneyTransferService
{
  @Inject
  MoneyTransferRepository moneyTransferRepository;

  public void createMoneyTransferOrder (MoneyTransferEntity moneyTransferEntity)
  {
    moneyTransferRepository.createMoneyTransferOrder(moneyTransferEntity);
  }

  public List<MoneyTransferEntity> getMoneyTransferOrders()
  {
    return moneyTransferRepository.getMoneyTransferOrders();
  }

  public Optional<MoneyTransferEntity> getMoneyTransferOrder(String reference)
  {
    return moneyTransferRepository.findByReference(reference);
  }

  public void updateMoneyTransferOrder(String ref, MoneyTransferEntity moneyTransferEntity)
  {
    MoneyTransferEntity mteToUpdate = moneyTransferRepository.findByReference(ref).orElseThrow();
    moneyTransferRepository.updateMoneyTransferOrder(mteToUpdate.getId(), moneyTransferEntity);
  }

  public void deleteMoneyTransferOrder(Long id)
  {
    moneyTransferRepository.deleteById(id);
  }

  /*public void updateMoneyTransferOrderByReference(String ref, MoneyTransferEntity moneyTransferEntity)
  {
    MoneyTransferEntity mteToUpdate = moneyTransferRepository.findByReference(ref).orElseThrow();
    moneyTransferRepository.updateMoneyTransferOrder(ref, mteToUpdate);
  }*/

  public void deleteMoneyTransferOrderByReference(String reference)
  {
    MoneyTransferEntity mteToDelete = moneyTransferRepository.findByReference(reference).orElseThrow();
    moneyTransferRepository.deleteById(mteToDelete.getId());
  }
}
