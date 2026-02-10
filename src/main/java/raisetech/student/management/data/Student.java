package raisetech.student.management.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
    private String studentId;

    @NotBlank
    private String name;

    @NotBlank
    private String kanaName;

    private String nickname;

    @NotBlank
    @Email(message = "正しいメールアドレス形式で入力してください。")
    private String email;

    private String area;
    private Integer age;
    private String gender;
    private String remark;
    private Boolean deleted;
}