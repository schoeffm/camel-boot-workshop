package de.iteconomics.workshop.middleware;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

import static org.apache.camel.model.rest.RestBindingMode.json;

@Component
public class IntegrationRoute extends RouteBuilder {

    @Bean
    ServletRegistrationBean camelServlet() {
        ServletRegistrationBean mapping = new ServletRegistrationBean();
        mapping.setName("CamelServlet");
        mapping.setLoadOnStartup(1);
        mapping.setServlet(new CamelHttpTransportServlet());
        mapping.addUrlMappings("/api/*");
        return mapping;
    }

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .contextPath("/api")
                .bindingMode(json);

        rest("/customer")
                .consumes("application/json")
                .produces("application/json")
                .get("/").route()
                    .multicast(this::mergeResponses).parallelProcessing()
                    .to("direct:customerBackend", "direct:orderBackend")
                    .endRest()
                .get("/hello").route()
                    .log("${body}")
                    .endRest();

        from("direct:customerBackend")
                .setHeader(Exchange.HTTP_PATH, constant("/customers/1"))
                .to("http4://localhost:8081/?bridgeEndpoint=true")
                .unmarshal()
                .json(JsonLibrary.Jackson);

        from("direct:orderBackend")
                .setHeader(Exchange.HTTP_PATH, constant("/orders"))
                .to("http4://localhost:8081/?bridgeEndpoint=true")
                .unmarshal()
                .json(JsonLibrary.Jackson);
    }

    private Exchange mergeResponses(Exchange exchange1, Exchange exchange2) {
        if (exchange1 == null) return exchange2;
        if (exchange2 == null) return exchange1;

        LinkedHashMap<String, Object> body1 = (LinkedHashMap<String, Object>) exchange1.getIn().getBody();
        List<LinkedHashMap<String, Object>> body2 = (List<LinkedHashMap<String, Object>>) exchange2.getIn().getBody();

        body1.put("orders", body2);

        return exchange1;
    }
}
