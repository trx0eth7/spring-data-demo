package ru.trx.spring.data.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alexander Vasiliev
 */
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "sdd")
public final class SpringDataDemoProperties {
}
