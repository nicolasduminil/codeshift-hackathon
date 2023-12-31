package fr.simplex_software.codeshift.hackathon.s3;

import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import jakarta.enterprise.context.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.eclipse.microprofile.config.inject.*;

import java.util.*;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.*;

@ApplicationScoped
public class S3ToSqsRoute extends RouteBuilder
{
  private static final String RANDOM = new Random().ints('a', 'z')
    .limit(5)
    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
    .toString();
  @ConfigProperty(name="sqs-queue-name")
  String queueName;
  String s3BucketName;

  public S3ToSqsRoute ()
  {
    AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().build();
    Optional<Bucket> optionalBucket = amazonS3.listBuckets().stream().filter(b -> b.getName().startsWith("mys3")).findFirst();
    if (optionalBucket.isPresent())
      this.s3BucketName = optionalBucket.get().getName();
    else
      this.s3BucketName = amazonS3.createBucket("mys3" + RANDOM).getName();
  }

  @Override
  public void configure()
  {
    from(aws2S3(s3BucketName).useDefaultCredentialsProvider(true))
      .log(LoggingLevel.INFO, "*** We got an XML message: ${body}")
      .split().tokenizeXML("moneyTransfer").streaming()
      .to(aws2Sqs(queueName).autoCreateQueue(true).useDefaultCredentialsProvider(true));
  }

  public String getS3BucketName()
  {
    return s3BucketName;
  }
}
