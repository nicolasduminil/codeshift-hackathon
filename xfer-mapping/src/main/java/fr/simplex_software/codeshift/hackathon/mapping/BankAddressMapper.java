package fr.simplex_software.codeshift.hackathon.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.mapstruct.*;

@Mapper
public interface BankAddressMapper
{
  BankAddress fromEntity (BankAddressEntity bankAddressEntity);
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "bankEntity", ignore = true)
  BankAddressEntity toEntity (BankAddress bankAddress);
}
