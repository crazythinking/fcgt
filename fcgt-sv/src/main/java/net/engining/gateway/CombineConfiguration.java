package net.engining.gateway;

import java.sql.Connection;

import javax.inject.Provider;
import javax.sql.DataSource;

import org.apache.catalina.filters.RemoteIpFilter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;

import io.swagger.annotations.ApiOperation;
import net.engining.control.api.FlowDispatcher;
import net.engining.control.core.dispatch.DetailedFlowListener;
import net.engining.control.core.dispatch.MDCFlowListener;
import net.engining.control.core.dispatch.SimpleFlowDispatcher;
import net.engining.control.sdk.FlowTransProcessorTemplate;
import net.engining.gateway.config.SyncSchedulingContextConfig;
import net.engining.pg.support.core.context.ApplicationContextHolder;
import net.engining.pg.web.filter.Log4jMappedDiagnosticContextFilter;
import net.engining.pg.web.handler.GlobalControllerExceptionHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableTransactionManagement(mode=AdviceMode.ASPECTJ)
//显式的指定具体的@Configuration类，不通过扫描的方式，更清晰；
@Import(value = {
		SyncSchedulingContextConfig.class
		})
@ComponentScan(basePackageClasses={GlobalControllerExceptionHandler.class})//这里显示的指定加载，避免其他不需要的组件被扫描
@EntityScan(basePackages = {
		"net.engining.gateway.entity"
	})
public class CombineConfiguration {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	protected static final String ScccAccountTransQueue = "sccc.account.trans.queue";
	
	/**
	 * ApplicationContext的静态辅助Bean，建议项目必须注入
	 * @return
	 */
	@Bean
	@Lazy(value=false)
	public ApplicationContextHolder applicationContextHolder(){
		return new ApplicationContextHolder();
	}
	
	/**
	 * 保留纯jdbc方式操作数据库的能力
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
	
	@Bean
    public com.querydsl.sql.Configuration querydslConfiguration() {
        SQLTemplates templates = MySQLTemplates.builder().build(); //change to your Templates
        
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        return configuration;
    }
	
	@Bean
    public SQLQueryFactory queryFactory() {
        Provider<Connection> provider = new SpringConnectionProvider(dataSource);
        return new SQLQueryFactory(querydslConfiguration(), provider);
    }
	
	/**
	 * 默认的Flow Trans Dispatcher
	 * @return
	 */
	@Bean
	public SimpleFlowDispatcher simpleFlowDispatcher(){
		return new SimpleFlowDispatcher();
	}
	
	@Bean
	public FlowTransProcessorTemplate flowTransProcessorTemplate(FlowDispatcher simpleFlowDispatcher){
		FlowTransProcessorTemplate flowTransProcessorTemplate = new FlowTransProcessorTemplate();
		flowTransProcessorTemplate.setTransactionProcessor(simpleFlowDispatcher);
		return flowTransProcessorTemplate;
	}
	
	@Bean
	public DetailedFlowListener detailedFlowListener(){
		DetailedFlowListener detailedFlowListener = new DetailedFlowListener();
		detailedFlowListener.setDumpProcedure(true);
		return detailedFlowListener;
	}
	
	@Bean
	public MDCFlowListener mdcFlowListener(){
		return new MDCFlowListener();
	}
	
	@Bean
	public Log4jMappedDiagnosticContextFilter log4jMappedDiagnosticContextFilter(){
		return new Log4jMappedDiagnosticContextFilter();
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasenames(
				"classpath:org/hibernate/validator/ValidationMessages"
				);
		reloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
		reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		reloadableResourceBundleMessageSource.setCacheSeconds(600);
		return reloadableResourceBundleMessageSource;

	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;

	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidator(validator());
		return methodValidationPostProcessor;

	}
	
	@Bean
	@ConditionalOnClass(value={RemoteIpFilter.class})
	public RemoteIpFilter remoteIpFilter(){
		return new RemoteIpFilter();
	}
	
	@Bean
	@Profile({"dev","test","sit","uat"})
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 RESTful APIs")
                .description("Web服务项目使用的基于Swagger2 RESTful APIs文档")
                .version("0.0.1")
                .build();
    }
    
    @Bean
	public Queue scccAccountTransQueue(){
		//定义为持久化队列
		return new Queue(ScccAccountTransQueue, true);
	}
}
