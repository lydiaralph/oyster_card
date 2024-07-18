package lydia.ralph.api;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class OysterCardApplicationTest {

    @Autowired
    private OysterService oysterService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(oysterService).isNotNull();
        log.info("CONTEXT IS LOADED");
    }
}
