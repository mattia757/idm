package com.nttdata.idmccnobe.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FacchettiM
 */
@Entity
@Table(name = "USER_ATTRIBUTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAttribute.findAll", query = "SELECT u FROM UserAttribute u"),
    @NamedQuery(name = "UserAttribute.findByName", query = "SELECT u FROM UserAttribute u WHERE u.name = :name"),
    @NamedQuery(name = "UserAttribute.findByValue", query = "SELECT u FROM UserAttribute u WHERE u.value = :value"),
    @NamedQuery(name = "UserAttribute.findById", query = "SELECT u FROM UserAttribute u WHERE u.id = :id")})
public class UserAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "VALUE")
    private String value;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID")
    private String id;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UserEntity userId;

    public UserAttribute() {
    }

    public UserAttribute(String id) {
        this.id = id;
    }

    public UserAttribute(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAttribute)) {
            return false;
        }
        UserAttribute other = (UserAttribute) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nttdata.ccno.model.UserAttribute[ id=" + id + " ]";
    }
    
}
