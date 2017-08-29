package models;

public class Authentication {

    private String email;
    private String password;
    private int id;

    public Authentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authentication that = (Authentication) o;

        if (id != that.id) return false;
        if (!email.equals(that.email)) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
