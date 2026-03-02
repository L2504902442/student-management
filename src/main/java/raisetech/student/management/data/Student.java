package raisetech.student.management.data;

public class Student {
    private Integer studentId;
    private String name;
    private String kanaName;
    private String nickname;
    private String email;
    private String area;
    private Integer age;
    private String gender;
    private Boolean isDeleted;

    // ----- getter / setter -----
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getKanaName() { return kanaName; }
    public void setKanaName(String kanaName) { this.kanaName = kanaName; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}