package com.leliva.model;

import java.io.Serializable;

public interface IEntity<I extends Serializable> extends Serializable {
	
	Integer getVersion();
	/**
     * Property which represents id.
     */
    String P_ID = "id";

    /**
     * Get primary key.
     *
     * @return primary key
     */
    I getId();

    /**
     * Set primary key.
     *
     * @param id primary key
     */
    void setId(I id);	
}