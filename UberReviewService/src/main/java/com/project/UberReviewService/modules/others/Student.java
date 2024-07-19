package com.project.UberReviewService.modules.others;

import com.project.UberReviewService.modules.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends BaseModel {
    @ManyToMany
    @JoinTable(
            name = "course_student_id",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    List<Course> courses = new ArrayList<>();
}
