package fr.simplex_software.codeshift.hackathon.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.mapstruct.*;

@Mapper(uses = {BankMapper.class})
public interface BankAccountMapper
{
  @Mapping(target = "bank", source = "bankEntity")
  BankAccount fromEntity(BankAccountEntity bankAccountEntity);
  @Mapping(target = "bankEntity", source = "bank")
  @Mapping(target = "id", ignore = true)
  BankAccountEntity toEntity (BankAccount bankAccount);
}
