package demo.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;

import demo.CurrentTenantId;

public class LocationRoutingDatasource extends AbstractDataSource {

    private Map<Object, Object> targetDataSources;

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }

    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return determineTargetDataSource().getConnection(username, password);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        return determineTargetDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return (iface.isInstance(this) || determineTargetDataSource().isWrapperFor(iface));
    }

    protected DataSource determineTargetDataSource() {
        Object lookupKey = CurrentTenantId.get();
        DataSource dataSource = (DataSource) this.targetDataSources.get(lookupKey);
        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        }
        return dataSource;
    }

}
