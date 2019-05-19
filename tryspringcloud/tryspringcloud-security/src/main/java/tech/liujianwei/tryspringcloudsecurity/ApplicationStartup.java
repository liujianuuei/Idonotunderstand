package tech.liujianwei.tryspringcloudsecurity;

import tech.liujianwei.tryspringcloudsecurity.model.Account;
import tech.liujianwei.tryspringcloudsecurity.repository.AccountRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public ApplicationStartup(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        accountRepository.deleteAll();
        Account account = new Account();
        account.setFirstname("Demo");
        account.setLastname("Account");
        account.setUsername("DemoAccount");
        account.setPassword(passwordEncoder.encode("DemoPassword"));

        accountRepository.save(account);
    }
}
