package p.vitaly.restexample;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"p.vitaly.restexample"})
public class TestApplicationConfig {
}
