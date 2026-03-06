package raisetech.student.management.domain;

public class StudentSearchCondition {

    private String name;
    private String nickname;
    private String gender;
    private String email;
    private String area;
    private Integer age;
    private Boolean isDeleted;

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getArea() {
        return area;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}