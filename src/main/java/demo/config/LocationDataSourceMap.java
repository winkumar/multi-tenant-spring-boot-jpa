package demo.config;

import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import demo.tenants.Tenant;
import demo.tenants.TenantRepository;

@SuppressWarnings("serial")
@Component
public class LocationDataSourceMap extends HashMap<Object, Object> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object get(Object key) {
        Object value = super.get(key);
        if (value == null) {

            // Can't autowire this because it apparently creates a chicken/egg
            // problem during configuration.
            TenantRepository repo = applicationContext.getBean(TenantRepository.class);

            Tenant tenant = repo.findOne((String) key);
            if (tenant != null) {
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                dataSource.setUrl(tenant.getUrl());
                dataSource.setUsername(tenant.getUsername());
                dataSource.setPassword(tenant.getPassword());

                value = dataSource;
                super.put(key, value);
            }
        }
        return value;
    }

}
