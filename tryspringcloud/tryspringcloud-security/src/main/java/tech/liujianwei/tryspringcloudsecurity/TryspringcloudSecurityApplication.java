package tech.liujianwei.tryspringcloudsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringBootAuthenticationServiceApp
 *
 * @author Ertan Toker
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TryspringcloudSecurityApplication
{

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main( String[] args )
    {
        SpringApplication.run(TryspringcloudSecurityApplication.class, args);
    }
}
