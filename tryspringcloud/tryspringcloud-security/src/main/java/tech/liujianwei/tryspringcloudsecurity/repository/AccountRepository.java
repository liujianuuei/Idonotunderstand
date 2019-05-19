package tech.liujianwei.tryspringcloudsecurity.repository;

import tech.liujianwei.tryspringcloudsecurity.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findOneByUsername(String username);
}
