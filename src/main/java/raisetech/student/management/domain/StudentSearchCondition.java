package raisetech.student.management.domain;

public class StudentSearchCondition {
    private String name;
    private String nickname;
    private String gender;
    private Integer age;
    private String email;
    private Boolean isDeleted;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }

    public boolean isEmpty() {
        return (name == null || name.isBlank()) &&
                (nickname == null || nickname.isBlank()) &&
                (gender == null || gender.isBlank()) &&
                age == null &&
                (email == null || email.isBlank()) &&
                isDeleted == null;
    }
}