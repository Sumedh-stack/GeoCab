package com.project.UberReviewService.modules.others;

import com.project.UberReviewService.modules.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course extends BaseModel {

    @ManyToMany
    List<Student> students = new ArrayList<>();
}
