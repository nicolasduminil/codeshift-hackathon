package fr.simplex_software.codeshift.hackathon.model.tests;

import jakarta.xml.bind.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import fr.simplex_software.codeshift.hackathon.model.*;

import static org.assertj.core.api.Assertions.*;

public class TestMoneyTransferOrders
{
  @Test
  public void testUnmarshalMoneyTransferFile()
  {
    MoneyTransfers moneyTransfers = (MoneyTransfers) unmarshalXmlFile(new File("src/main/resources/xml//money-transfers.xml"));
    assertThat(moneyTransfers).isNotNull();
    List<MoneyTransfer> moneyTransferList = moneyTransfers.getMoneyTransfers();
    assertThat(moneyTransferList).isNotNull();
    assertThat(moneyTransferList.size()).isEqualTo(5);
    MoneyTransfer moneyTransfer = moneyTransferList.get(3);
    assertThat(moneyTransfer.getReference()).isEqualTo("Meal and Entertainment");
    assertThat(moneyTransfer.getSourceAccount().getAccountID()).isEqualTo("SG01");
  }

  @Test
  public void testMarshalMoneyTransfers()
  {
    MoneyTransfers moneyTransfers = (MoneyTransfers) unmarshalXmlFile(new File("src/main/resources/xml/money-transfers.xml"));
    assertThat(moneyTransfers).isNotNull();
    List<MoneyTransfer> moneyTransferList = moneyTransfers.getMoneyTransfers();
    assertThat(marshalMoneyTransfersToXmlFile(moneyTransfers, new File("src/test/resources/xfer.xml"))).exists();
  }

  private Object unmarshalXmlFile(File xml)
  {
    Object moneyTransfers = null;
    try
    {
      Unmarshaller unmarshaller = JAXBContext.newInstance(MoneyTransfers.class).createUnmarshaller();
      moneyTransfers = unmarshaller.unmarshal(xml);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return moneyTransfers;
  }

  private File marshalMoneyTransfersToXmlFile(Object moneyTransfers, File xml)
  {
    try
    {
      Marshaller marshaller = JAXBContext.newInstance(moneyTransfers.getClass()).createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(moneyTransfers, xml);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return xml;
  }
}
