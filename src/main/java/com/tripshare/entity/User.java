package com.tripshare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseEntity {

    private static final long serialVersionUID = -9141886501561769867L;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String encryptedPassword;
    private boolean active;
    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private UserProfile userProfile;

    @ManyToMany
    @JoinTable(name = "user_permissions")
    private List<Permission> permissions;

    @ManyToMany(
        fetch = FetchType.LAZY
    )
    @JoinTable(
        name = "trip_users",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "trip_id")}
    )
    private List<Trip> trips;

    public User() {
        this.active = true;
        this.permissions = new ArrayList<>();
    }

}
