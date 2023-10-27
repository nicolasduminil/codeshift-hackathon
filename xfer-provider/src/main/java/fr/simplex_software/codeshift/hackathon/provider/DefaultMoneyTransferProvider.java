package fr.simplex_software.codeshift.hackathon.provider;

import fr.simplex_software.codeshift.hackathon.api.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.enterprise.context.*;
import jakarta.enterprise.inject.*;

import java.util.*;
import java.util.concurrent.*;

@ApplicationScoped
@Alternative
@InMemory
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
  public long deleteMoneyTransferOrder(String ref)
  {
    moneyTransferMap.remove(ref);
    return 0L;
  }
}
