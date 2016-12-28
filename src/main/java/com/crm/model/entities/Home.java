package com.crm.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "home")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "html")
    @Lob
    private String html;
    @OneToMany(mappedBy = "home")
    private List<UserHome> usersHome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<UserHome> getUsersHome() {
        return usersHome;
    }

    public void setUsersHome(List<UserHome> usersHome) {
        this.usersHome = usersHome;
    }
}
