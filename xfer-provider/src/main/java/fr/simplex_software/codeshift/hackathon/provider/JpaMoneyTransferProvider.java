package fr.simplex_software.codeshift.hackathon.provider;

import fr.simplex_software.codeshift.hackathon.api.*;
import fr.simplex_software.codeshift.hackathon.model.*;

import java.util.*;

public class JpaMoneyTransferProvider implements MoneyTransferFacade
{
  @Override
  public List<MoneyTransfer> getMoneyTransferOrders()
  {
    return null;
  }

  @Override
  public Optional<MoneyTransfer> getMoneyTransferOrder(String reference)
  {
    return Optional.empty();
  }

  @Override
  public String createMoneyTransferOrder(MoneyTransfer moneyTransfer)
  {
    return null;
  }

  @Override
  public void updateMoneyTransferOrder(String ref, MoneyTransfer moneyTransfer)
  {

  }

  @Override
  public void deleteMoneyTransferOrder(String ref)
  {

  }
}
