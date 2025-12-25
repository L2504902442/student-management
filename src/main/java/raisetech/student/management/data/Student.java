package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Schema(description = "受講生")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Student {
    @Schema(description = "受講生ID", example = "1")
    @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
    private String studentId;

    @Schema(description = "受講生の名前", example = "伊東 剛")
    @NotBlank
    private String name;

    @Schema(description = "受講生のフリガナ", example = "イトウツヨシ")
    @NotBlank
    private String kanaName;

    @Schema(description = "受講生のニックネーム", example = "つよ")
    @NotBlank
    private String nickname;

    @Schema(description = "受講生のメールアドレス", example = "tsuyoshi@example.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "受講生の住んでいる地域", example = "東京")
    @NotBlank
    private String area;

    @Schema(description = "受講生の年齢", example = "37")
    private int age;

    @Schema(description = "受講生の性別", example = "男")
    @NotBlank
    private String gender;

    @Schema(description = "備考", example = "特になし")
    private String remark;

    @Schema(description = "削除フラグ(true: 削除済み, false: 有効)", example = "false")
    private boolean isDeleted;
}