package com.qingcha.tech.doc4db.core.worker;

import com.qingcha.tech.doc4db.core.Doc4DatabaseConfiguration;
import com.qingcha.tech.doc4db.core.Doc4DatabaseModel;

import java.sql.Connection;

/**
 * @author qiqiang
 */
public abstract class GenerateWorker {
    private final Doc4DatabaseConfiguration doc4DatabaseConfiguration;

    protected GenerateWorker(Doc4DatabaseConfiguration doc4DatabaseConfiguration) {
        this.doc4DatabaseConfiguration = doc4DatabaseConfiguration;
    }

    public abstract Doc4DatabaseModel create() throws Exception;

    protected abstract Connection getConnection() throws Exception;

    public Doc4DatabaseConfiguration getDoc4DatabaseConfiguration() {
        return doc4DatabaseConfiguration;
    }
}