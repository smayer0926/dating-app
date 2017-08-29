package models;

public class Authentication {

    private String username;
    private String password;
    private int id;

    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!username.equals(that.username)) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
