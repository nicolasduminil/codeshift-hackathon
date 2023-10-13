package fr.simplex_software.codeshift.hackathon.provider.mapping;

import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.provider.jpa.*;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface BankMapper
{
  @Mapping(target = "bankAddresses", source = "bankAddressEntities")
  Bank fromEntity (BankEntity bankEntity);
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "bankAccountEntities", ignore = true)
  @Mapping(target = "bankAddressEntities", source = "bankAddresses")
  BankEntity toEntity (Bank bank);
}
