
import org.example.UserPortal.Main;
import org.example.UserPortal.payload.*;
import org.example.UserPortal.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UserControllerMutationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testRegisterStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO(1L, "John", 20,null,"john@example.com" ,"password", "10th", "address");
        String expectedResponse = "You have registered successfully";

        Mockito.when(userService.registerStudent(Mockito.any(StudentDTO.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/portal/student/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "id": 1,
                      "name": "John",
                      "age": 20,
                      "subjects": null,
                      "email": "john@example.com",
                      "password": "password",
                      "classLevel": "10th",
                      "address": "address"
                      
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testRegisterTeacher() throws Exception {
        String email = "teacher@example.com";
        TeacherDTO teacherDTO = new TeacherDTO(1L, "John Doe",2L,email,"address",53,12,"qualification","password");
        String expectedResponse = "You have registered successfully";

        Mockito.when(userService.registerTeacher(Mockito.any(TeacherDTO.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/portal/teacher/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "id": 1,
                      "name": "John Doe",
                       "sub_id":2,
                       "email":"teacher@example.com",
                       "address" : "address",
                       "age":"53",
                       "exp":12,
                       "qualification":"qualification",
                       "password":"password"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testLoginAdmin() throws Exception {
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO(1L, "admin@example.com", "password");
        String expectedResponse = "Login Successful";

        Mockito.when(userService.loginAdmin(Mockito.any(AdminLoginDTO.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/portal/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "id": 1,
                      "email": "admin@example.com",
                      "password": "password"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}

