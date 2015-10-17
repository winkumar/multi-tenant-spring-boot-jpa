package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.locations.Location;
import demo.locations.LocationRepository;
import demo.tenants.Tenant;
import demo.tenants.TenantRepository;

@Service
public class DataProcessor {
    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private LocationRepository locationRepository;

    public Iterable<Tenant> getTenants() {
        return tenantRepository.findAll();
    }
    
    public void save(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    public Iterable<Location> getLocations() {
        return locationRepository.findAll();
    }
}
