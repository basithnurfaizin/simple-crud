import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.repository.UserRepository;
import com.nurfaizin.backend.service.UserService;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void shouldReturnUserResponse() throws NotFoundException {
        userService.getUser(1l);

    }


}
