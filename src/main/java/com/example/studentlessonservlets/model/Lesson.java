package com.example.studentlessonservlets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    private int id;
    private String name;
    private Time duration;
    private String lecturerName;
    private double price;

}
