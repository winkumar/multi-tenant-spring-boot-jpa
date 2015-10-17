package demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import demo.locations.Location;
import demo.tenants.Tenant;

/**
 * Main application entry point
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("demo");

        DataProcessor dp = ctx.getBean(DataProcessor.class);

        System.out.println("----------------------------------------");
        System.out.println("          BEFORE CREATING GHI");
        System.out.println("----------------------------------------");
        // iterate the tenants and show location data from each database
        for (Tenant t : dp.getTenants()) {
            System.out.println(t.getUrl());
            CurrentTenantId.set(t.getId());
            for (Location l : dp.getLocations()) {
                System.out.println(l.getName());
            }
        }
        
        // Add a new record to the tenant table and do it again
        // This proves we can configure new tenants on the fly
        Tenant ghi = new Tenant("ghi", "jdbc:mysql://localhost:3306/tenant_ghi", "root", null);
        dp.save(ghi);
        
        System.out.println("----------------------------------------");
        System.out.println("          AFTER CREATING GHI");
        System.out.println("----------------------------------------");
        // iterate the tenants and show location data from each database
        for (Tenant t : dp.getTenants()) {
            System.out.println(t.getUrl());
            CurrentTenantId.set(t.getId());
            for (Location l : dp.getLocations()) {
                System.out.println(l.getName());
            }
        }
        System.out.println("----------------------------------------");
        
        ctx.close();

    }

}
