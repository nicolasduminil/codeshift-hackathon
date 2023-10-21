package fr.simplex_software.codeshift.hackathon.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = {BankAccountMapper.class, BankAddressMapper.class})
public interface BankMapper
{
  BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);
  @Mapping(target = "bankAddresses", source = "bankAddressEntities")
  Bank fromEntity (BankEntity bankEntity);
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "bankAddressEntities", source = "bankAddresses")
  BankEntity toEntity (Bank bank);
  BankEntity copyBankEntity (BankEntity bankEntity);
  Bank copyBank (Bank bank);
}
