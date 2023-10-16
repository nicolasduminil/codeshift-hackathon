package fr.simplex_software.codeshift.hackathon.repository.tests;

import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.repository.*;
import io.quarkus.test.*;
import io.quarkus.test.junit.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@QuarkusTest
public class TestBankRepository
{
  private BankEntity bankEntity;

  @BeforeEach
  public void beforeEach()
  {
    bankEntity = new BankEntity();
  }

  @AfterEach
  public void afterEach()
  {
    bankEntity = null;
  }

  @InjectMock
  private BankRepository bankRepository;

  @Test
  public void testPersist()
  {
    doNothing().when(bankRepository).persist(bankEntity);
    bankRepository.persist(bankEntity);
    verify(bankRepository, times(1)).persist(bankEntity);
  }

  @Test
  public void testFindById()
  {
    when(bankRepository.findById(anyLong())).thenReturn(bankEntity);
    assertSame(bankEntity, bankRepository.findById(1234L));
  }

  @Test
  public void testUpdate() throws InstantiationException, IllegalAccessException
  {
    when(bankRepository.update("none", bankEntity)).thenReturn(1);
    assertThat(bankRepository.update("none", bankEntity)).isEqualTo(1);
  }

  @Test
  public void testDelete()
  {
    when (bankRepository.deleteById(anyLong())).thenReturn(true);
    assertThat(bankRepository.deleteById(1234L)).isTrue();
  }
}
