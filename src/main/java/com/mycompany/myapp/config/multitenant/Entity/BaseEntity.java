package com.mycompany.myapp.config.multitenant.Entity;

import com.mycompany.myapp.config.multitenant.TenantAware;
import com.mycompany.myapp.config.multitenant.TenantListener;
import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
@EntityListeners(TenantListener.class)
public abstract class BaseEntity implements TenantAware, Serializable {

    @Column(name = "tenant_id")
    private String tenantId;

    public BaseEntity() {}

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BaseEntity(String tenantId) {
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
    private void onPrePersist() {}

    @PreUpdate
    private void onPreUpdate() {}
}
