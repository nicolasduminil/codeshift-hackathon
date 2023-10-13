package fr.simplex_software.codeshift.hackathon.provider.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.provider.jpa.*;
import org.mapstruct.*;

@Mapper
public interface BankAccountMapper
{
  @Mapping(target = "bank", source = "bankEntity")
  BankAccount fromEntity(BankAccountEntity bankAccountEntity);
  @Mapping(target = "bankEntity", source = "bank")
  @Mapping(target = "id", ignore = true)
  BankAccountEntity toEntity (BankAccount bankAccount);
}
