package com.gmilani.liquibase.logging;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.lockservice.LockServiceFactory;
import liquibase.lockservice.StandardLockService;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.ops4j.pax.jdbc.hook.PreHook;
import org.osgi.service.component.annotations.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component(immediate = true, service = PreHook.class, property = "name=liquibaseMigrator")
public class Migrator implements PreHook {
    @Override
    public void prepare(DataSource ds) throws SQLException {
        try (Connection connection = ds.getConnection()) {
            this.prepare(connection);
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepare(Connection connection) throws LiquibaseException {
        Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        LockServiceFactory.getInstance().register(new StandardLockService());
        ClassLoader classLoader = this.getClass().getClassLoader();
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(classLoader);
        try (Liquibase liquibase = new Liquibase("migrations.xml", resourceAccessor, db)) {
            liquibase.update(new Contexts());
        }
    }
}
