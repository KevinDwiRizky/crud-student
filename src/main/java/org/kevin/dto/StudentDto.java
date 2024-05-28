package org.kevin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "nama harus lebih dari 2 huruf dan kurang dari 50 huruf")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;
}
