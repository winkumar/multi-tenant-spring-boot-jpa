package demo.tenants;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for accessing tenant data
 */
public interface TenantRepository extends CrudRepository<Tenant, String> {

}
