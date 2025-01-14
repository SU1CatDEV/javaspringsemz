package su1cat.sem5.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import su1cat.sem5.aspects.TrackUserAspect;

@Configuration
@ComponentScan("su1cat.sem5")
@EnableAspectJAutoProxy
public class ProjectConfiguration {
    @Bean
    public TrackUserAspect aspect() {
        return new TrackUserAspect();
    }
}
