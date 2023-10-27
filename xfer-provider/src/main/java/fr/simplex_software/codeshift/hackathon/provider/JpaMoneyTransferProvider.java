package fr.simplex_software.codeshift.hackathon.provider;

import fr.simplex_software.codeshift.hackathon.api.*;
import fr.simplex_software.codeshift.hackathon.mapping.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.service.*;
import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.*;
import jakarta.inject.*;

import java.util.*;
import java.util.stream.*;

@ApplicationScoped
@Alternative
@PostgreSQL
public class JpaMoneyTransferProvider implements MoneyTransferFacade
{
  @Inject
  MoneyTransferService moneyTransferService;

  @Override
  public List<MoneyTransfer> getMoneyTransferOrders()
  {
    return moneyTransferService.getMoneyTransferOrders().stream().map(MoneyTransferMapper.INSTANCE::fromEntity).collect(Collectors.toList());
  }

  @Override
  public Optional<MoneyTransfer> getMoneyTransferOrder(String reference)
  {
    return Optional.ofNullable(MoneyTransferMapper.INSTANCE.fromEntity(moneyTransferService.getMoneyTransferOrder(reference).orElseThrow()));
  }

  @Override
  public String createMoneyTransferOrder(MoneyTransfer moneyTransfer)
  {
    moneyTransferService.createMoneyTransferOrder(MoneyTransferMapper.INSTANCE.toEntity(moneyTransfer));
    return moneyTransfer.getReference();
  }

  @Override
  public int updateMoneyTransferOrder(String ref, MoneyTransfer moneyTransfer)
  {
    return moneyTransferService.updateMoneyTransferOrder(ref, MoneyTransferMapper.INSTANCE.toEntity(moneyTransfer));
  }

  @Override
  public long deleteMoneyTransferOrder(String ref)
  {
    return moneyTransferService.deleteMoneyTransferOrderByReference(ref);
  }
}
