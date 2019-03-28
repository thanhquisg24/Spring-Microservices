/**
 * @author qui
 *
 */
package com.qui.bootadmin;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableAdminServer
@EnableAutoConfiguration
public class AdminServer {

  public static void main(String[] args) {
    new SpringApplicationBuilder(AdminServer.class)
    .web(WebApplicationType.REACTIVE)
    .run(args);
}
}
