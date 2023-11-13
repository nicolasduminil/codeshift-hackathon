package fr.simplex_software.codeshift.hackathon.jaxrs;

import jakarta.enterprise.context.*;
import jakarta.inject.*;
import org.eclipse.microprofile.health.*;

@Liveness
@ApplicationScoped
public class MoneyTransferLivenessCheck implements HealthCheck
{
  @Inject
  MoneyTransferResource moneyTransferResource;

  @Override
  public HealthCheckResponse call()
  {
    moneyTransferResource.testIfLive();
    return HealthCheckResponse.named("Liveness health check").up().build();
  }
}
