package models;

public class User {
    private int id;
    private String name;
    private int age;
    private String gender;
    private int matchMinAge;
    private int matchMaxAge;

    public User(String name, int age, String gender, int matchMinAge, int matchMaxAge){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.matchMinAge = matchMinAge;
        this.matchMaxAge = matchMaxAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (matchMinAge != user.matchMinAge) return false;
        if (matchMaxAge != user.matchMaxAge) return false;
        if (!name.equals(user.name)) return false;
        return gender != null ? gender.equals(user.gender) : user.gender == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + matchMinAge;
        result = 31 * result + matchMaxAge;
        return result;
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
}
