# Multi-tenancy in a Spring Data JPA World

The point of this repo is to demonstrate how to implement a Spring JPA
application that has a primary database which contains references to 
other databases for each customer.

## Environment Setup
I am assuming that a MySQL database is running on localhost, standard ports,
root user and no password.  Yes, I know this is insecure (in soooo many ways),
but I also know that not many people will go through the extra trouble of securing
a local dev setup.  If you are one of the few who actually sets things up correctly,
modify `setup.sql` accordingly.

Before running the application, you should load the setup script.

```
$> mysql -u root < setup.sql
```

## How it works
The primary database has a table for all the tenants.  Each tenant contains a record that
defines the database connection string, username, and password for that tenant's data.

Each tenant has their own database with identical schemas; in this case, everyone has 
a single table named `Location`.

The JPA repository and entities for the primary database are stored in a separate 
package from the repository and entities for the data stored in each tenant's database.
This separation allows the JPA annotations to do their work by scanning packages without
inadvertantly picking up invalid configurations.

## Where the complexity lies
There were two tricky parts to this whole things:

1. The persistence configuration
2. The datasource routing

### Persistence Configuration
As mentioned above, the first important part of making this work is ensuring that the repositories and entities are well separated from each other.

Each persistent unit needs to be configured separately.  This means that we have to be
careful to ensure that the Bean definitions do not conflict.  This can be most 
simply achieved by ensuring that the method names are different for each Bean definition.

One thing that bit me in the ass pretty hard is that my persistence configuration relied
on bean parameter injection, but I wasn't paying close attention; and let's just say
that the error messages that come out of Spring's error reporting system are slightly 
misleading.  Once I figured out the source of the problem, the solution was easy: 
specify a `@Qualifier` for the method parameter:

```
@Bean
public PlatformTransactionManager locationTransactionManager(
    @Qualifier("locationEntityManagerFactory") 
    EntityManagerFactory emf) {
        // code goes here 
    }
```

### DataSource Routing
The datasource for each of the tenants was the really tricky part of this whole thing.
This all would have been much simpler if I wanted to store the datasource configuration
somewhere that is loaded in its entirety at startup (I could have simply extended 
Spring's `AbstractRoutingDataSource`).  But I wanted to create a situation where I could
add new configurations to the database without needing to restart the application.

In the end, the problem is solved by implementing my own DataSource that finds the
right connection info based on the tenant's ID which is stored in a ThreadLocal variable.
The correct datasource is stored in a map.

The map is a custom implementation that performs a lookup from the `TenantRepository`
in the event that the DataSource is not already in the map.

This would have been simpler if I could have been able to autowire the `TenantRepository`
into the map implementation.  Unfortunately, autowiring caused Spring to choke on itself
during startup, so I had to resort to implementing `ApplicationContextAware` so that I
could lazy load the `TenantRepository` from the application context when needed.
 