package fr.simplex_software.codeshift.hackathon.jaxrs;

import fr.simplex_software.codeshift.hackathon.orm.*;
import fr.simplex_software.codeshift.hackathon.service.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import org.eclipse.microprofile.health.*;

import java.util.*;

@ApplicationScoped
@Readiness
public class MoneyTransferReadinessCheck implements HealthCheck
{
  @Inject
  MoneyTransferService moneyTransferService;

  @Override
  public HealthCheckResponse call()
  {
    HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse.named("Readiness health check");
    try
    {
      List<MoneyTransferEntity> moneyTransferOrders = moneyTransferService.getMoneyTransferOrders();
      healthCheckResponseBuilder.withData("Number of money transfer orders in the datastore", moneyTransferOrders.size()).up().build();
    }
    catch (IllegalStateException ex)
    {
      healthCheckResponseBuilder.down();
    }
    return healthCheckResponseBuilder.build();
  }
}
