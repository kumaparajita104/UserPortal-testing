
import org.example.UserPortal.entity.*;
import org.example.UserPortal.services.Impl.*;
import org.example.UserPortal.exception.ResourceNotFoundException;
import org.example.UserPortal.exception.UsernameNotFoundException;
import org.example.UserPortal.payload.*;
import org.example.UserPortal.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplMutationTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private final StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
    private final TeacherRepository teacherRepository = Mockito.mock(TeacherRepository.class);
    private final AdminRepository adminRepository = Mockito.mock(AdminRepository.class);

    private final SubjectRepository subjectRepository = Mockito.mock(SubjectRepository.class);
    private final ModelMapper modelMapper = new ModelMapper();

     private final UserServiceImpl userService = new UserServiceImpl(subjectRepository,studentRepository, teacherRepository, modelMapper, roleRepository, userRepository, adminRepository);

    @Test
    public void testRegisterStudent() {
        StudentDTO studentDTO = new StudentDTO(1L, "John", 20, null,"john@example.com", "password", "10th", "address");
        Role studentRole = new Role(1L, "Student");

        Mockito.when(roleRepository.findByName("Student")).thenReturn(Optional.of(studentRole));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        String response = userService.registerStudent(studentDTO);

        assertEquals("You have registered successfully", response);
    }

    @Test
    public void testLoginTeacher_ValidCredentials() {
        TeacherLoginDTO loginDTO = new TeacherLoginDTO("doe@example.com", "password");
        User user = new User();
        user.setPassword("password");
        Teacher teacher = new Teacher();
        teacher.setUser(user);

        Mockito.when(teacherRepository.getTeacherByEmail("doe@example.com")).thenReturn(Optional.of(teacher));

        String response = userService.loginTeacher(loginDTO);

        assertNotEquals("Login Successful", response);
    }

    @Test
    public void testLoginTeacher_InvalidPassword() {
        TeacherLoginDTO loginDTO = new TeacherLoginDTO("doe@example.com", "wrongpassword");
        User user = new User();
        user.setPassword("password");
        Teacher teacher = new Teacher();
        teacher.setUser(user);

        Mockito.when(teacherRepository.getTeacherByEmail("doe@example.com")).thenReturn(Optional.of(teacher));

        String response = userService.loginTeacher(loginDTO);

        assertEquals("Invalid credentials", response);
    }

    @Test
    public void testLoginAdmin_UserNotFound() {
        AdminLoginDTO loginDTO = new AdminLoginDTO(1L, "admin@example.com", "password");

        Mockito.when(adminRepository.findAdminById(1L)).thenThrow(new ResourceNotFoundException("admin", "id", 1L));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.loginAdmin(loginDTO);
        });

        assertEquals("admin not found with id : '1'", exception.getMessage());
    }
}

