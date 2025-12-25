package raisetech.student.management.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApplicationStatus {

  public int studentId;

  private String courseId;

  private String applicationStatus;
}
