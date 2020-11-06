package bang.scad.p01;

public class TestModel {
    private final String firstname, lastname;

    public TestModel(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }
}
