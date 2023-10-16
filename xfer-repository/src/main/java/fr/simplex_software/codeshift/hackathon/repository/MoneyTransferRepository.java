package fr.simplex_software.codeshift.hackathon.repository;

import fr.simplex_software.codeshift.hackathon.orm.*;
import io.quarkus.hibernate.orm.panache.*;
import jakarta.enterprise.context.*;

@ApplicationScoped
public class MoneyTransferRepository implements PanacheRepository<MoneyTransferEntity>
{
}
