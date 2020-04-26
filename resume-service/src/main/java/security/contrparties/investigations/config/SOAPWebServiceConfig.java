package security.contrparties.investigations.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SOAPWebServiceConfig  extends WsConfigurerAdapter {

    @Bean
    ServletRegistrationBean servletRegistrationBean (ApplicationContext applicationContext) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        //  return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet,"/ias_proverka/*");
        return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet, "/ws/*");
    }

  /*  @Bean(name="CounterPartyControlService")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CounterPartyControlServiceImpl");
        wsdl11Definition.setLocationUri("/ias_proverka");
        wsdl11Definition.setTargetNamespace("http://spi2.ru/jaxws/datatypes");
        wsdl11Definition.setSchema(xsdSchema);

         return wsdl11Definition;
    }
*/

    @Bean(name ="CounterPartyControlService")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        //  wsdl11Definition.setWsdl(new ClassPathResource("/webapp/WEB-INF/wsdl/CounterpartyControlService.wsdl"));
        wsdl11Definition.setWsdl(new ClassPathResource("CounterpartyControlService.wsdl"));
        return wsdl11Definition;
    }

        @Bean
    public XsdSchema xsdSchema(){
        return new SimpleXsdSchema(new ClassPathResource("ias_data_types.xsd"));
    }

}
