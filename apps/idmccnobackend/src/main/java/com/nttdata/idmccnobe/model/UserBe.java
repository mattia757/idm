package com.nttdata.idmccnobe.model;

import com.nttdata.idmccnobe.dto.UserBeDto;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author DelorenziVa
 */
@Entity
@Table(name = "CCNO_BE_USER")

public class UserBe implements Serializable {

    private static final long serialVersionUID = -2483101583616219116L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ROLE")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PASSWORD", length = 255)
    private String password;
    @JoinColumn(name = "CCNO_COOPERATIVA", referencedColumnName = "ID")
    @ManyToOne
    private Cooperativa cooperativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTOMER_DELETION_ENABLED")
    private int customerDeletionEnabled;

    public UserBe() {
    }

    public UserBe(Integer userId) {
        this.userId = userId;
    }

    public UserBe(Integer userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public UserBe(String username, String password, String role, Cooperativa coop, int customerDeletionEnabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.cooperativa = coop;
        this.customerDeletionEnabled = customerDeletionEnabled;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Cooperativa getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(Cooperativa cooperativa) {
        this.cooperativa = cooperativa;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public int getCustomerDeletionEnabled() {
        return customerDeletionEnabled;
    }

    public void setCustomerDeletionEnabled(int customerDeletionEnabled) {
        this.customerDeletionEnabled = customerDeletionEnabled;
    }
    
    public UserBeDto toDto(UserBe user) {
        UserBeDto userDto = new UserBeDto();
        userDto.setId(getUserId().toString());
        userDto.setUsername(getUsername());
        userDto.setCoop(getCooperativa().getRagioneSociale());
        userDto.setRole(getRole());
        userDto.setCustomerCancellation(String.valueOf(user.getCustomerDeletionEnabled()));
        return userDto;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserBe)) {
            return false;
        }
        UserBe other = (UserBe) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nttdata.idmccnobe.model.PdvUsers[ userId=" + userId + " ]";
    }
    
}
