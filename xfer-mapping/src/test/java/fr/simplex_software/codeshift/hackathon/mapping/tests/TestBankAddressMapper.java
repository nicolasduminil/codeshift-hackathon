package fr.simplex_software.codeshift.hackathon.mapping.tests;

import fr.simplex_software.codeshift.hackathon.mapping.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import fr.simplex_software.codeshift.hackathon.orm.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class TestBankAddressMapper
{
  @Test
  public void testMapBankAddressToBankAddressEntity()
  {
    BankAddress bankAddress = new BankAddress("streetName", "streetNumber",
      "poBox", "cityName", "zipCode", "countryName");
    BankAddressEntity bankAddressEntity = BankAddressMapper.INSTANCE.toEntity(bankAddress);
    assertThat(bankAddressEntity).isNotNull();
    assertThat(bankAddressEntity.getCityName()).isEqualTo("cityName");
    assertThat(bankAddressEntity.getCountryName()).isEqualTo("countryName");
  }

  @Test
  public void testMapBankAddressEntityToBankAddress()
  {
    BankAddressEntity bankAddressEntity = new BankAddressEntity("streetName", "streetNumber",
      "poBox", "cityName", "zipCode", "countryName");
    BankAddress bankAddress = BankAddressMapper.INSTANCE.fromEntity(bankAddressEntity);
    assertThat(bankAddress).isNotNull();
    assertThat(bankAddress.getCityName()).isEqualTo("cityName");
    assertThat(bankAddress.getCountryName()).isEqualTo("countryName");
  }
}
