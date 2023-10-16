package fr.simplex_software.codeshift.hackathon.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.mapstruct.*;

@Mapper(uses = {BankAccountMapper.class})
public interface MoneyTransferMapper
{
  @Mapping(target = "sourceAccount", source = "sourceBankAccountEntity")
  @Mapping(target = "targetAccount", source = "targetBankAccountEntity")
  MoneyTransfer fromEntity (MoneyTransferEntity moneyTransferEntity);
  @Mapping(target = "sourceBankAccountEntity", source = "sourceAccount")
  @Mapping(target = "targetBankAccountEntity", source = "targetAccount")
  @Mapping(target = "id", ignore = true)
  MoneyTransferEntity toEntity (MoneyTransfer moneyTransfer);
}
