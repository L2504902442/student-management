package raisetech.student.management.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student {

    @NotBlank
    @Pattern(regexp = "\\d+$")
    private  String studentId;

    @NotBlank
    private  String name;

    @NotBlank
    private  String kanaName;

    @NotBlank
    private  String nickname;

    @NotBlank
    @Email
    private  String email;

    @NotBlank
    private  String area;

    private  int age;

    @NotBlank
    private  String gender;

    private  String remark;
    private  boolean isDeleted;
}

