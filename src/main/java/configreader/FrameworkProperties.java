package configreader;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:framework_config.properties")
public interface FrameworkProperties extends Config {

    @Key("foo.bar")
    String getFooBar();

    @Key("selenium.timeout")
    int getTimeout();
}
