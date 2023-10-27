package fr.simplex_software.codeshift.hackathon.api;

import fr.simplex_software.codeshift.hackathon.model.*;

import java.io.*;
import java.util.*;

public interface MoneyTransferFacade extends Serializable
{
  List<MoneyTransfer> getMoneyTransferOrders();
  Optional<MoneyTransfer> getMoneyTransferOrder(String reference);
  String createMoneyTransferOrder(MoneyTransfer moneyTransfer);
  long deleteMoneyTransferOrder(String ref);
}
