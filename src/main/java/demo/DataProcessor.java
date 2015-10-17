package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.tenants.Tenant;
import demo.tenants.TenantRepository;

@Service
public class DataProcessor {
    @Autowired
    private TenantRepository tenantRepository;

    public void setup() {
        Tenant abc = new Tenant("abc", "tenant_abc", "root", null);
        Tenant def = new Tenant("def", "tenant_def", "root", null);
        Tenant ghi = new Tenant("ghi", "tenant_ghi", "root", null);
        tenantRepository.save(abc);
        tenantRepository.save(def);
        tenantRepository.save(ghi);
    }

    public Iterable<Tenant> getTenants() {
        return tenantRepository.findAll();
    }
}
