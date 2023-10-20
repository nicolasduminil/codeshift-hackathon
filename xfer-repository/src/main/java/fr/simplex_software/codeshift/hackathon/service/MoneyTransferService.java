package fr.simplex_software.codeshift.hackathon.service;

import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.repository.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import jakarta.transaction.*;

@ApplicationScoped
public class MoneyTransferService
{
  @Inject
  private MoneyTransferRepository moneyTransferRepository;

  @Transactional
  public void persisteMoneyTransfer (MoneyTransferEntity moneyTransferEntity)
  {
    moneyTransferRepository.persist(moneyTransferEntity);
  }
}
