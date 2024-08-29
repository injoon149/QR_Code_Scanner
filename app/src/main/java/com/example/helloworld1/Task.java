package com.example.helloworld1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;


    // id 없이 초기화하는 생성자
    public Task(String name, String category, boolean isCompleted, String deadline) {
        this.name = name;
        this.category = category;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
    }

    private String name;
    private String category;
    private boolean isCompleted;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    private String deadline;

    // 생성자와 getter, setter 메서드 작성
}
