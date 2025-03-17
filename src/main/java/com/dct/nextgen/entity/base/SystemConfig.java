package com.dct.nextgen.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Store custom system configurations as JSON, such as optional notification settings
 * @author thoaidc
 */
@Entity
@Table(name = "system_config")
@SuppressWarnings("unused")
public class SystemConfig extends AbstractAuditingEntity {

    @Column(name = "code", nullable = false, length = 45, unique = true)
    private String code; // This code is used to reference or update the content of the configuration

    @Column(name = "content", nullable = false)
    private String content; // The JSON string represents the content of the configuration for a feature

    @Column(name = "description")
    private String description;

    @Column(name = "enabled", nullable = false)
    private Integer enabled; // The activation status of the configuration: 1 - active, 0 - inactive

    public SystemConfig() {}

    public SystemConfig(String code, String content, String description, Integer enabled) {
        this.code = code;
        this.content = content;
        this.description = description;
        this.enabled = enabled;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getEnabled() {
        return enabled == 1;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
