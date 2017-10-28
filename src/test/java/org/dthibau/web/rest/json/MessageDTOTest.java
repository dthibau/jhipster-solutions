package org.dthibau.web.rest.json;


import static org.assertj.core.api.Assertions.assertThat;

import org.dthibau.ForumApp;
import org.dthibau.config.JacksonConfiguration;
import org.dthibau.service.dto.MessageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JacksonConfiguration.class)
@JsonTest()
public class MessageDTOTest {

	@Autowired
	private JacksonTester<MessageDTO> json;
	
	
	@Test
    public void testSerialize() throws Exception {
        MessageDTO root = new MessageDTO(1l, "Subject1","content1",null,null,null);
        MessageDTO child1 = new MessageDTO(11l, "Subject11","content11",null,null,null);
        MessageDTO child2 = new MessageDTO(12l, "Subject12","content12",null,null,null);
        MessageDTO child11 = new MessageDTO(111l, "Subject111","content111",null,null,null);
        MessageDTO child12 = new MessageDTO(112l, "Subject112","content112",null,null,null);
        MessageDTO child21 = new MessageDTO(121l, "Subject121","content121",null,null,null);
        
        child1.addResponses(child11);
        child1.addResponses(child12);
        child2.addResponses(child21);
        
        root.addResponses(child1);
        root.addResponses(child2);
        
        System.out.println(this.json.write(root));
        
//        // Assert against a `.json` file in the same package as the test
//        assertThat(this.json.write(details)).isEqualToJson("expected.json");
//        // Or use JSON path based assertions
        assertThat(this.json.write(root)).hasJsonPathMapValue("@.responses[1].responses[0]");
//        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
//                .isEqualTo("Honda");
    }
}
