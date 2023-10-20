package fr.simplex_software.codeshift.hackathon.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper
public interface BankAddressMapper
{
  BankAddressMapper INSTANCE = Mappers.getMapper(BankAddressMapper.class);
  BankAddress fromEntity (BankAddressEntity bankAddressEntity);
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "bankEntity", ignore = true)
  BankAddressEntity toEntity (BankAddress bankAddress);
}
