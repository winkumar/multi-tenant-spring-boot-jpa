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

        // iterate the tenants and show location data from each database
        for (Tenant t : dp.getTenants()) {
            System.out.println(t.getUrl());
            CurrentTenantId.set(t.getId());
            for (Location l : dp.getLocations()) {
                System.out.println(l.getName());
            }
        }
        
        ctx.close();

    }

}
