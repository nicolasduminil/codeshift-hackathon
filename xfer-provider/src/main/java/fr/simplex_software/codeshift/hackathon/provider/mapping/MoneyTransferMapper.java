package fr.simplex_software.codeshift.hackathon.provider.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.provider.jpa.*;
import org.mapstruct.*;

@Mapper
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
