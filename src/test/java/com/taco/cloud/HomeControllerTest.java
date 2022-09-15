package com.taco.cloud;

import com.taco.cloud.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //check because with 2.7.3 not working
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test //other library for this as well in 2.7.3
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(200))
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome")
                ));
    }

}
