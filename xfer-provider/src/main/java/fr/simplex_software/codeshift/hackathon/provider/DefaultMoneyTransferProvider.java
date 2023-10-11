package fr.simplex_software.codeshift.hackathon.provider;

import fr.simplex_software.codeshift.hackathon.api.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.enterprise.context.*;

import java.util.*;
import java.util.concurrent.*;

@ApplicationScoped
public class DefaultMoneyTransferProvider implements MoneyTransferFacade
{
  private final Map<String, MoneyTransfer> moneyTransferMap = new ConcurrentHashMap<>();

  @Override
  public List<MoneyTransfer> getMoneyTransferOrders()
  {
    return new ArrayList<>(moneyTransferMap.values());
  }

  @Override
  public Optional<MoneyTransfer> getMoneyTransferOrder(String reference)
  {
    return Optional.ofNullable(moneyTransferMap.get(reference));
  }

  @Override
  public String createMoneyTransferOrder(MoneyTransfer moneyTransfer)
  {
    String ref = moneyTransfer.getReference();
    moneyTransferMap.put(ref, moneyTransfer);
    return ref;
  }

  @Override
  public void updateMoneyTransferOrder(String ref, MoneyTransfer moneyTransfer)
  {
    moneyTransferMap.put(ref, moneyTransfer);
  }

  @Override
  public void deleteMoneyTransferOrder(String ref)
  {
    moneyTransferMap.remove(ref);
  }
}
