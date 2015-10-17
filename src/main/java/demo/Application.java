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

        // prove that the setup worked
        for (Tenant t : dp.getTenants()) {
            System.out.println(t.getUrl());
        }
        
        for (Location l : dp.getLocations()) {
            System.out.println(l.getName());
        }
    }

}
