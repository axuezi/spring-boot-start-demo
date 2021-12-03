package com.axue.code.generate;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateCode {

    private final static AutoGenerator mpg = new AutoGenerator();

    /**
     * 代码生成器 - 主方法
     */
    public GenerateCode(String url, String projectPath, String parent, String username, String password, String author) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String path = System.getProperty("user.dir");
        //生成文件的输出目录
        gc.setOutputDir(path + projectPath + "/src/main/java");
        //作者名称
        gc.setAuthor(author);
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + url + "?useUnicode=true&characterEncoding=" +
                "UTF-8&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parent);
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setEntity("entity.po");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        InjectionConfig cfg = getInjectionConfig();
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        templateConfig(mpg);
        strategyConfig(mpg);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    }

    /**
     * 执行生成文件
     */
    public void execute() {
        mpg.execute();
    }

    /**
     * 自定义配置
     */
    private static InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
    }

    /**
     * 配置模板
     *
     * @param mpg mpg
     */
    private static void templateConfig(AutoGenerator mpg) {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
    }

    /**
     * 策略配置
     *
     * @param mpg mpg
     */
    private static void strategyConfig(AutoGenerator mpg) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否开启lombok注解
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类控制器,没有就不用设置!
        // strategy.setSuperControllerClass("BaseController");
        // 写于父类中的公共字段
        // strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setSuperEntityColumns("id", "version", "create_time",
                "create_by", "update_time", "update_by", "logic_del");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        // 是否生成表字段映射的常量,在entity里面生成数据库映射字段常量
        // strategy.setEntityColumnConstant(true);
        // 数据库表前缀
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
    }

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            return scanner.next();
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
