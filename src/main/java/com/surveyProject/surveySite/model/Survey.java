package com.surveyProject.surveySite.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_group")
public class Survey {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<User> respondents;
}
