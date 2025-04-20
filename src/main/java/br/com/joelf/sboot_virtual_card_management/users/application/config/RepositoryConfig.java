package br.com.joelf.sboot_virtual_card_management.users.application.config;

import br.com.joelf.sboot_virtual_card_management.users.application.infrastructure.database.JdbiUserRepository;
import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class RepositoryConfig {

    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);

        jdbiPlugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);

        return jdbi;
    }

    @Bean
    public JdbiPlugin jdbiPlugin() {
        return new SqlObjectPlugin();
    }

    @Bean
    public UserRepository userRepository(Jdbi jdbi) {
        return jdbi.onDemand(JdbiUserRepository.class);
    }
}
