package com.mycompany.myapp.config.multitenant.Entity;

import com.mycompany.myapp.config.multitenant.TenantAware;
import com.mycompany.myapp.config.multitenant.TenantListener;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
@EntityListeners(TenantListener.class)
public abstract class BaseEntityId implements TenantAware, Serializable {

    @Column(name = "tenant_id")
    private String tenantId;

    @Id
    @Column(name = "id")
    private String id;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BaseEntityId() {}

    public BaseEntityId(String tenantId, String id) {
        this.tenantId = tenantId;
        this.id = id;
    }

    public BaseEntityId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Should be implemented by subclass.");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Should be implemented by subclass.");
    }

    @PrePersist
    private void onPrePersist() {
        id = UUID.randomUUID().toString();
    }

    @PreUpdate
    private void onPreUpdate() {}
}
