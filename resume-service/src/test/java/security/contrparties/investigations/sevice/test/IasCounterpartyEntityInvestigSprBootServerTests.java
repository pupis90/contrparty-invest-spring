package security.contrparties.investigations.sevice.test;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import security.contrparties.investigations.IasCounterpartyInvestigSprBootServer;

@SpringBootTest(classes = IasCounterpartyInvestigSprBootServer.class)
//@WebAppConfiguration
//@ActiveProfiles("scratch")

/** Basic integration tests 		 */
class IasCounterpartyEntityInvestigSprBootServerTests {

    @Test
    void contextLoads() {
    }

//	@Autowired
//	private WebApplicationContext context;

//	private MockMvc mvc;

    //@Before

    public void setUp() {

        //this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {

    }


    @RepeatedTest(5)
    void repeatedTest() {

        System.out.println("Этот тест будет запущен пять раз. ");
    }


}
