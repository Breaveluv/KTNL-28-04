package com.example.ktnl2804.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "artifact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5,max = 150,message = "Tên phải từ 5-150")
    private String name;

    @NotBlank(message = " địa chỉ không đươc để trống")
    private String origin;

    @NotNull
    @PastOrPresent(message = "ngày không được là 1 ngày trong tương lai")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;


}
