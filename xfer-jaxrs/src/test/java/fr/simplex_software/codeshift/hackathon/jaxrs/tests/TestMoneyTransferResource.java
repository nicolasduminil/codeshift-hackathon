package fr.simplex_software.codeshift.hackathon.jaxrs.tests;

import fr.simplex_software.codeshift.hackathon.jaxrs.*;
import fr.simplex_software.codeshift.hackathon.model.*;
import io.quarkus.test.common.*;
import io.quarkus.test.common.http.*;
import io.quarkus.test.h2.*;
import io.quarkus.test.junit.*;
import io.restassured.common.mapper.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.apache.http.*;
import org.junit.jupiter.api.*;

import java.math.*;
import java.net.*;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMoneyTransferResource
{
  @TestHTTPEndpoint(MoneyTransferResource.class)
  @TestHTTPResource
  private URL url;
  private RequestSpecification requestSpecification;

  @BeforeAll
  public void beforeAll()
  {
    requestSpecification = given().baseUri(url.toString());
  }

  @AfterAll
  public void afterAll()
  {
    requestSpecification = null;
  }

  @Test
  @Order(10)
  public void testLivenessEndpoint()
  {
    requestSpecification.when().get("live").then().statusCode(200).body(is("Live"));
  }

  @Test
  @Order(20)
  public void testReadinessEndpoint()
  {
    requestSpecification.when().get("ready").then().statusCode(200).body(is("Ready"));
  }

  @Test
  @Order(30)
  public void testCreateMoneyTransferOrderEndpoint()
  {
    MoneyTransfer moneyTransfer = new MoneyTransfer("reference",
      new BankAccount(new Bank(List.of(new BankAddress("rue de Paris", "24",
        "BP 100", "Soisy sous Montmorency", "95230", "France")),
        "Société Générale"), "accountId", BankAccountType.CHECKING, "sortCode",
        "accountNumber", "transCode"),
      new BankAccount(new Bank(List.of(new BankAddress("Argyle Street", "201",
        "PO 258", "Glasgow", "G2 8BU", "UK")),
        "Bank of Scotland"), "accountId2", BankAccountType.CHECKING, "sortCode2",
        "accountNumber2", "transCode2"),
      new BigDecimal("100.00"));
    requestSpecification.when()
      .contentType(ContentType.JSON)
      .body(moneyTransfer)
      .post()
      .then()
      .statusCode(HttpStatus.SC_CREATED)
      .body(is(notNullValue()));
  }

  @Test
  @Order(40)
  public void testGetMoneyTransferOrdersEndpoint()
  {
    Response response = requestSpecification.when()
      .get()
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract().response();
    assertThat(response).isNotNull();
    List<MoneyTransfer> moneyTransfers = response.as(new TypeRef<List<MoneyTransfer>>() {});
    assertThat(moneyTransfers).isNotNull();
    assertThat(moneyTransfers).isNotNull();
    assertThat(moneyTransfers).hasSize(6);
    MoneyTransfer moneyTransfer = moneyTransfers.get(0);
    assertThat(moneyTransfer).isNotNull();
    assertThat(moneyTransfer.getReference()).isEqualTo("reference");
  }

  @Test
  @Order(50)
  public void testGetMoneyTransferOrderEndpoint()
  {
    Response response = requestSpecification.when()
      .pathParam("ref", "reference")
      .get("{ref}")
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract().response();
    assertThat(response).isNotNull();
    MoneyTransfer moneyTransfer = response.as(MoneyTransfer.class);
    assertThat(moneyTransfer).isNotNull();
    assertThat(moneyTransfer.getReference()).isEqualTo("reference");
  }

  @Test
  public void testDeleteMoneyTransferOrderEndpoint()
  {
    requestSpecification.when()
      .pathParam("ref", "reference")
      .delete("{ref}")
      .then()
      .statusCode(HttpStatus.SC_OK)
      .body(is(notNullValue()));
  }
}
