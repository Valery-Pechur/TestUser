package testUserProject.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import testUserProject.model.Users;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDAOClass implements UsersDAOInterface {
    private static int countId;
    private JdbcTemplate jdbcTemplate;

    public UsersDAOClass(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Users user) {
        String sql = "INSERT INTO Users " +
                "(lastname, firstname, email, year_of_birth) " +
                "VALUES (?,?,?,?)";

        return jdbcTemplate.update(sql, new Object[] {user.getLastname(), user.getFirstname(), user.getEmail(), user.getYearOfBirth()});

    }

    @Override
    public int update(Users user) {
        String sql = "UPDATE Users " +
                "SET lastname=?, firstname=?, email=?, year_of_birth=? " +
                "WHERE id=?";
        return jdbcTemplate.update(sql, user.getLastname(), user.getFirstname(), user.getEmail(), user.getYearOfBirth(), user.getId());
    }

    @Override
    public Users get(Integer id) {
        String sql = "SELECT * FROM Users WHERE id="+id;
        ResultSetExtractor<Users> extractor=new ResultSetExtractor<Users>() {
            @Override
            public Users extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    Users user = new Users();
                    user.setId(id);
                    user.setLastname(rs.getString("lastname"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setEmail(rs.getString("email"));
                    user.setYearOfBirth(rs.getInt("year_of_birth"));
                    return user;
                }
                return null;
            }
        };
    return jdbcTemplate.query(sql, extractor);

    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Users WHERE id="+id;
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Users> getAll() {
        String sql = "SELECT * FROM Users";
        List <Users> usersList = jdbcTemplate.query(sql,
                new ResultSetExtractor<List<Users>>(){
                    @Override
                    public List<Users> extractData(ResultSet rs) throws SQLException, DataAccessException {

                        List<Users> list = new ArrayList<>();
                        while(rs.next()){
                            Users user = new Users();
                            user.setId(rs.getInt("id"));
                            user.setLastname(rs.getString("lastname"));
                            user.setFirstname(rs.getString("firstname"));
                            user.setEmail(rs.getString("email"));
                            user.setYearOfBirth(rs.getInt("year_of_birth"));
                            list.add(user);
                        }
                        return list;
                    }
                });
        return usersList;

    }
}
