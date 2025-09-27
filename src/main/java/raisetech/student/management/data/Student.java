package raisetech.student.management.data;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student {

    private  String studentId;
    private  String name;
    private  String kanaName;
    private  String nickname;
    private  String email;
    private  String area;
    private  int age;
    private  String gender;
    private  String remark;
    private  boolean isDeleted;

    private String id;

    public String getId() {
        return studentId; // id の getter では studentId を返す
    }

    public void setId(String id) {
        this.studentId = id; // setter も studentId に値を入れる
    }
}

