
package com.example.demo;

public class Staffuser {
    private String name;
    private Long id;
    // private String username;
    private String password;
    private String location;

    // Constructors, getters, and setters
    public Staffuser() {

    }

    public Staffuser(String name, String username, String password, String location) {
        this.name = name;
        // this.username = username;
        this.password = password;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public String getUsername() {
    // return username;
    // }

    // public void setUsername(String username) {
    // this.username = username;
    // }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
