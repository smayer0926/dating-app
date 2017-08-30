package models;

public class User {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String genderPreference;
    private int matchMinAge;
    private int matchMaxAge;
    private String zip;
    private String email;
    private String password;
    private String bio;

    public User(String name, int age, String gender, String genderPreference, int matchMinAge, int matchMaxAge, String zip, String email, String password, String bio){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.setGenderPreference(genderPreference);
        this.matchMinAge = matchMinAge;
        this.matchMaxAge = matchMaxAge;
        this.setZip(zip);
        this.setEmail(email);
        this.setPassword(password);
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMatchMinAge() {
        return matchMinAge;
    }

    public void setMatchMinAge(int matchMinAge) {
        this.matchMinAge = matchMinAge;
    }

    public int getMatchMaxAge() {
        return matchMaxAge;
    }

    public void setMatchMaxAge(int matchMaxAge) {
        this.matchMaxAge = matchMaxAge;
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (age != user.age) return false;
        if (matchMinAge != user.matchMinAge) return false;
        if (matchMaxAge != user.matchMaxAge) return false;
        if (!name.equals(user.name)) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (genderPreference != null ? !genderPreference.equals(user.genderPreference) : user.genderPreference != null)
            return false;
        if (zip != null ? !zip.equals(user.zip) : user.zip != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (genderPreference != null ? genderPreference.hashCode() : 0);
        result = 31 * result + matchMinAge;
        result = 31 * result + matchMaxAge;
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
