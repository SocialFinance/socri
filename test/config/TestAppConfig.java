package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Setup up the app configs (components, services, controllers, repositories)
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"controllers", "services"})
public class TestAppConfig {
}
