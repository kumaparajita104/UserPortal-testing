import org.example.UserPortal.*;
import org.example.UserPortal.payload.TeacherDTO;
import org.example.UserPortal.payload.TeacherDisplayDTO;
import org.example.UserPortal.services.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class TeacherControllerMutationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    public void testGetTeacherByEmail() throws Exception {
        // Mutation killed: Returns a teacher even when the email doesn't exist
        String email = "teacher@example.com";
        TeacherDisplayDTO dto = new TeacherDisplayDTO(1L, "John Doe", email,0L);

        Mockito.when(teacherService.getTeacherByEmail(email)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/" + email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email));

        // Mutation killed: Returns incorrect status (e.g., 404 instead of 200)
    }

    @Test
    public void testGetTeacherById() throws Exception {
        // Mutation killed: Returns a teacher even when the ID doesn't exist
        Long id = 1L;
        TeacherDisplayDTO dto = new TeacherDisplayDTO(id, "John Doe", "teacher@example.com",0L);

        Mockito.when(teacherService.getInfo(id)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void testAddSubject() throws Exception {
        // Mutation killed: Fails to validate teacher and subject IDs
        Long teacherId = 1L;
        Long subjectId = 2L;
        TeacherDisplayDTO dto = new TeacherDisplayDTO(teacherId, "John Doe", "teacher@example.com", subjectId);

        Mockito.when(teacherService.addSubject(teacherId, subjectId)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher/" + teacherId + "/" + subjectId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(teacherId));
    }
}

