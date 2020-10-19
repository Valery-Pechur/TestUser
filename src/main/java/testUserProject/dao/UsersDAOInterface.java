package testUserProject.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import testUserProject.model.Users;

import java.util.List;

@Component
public interface UsersDAOInterface {
    public int save (Users user);

    public int update (Users user);

    public Users get(Integer id);

    public int delete(Integer id);

    public List<Users> getAll();
}
