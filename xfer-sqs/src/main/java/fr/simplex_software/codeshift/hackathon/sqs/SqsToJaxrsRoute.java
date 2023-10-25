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
    from(aws2Sqs(queueName).useDefaultCredentialsProvider(true))
      .log (LoggingLevel.INFO, "*** We got a JMS message: ${body}")
      .unmarshal(jaxbDataFormat)
      .marshal().json(JsonLibrary.Jsonb)
      .log (LoggingLevel.INFO, "*** Marshalled to JSON: ${body}")
      .setHeader(Exchange.HTTP_METHOD, constant("POST"))
      .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
      .to(http(uri))
      .process(exchange -> log.info("*** A HTTP POST request having the payload above has been sent to {} and the response is: {}", uri, exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE)));
   }
}
