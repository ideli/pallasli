package com.pallas.knowledge.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Knowledge implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private long parentId;

    /** persistent field */
    private String caption;

    /** persistent field */
    private String content;

    /** full constructor */
    public Knowledge(long parentId, String caption, String content) {
        this.parentId = parentId;
        this.caption = caption;
        this.content = content;
    }

    /** default constructor */
    public Knowledge() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
