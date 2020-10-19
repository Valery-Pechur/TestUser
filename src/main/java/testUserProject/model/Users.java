package testUserProject.model;

public class Users {
    private Integer id;
    private String lastname;
    private String firstname;
    private String email;
    private Integer yearOfBirth;

    public Users() {
    }

    public Users(String lastname, String firstname, String email, Integer yearOfBirth) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.yearOfBirth= yearOfBirth;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getId() { return id;}

    public void setId(Integer id) { this.id=id;}
}
