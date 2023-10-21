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
  BankAddressEntity toEntity (BankAddress bankAddress);
  BankAddressEntity copyBankAddressEntity (BankAddressEntity bankAddressEntity);
  BankAddress copyBankAddress (BankAddress bankAddress);}
