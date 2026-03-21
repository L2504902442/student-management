package raisetech.student.management.data;

import java.util.List;

public class Student {

    private String studentId;
    private String name;
    private String kanaName;   // 追加
    private String nickname;
    private String email;
    private String area;
    private String age;        // String 型に統一
    private String gender;
    private String remark;     // 追加
    private String isDeleted;  // String 型に統一

    private List<StudentCourse> courses; // 紐づくコース情報

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

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

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public String getIsDeleted() { return isDeleted; }
    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }

    public List<StudentCourse> getCourses() { return courses; }
    public void setCourses(List<StudentCourse> courses) { this.courses = courses; }
}