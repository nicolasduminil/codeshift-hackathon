package fr.simplex_software.codeshift.hackathon.sqs;

import fr.simplex_software.codeshift.hackathon.model.*;
import jakarta.annotation.*;
import jakarta.enterprise.context.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.apache.camel.model.dataformat.*;
import org.eclipse.microprofile.config.inject.*;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.*;

@ApplicationScoped
public class SqsToJaxrsRoute extends RouteBuilder
{
  @ConfigProperty(name="sqs-queue-name")
  String queueName;
  @ConfigProperty(name="rest-uri")
  String uri;
  private final JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(true);

  @PostConstruct
  public void postConstruct()
  {
    jaxbDataFormat.setContextPath(MoneyTransfer.class.getPackageName());
  }

  @Override
  public void configure() throws Exception
  {
    from(aws2Sqs(queueName).useDefaultCredentialsProvider(true).region("eu-west-3"))
      .unmarshal(jaxbDataFormat)
      .marshal().json(JsonLibrary.Jsonb)
      .setHeader(Exchange.HTTP_METHOD, constant("POST"))
      .to(http(uri));
  }
}
