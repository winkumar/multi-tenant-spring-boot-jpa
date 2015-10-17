package demo.tenants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tenant {
    @Id
    private String id;
    private String url;
    private String username;
    
    @Column(name="pwd")
    private String password;

    public Tenant() {
        super();
    }

    public Tenant(String id, String url, String username, String password) {
        super();
        this.id = id;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
