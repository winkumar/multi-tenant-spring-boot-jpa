package demo.tenants;

import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, String> {

}
