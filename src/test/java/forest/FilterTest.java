package forest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contactFilterTest1() throws Exception {

        this.mockMvc.perform(get("/hello/contacts").param("nameFilter","^.*[aei].*$"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RUBIROID")));

    }

    @Test
    public void contactFilterTest2() throws Exception {

        this.mockMvc.perform(get("/hello/contacts").param("nameFilter","^A.*$"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RUBIROID")));

    }

    @Test
    public void contactFilterTest3() throws Exception {

        this.mockMvc.perform(get("/hello/contacts").param("nameFilter","^.*$"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));

    }

    @Test
    public void contactFilterTest4() throws Exception {

        this.mockMvc.perform(get("/hello/contacts").param("nameFilter","^.*[0-9].*$"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RUBIROID")));

    }

    @Test
    public void contactFilterTest5() throws Exception {

        this.mockMvc.perform(get("/hello/contacts").param("nameFilter","^.*i{1,2}.*$"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RUBIROID")));

    }

}
