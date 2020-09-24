package com.qingcha.tech;

import com.qingcha.tech.doc4db.core.Doc4DatabaseConfiguration;
import com.qingcha.tech.doc4db.core.Doc4DatabaseGenerator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author qiqiang
 * @date 2020-09-23 7:48 下午
 */
@Mojo(name = "generate")
public class GenerateMojo extends AbstractMojo {
    private final Logger logger = LoggerFactory.getLogger(GenerateMojo.class);

    @Parameter(name = "host", defaultValue = "localhost")
    private String host;
    @Parameter(name = "user", required = true)
    private String user;
    @Parameter(name = "password", required = true)
    private String password;
    @Parameter(name = "database", required = true)
    private String database;
    @Parameter(name = "output", required = true)
    private String output;
    @Parameter(name = "templateFile")
    private String templateFile;

    @Parameter(defaultValue = "${project.basedir}", readonly = true)
    private File basedir;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        logger.info("正在生成文档");
        logger.info(basedir.toString());
        Doc4DatabaseConfiguration doc4DatabaseConfiguration = new Doc4DatabaseConfiguration(host, user, password, database);
        System.out.println(this.getClass().getClassLoader().getResource(""));
        doc4DatabaseConfiguration.setClassLoader(this.getClass().getClassLoader());
        doc4DatabaseConfiguration.setOutput(output);
        if (templateFile != null) {
            if (!templateFile.startsWith("/")) {
                templateFile = "/" + templateFile;
            }
            doc4DatabaseConfiguration.setTemplateFile(new File(basedir + templateFile));
        }
        Doc4DatabaseGenerator doc4DatabaseGenerator = new Doc4DatabaseGenerator(doc4DatabaseConfiguration);
        doc4DatabaseGenerator.setPrintLog(true);
        try {
            doc4DatabaseGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}