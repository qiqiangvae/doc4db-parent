package com.qingcha.tech.doc4db.core;

import com.qingcha.tech.doc4db.core.worker.GenerateWorker;
import com.qingcha.tech.doc4db.core.worker.GenerateWorkerFactory;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author qiqiang
 * @date 2020-09-23 7:11 下午
 */
public class Doc4DatabaseGenerator implements Generator {
    private final Logger logger = LoggerFactory.getLogger(Doc4DatabaseGenerator.class);
    private final GenerateWorker generateWorker;
    private final Doc4DatabaseConfiguration doc4DatabaseConfiguration;

    private File templateFile;
    private String output;


    public Doc4DatabaseGenerator(Doc4DatabaseConfiguration doc4DatabaseConfiguration) {
        this.doc4DatabaseConfiguration = doc4DatabaseConfiguration;
        generateWorker = findWorker();
    }

    private GenerateWorker findWorker() {
        return new GenerateWorkerFactory().create(doc4DatabaseConfiguration);
    }


    @Override
    public void generate() throws Exception {
        Doc4DatabaseModel templateModel = generateWorker.create();
        print(templateModel);
    }

    private void print(Doc4DatabaseModel templateModel) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        Template template;
        if (templateFile == null) {
            configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "");
            template = configuration.getTemplate("defaultDocTemplate.ftl");
            logger.info("使用默认模版 defaultDocTemplate.ftl");
        } else {
            configuration.setTemplateLoader(new FileTemplateLoader(new File(templateFile.getParent())));
            template = configuration.getTemplate(templateFile.getName());
            logger.info("templateFile:[{}]", templateFile);
        }
        File file = new File(output);
        Writer out = new FileWriter(file);
        //调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
        template.process(templateModel, out);
        logger.info("文档生成完成:[{}]", file.getPath());
        //关闭writer对象。
        out.flush();
        out.close();
    }

    public void configFile(File templateFile, String output) {
        this.templateFile = templateFile;
        this.output = output;
    }
}