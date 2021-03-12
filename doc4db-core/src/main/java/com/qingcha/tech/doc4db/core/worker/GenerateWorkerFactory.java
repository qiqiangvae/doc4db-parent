package com.qingcha.tech.doc4db.core.worker;

import com.qingcha.tech.doc4db.core.Doc4DatabaseConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author qiqiang
 */
public class GenerateWorkerFactory {
    private final Map<String, Class<? extends GenerateWorker>> defaultWorkers;
    private final Map<String, GenerateWorker> loadedWorkers;

    public GenerateWorkerFactory() {
        defaultWorkers = new HashMap<>();
        loadedWorkers = new HashMap<>();
        init();
    }

    private void init() {
        put(MysqlGenerateWorker.class);
        put(SqliteGenerateWorker.class);
        load();
    }

    private void put(Class<? extends GenerateWorker> clazz) {
        WorkerSchema schema = clazz.getDeclaredAnnotation(WorkerSchema.class);
        defaultWorkers.put(schema.value().toLowerCase(), clazz);
    }

    private void load() {
        ServiceLoader<GenerateWorker> serviceLoader = ServiceLoader.load(GenerateWorker.class);
        for (GenerateWorker generateWorker : serviceLoader) {
            WorkerSchema schema = generateWorker.getClass().getDeclaredAnnotation(WorkerSchema.class);
            if (schema == null) {
                throw new RuntimeException(generateWorker.getClass() + "未找到 com.qingcha.tech.doc4db.core.worker.WorkerSchema 注解");
            }
            loadedWorkers.put(schema.value(), generateWorker);
        }
    }

    public GenerateWorker create(Doc4DatabaseConfiguration configuration) {
        URI uri = URI.create(configuration.getUrl().substring(5));
        String schema = uri.getScheme().toLowerCase();
        GenerateWorker worker = loadedWorkers.get(schema);
        if (worker != null) {
            return worker;
        }
        Class<? extends GenerateWorker> aClass = defaultWorkers.get(schema);
        if (aClass == null) {
            throw new RuntimeException("未找到" + schema + "相关的 GenerateWorker");
        }
        try {
            return aClass.getDeclaredConstructor(Doc4DatabaseConfiguration.class).newInstance(configuration);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}