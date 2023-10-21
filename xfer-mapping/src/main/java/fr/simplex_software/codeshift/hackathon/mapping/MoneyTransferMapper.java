package fr.simplex_software.codeshift.hackathon.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(uses = {BankAccountMapper.class})
public interface MoneyTransferMapper
{
  MoneyTransferMapper INSTANCE = Mappers.getMapper(MoneyTransferMapper.class);
  @Mapping(target = "sourceAccount", source = "sourceBankAccountEntity")
  @Mapping(target = "targetAccount", source = "targetBankAccountEntity")
  MoneyTransfer fromEntity (MoneyTransferEntity moneyTransferEntity);
  @Mapping(target = "sourceBankAccountEntity", source = "sourceAccount")
  @Mapping(target = "targetBankAccountEntity", source = "targetAccount")
  @Mapping(target = "id", ignore = true)
  MoneyTransferEntity toEntity (MoneyTransfer moneyTransfer);
  @Mapping(target="id", ignore = true)
  MoneyTransferEntity copyMoneyTransferEntity (MoneyTransferEntity moneyTransferEntity);
  MoneyTransfer copyMoneyTransfer (MoneyTransfer moneyTransfer);}
