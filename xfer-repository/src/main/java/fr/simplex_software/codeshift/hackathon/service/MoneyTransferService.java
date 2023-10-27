package fr.simplex_software.codeshift.hackathon.service;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.repository.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;

import java.security.*;
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

  public void deleteMoneyTransferOrder(Long id)
  {
    moneyTransferRepository.deleteById(id);
  }

  public long deleteMoneyTransferOrderByReference(String reference)
  {
    return moneyTransferRepository.deleteMoneyTransferOrderByReference(reference);
  }
}
