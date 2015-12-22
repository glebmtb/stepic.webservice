package ru.n5g.stepic.webservice.templater;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import static freemarker.template.Configuration.VERSION_2_3_23;

/**
 * @author Gleb Belyaev
 * @version 1.0
 * @since 22.12.15
 */
public class PageGenerator {
    private static PageGenerator ourInstance = new PageGenerator();
    private final Configuration cfg;

    private PageGenerator() {
        cfg = new Configuration(VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateLoader(new ClassTemplateLoader(getClass().getClassLoader(), ""));
    }

    public static PageGenerator getInstance() {
        return ourInstance;
    }

    public String getPage(String filename, Map<String, Object> pageVariables) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(filename);
            template.process(pageVariables, stream);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return stream.toString();
    }
}
