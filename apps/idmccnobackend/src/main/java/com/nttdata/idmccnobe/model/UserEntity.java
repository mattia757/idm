package com.nttdata.idmccnobe.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FacchettiM
 */
@Entity
@Table(name = "USER_ENTITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
    @NamedQuery(name = "UserEntity.findById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UserEntity.findByEmailConstraint", query = "SELECT u FROM UserEntity u WHERE u.emailConstraint = :emailConstraint"),
    @NamedQuery(name = "UserEntity.findByEmailVerified", query = "SELECT u FROM UserEntity u WHERE u.emailVerified = :emailVerified"),
    @NamedQuery(name = "UserEntity.findByEnabled", query = "SELECT u FROM UserEntity u WHERE u.enabled = :enabled"),
    @NamedQuery(name = "UserEntity.findByFederationLink", query = "SELECT u FROM UserEntity u WHERE u.federationLink = :federationLink"),
    @NamedQuery(name = "UserEntity.findByFirstName", query = "SELECT u FROM UserEntity u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UserEntity.findByLastName", query = "SELECT u FROM UserEntity u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UserEntity.findByRealmId", query = "SELECT u FROM UserEntity u WHERE u.realmId = :realmId"),
    @NamedQuery(name = "UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
    @NamedQuery(name = "UserEntity.findByCreatedTimestamp", query = "SELECT u FROM UserEntity u WHERE u.createdTimestamp = :createdTimestamp"),
    @NamedQuery(name = "UserEntity.findByServiceAccountClientLink", query = "SELECT u FROM UserEntity u WHERE u.serviceAccountClientLink = :serviceAccountClientLink"),
    @NamedQuery(name = "UserEntity.findByNotBefore", query = "SELECT u FROM UserEntity u WHERE u.notBefore = :notBefore")})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID")
    private String id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "EMAIL_CONSTRAINT")
    private String emailConstraint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMAIL_VERIFIED")
    private boolean emailVerified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENABLED")
    private boolean enabled;
    @Size(max = 255)
    @Column(name = "FEDERATION_LINK")
    private String federationLink;
    @Size(max = 255)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 255)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 255)
    @Column(name = "REALM_ID")
    private String realmId;
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "CREATED_TIMESTAMP")
    private BigInteger createdTimestamp;
    @Size(max = 36)
    @Column(name = "SERVICE_ACCOUNT_CLIENT_LINK")
    private String serviceAccountClientLink;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOT_BEFORE")
    private int notBefore;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserAttribute> userAttributeCollection;

    public UserEntity() {
    }

    public UserEntity(String id) {
        this.id = id;
    }

    public UserEntity(String id, boolean emailVerified, boolean enabled, int notBefore) {
        this.id = id;
        this.emailVerified = emailVerified;
        this.enabled = enabled;
        this.notBefore = notBefore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailConstraint() {
        return emailConstraint;
    }

    public void setEmailConstraint(String emailConstraint) {
        this.emailConstraint = emailConstraint;
    }

    public boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFederationLink() {
        return federationLink;
    }

    public void setFederationLink(String federationLink) {
        this.federationLink = federationLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigInteger getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(BigInteger createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getServiceAccountClientLink() {
        return serviceAccountClientLink;
    }

    public void setServiceAccountClientLink(String serviceAccountClientLink) {
        this.serviceAccountClientLink = serviceAccountClientLink;
    }

    public int getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(int notBefore) {
        this.notBefore = notBefore;
    }

    @XmlTransient
    public Collection<UserAttribute> getUserAttributeCollection() {
        return userAttributeCollection;
    }

    public void setUserAttributeCollection(Collection<UserAttribute> userAttributeCollection) {
        this.userAttributeCollection = userAttributeCollection;
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nttdata.ccno.model.UserEntity[ id=" + id + " ]";
    }
    
}
