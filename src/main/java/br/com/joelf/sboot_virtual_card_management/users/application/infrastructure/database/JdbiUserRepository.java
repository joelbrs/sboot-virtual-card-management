package br.com.joelf.sboot_virtual_card_management.users.application.infrastructure.database;

import br.com.joelf.sboot_virtual_card_management.users.domain.entities.User;
import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.UUID;

@UseClasspathSqlLocator
public interface JdbiUserRepository extends UserRepository {

    @Override
    @SqlUpdate
    @GetGeneratedKeys
    UUID create(@BindBean User user);
}
