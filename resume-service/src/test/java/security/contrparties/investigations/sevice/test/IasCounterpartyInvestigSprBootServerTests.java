package security.contrparties.investigations.sevice.test;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import security.contrparties.investigations.IasCounterpartyInvestigSprBootServer;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = IasCounterpartyInvestigSprBootServer.class)
//@WebAppConfiguration
//@ActiveProfiles("scratch")
class IasCounterpartyInvestigSprBootServerTests {

	@Test
	void contextLoads() { 	}

//	@Autowired
//	private WebApplicationContext context;

//	private MockMvc mvc;

	//@Before

	public void setUp() {

		//this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testHome() throws Exception {

	//	this.mvc.perform(get("/")).andExpect(status().isOk())
	//			.andExpect(content().string("Bath"));
	}

}
