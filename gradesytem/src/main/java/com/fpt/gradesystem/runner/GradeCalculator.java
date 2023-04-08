/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.runner;

import java.util.ArrayList;
import java.util.List;

public class GradeCalculator {

    public static void main(String[] args) {
        // Create a list of grades
        List<Grade> grades = new ArrayList<>();
        grades.add(new Grade("Participation", "Participation", 10.0, 3.0));
        grades.add(new Grade("Participation", "Total", 10.0, 6.0));
        grades.add(new Grade("Progress test", "Progress test 1", 10.0, 7.0));
        grades.add(new Grade("Progress test", "Progress test 2", 10.0, 5.0));
        grades.add(new Grade("Progress test", "Total", 20.0, 5.0));
        grades.add(new Grade("Mid-term test", "Mid-term test", 30.0, 9.0));
        grades.add(new Grade("Mid-term test", "Total", 30.0, 6.0));
        grades.add(new Grade("Final Exam", "FE: Listening", 10.0, 3.0));
        grades.add(new Grade("Final Exam", "FE: GVR", 30.0, 8.0));
        grades.add(new Grade("Final Exam", "Total", 40.0, 5.0));
        grades.add(new Grade("Final Exam Resit", "FE: Listening Resit", 10.0, 6.0));
        grades.add(new Grade("Final Exam Resit", "FE: GVR Resit", 30.0, 9.0));
        grades.add(new Grade("Final Exam Resit", "Total", 40.0, 4.0));

        // Calculate the accurate value for each "Total" column
        for (Grade grade : grades) {
            if (grade.getGradeItem().equals("Total")) {
                double totalWeight = 0.0;
                double totalValueWeighted = 0.0;
                String category = grade.getGradeCategory();
                for (Grade subGrade : grades) {
                    if (subGrade.getGradeCategory().equals(category) && !subGrade.getGradeItem().equals("Total")) {
                        totalWeight += subGrade.getWeight();
                        totalValueWeighted += subGrade.getWeight() * subGrade.getValue();
                    }
                }
                double accurateValue = totalValueWeighted / totalWeight;
                grade.setValue(accurateValue);
            }
        }

        // Print the final grades
        System.out.println("GRADE CATEGORY\tGRADE ITEM\tWEIGHT\tVALUE");
        for (Grade grade : grades) {
            System.out.println(grade.getGradeCategory() + "\t" + grade.getGradeItem() + "\t" + grade.getWeight() + " %" + "\t" + grade.getValue());
        }
    }
}

/*

Để tính giá trị chính xác cho cột "Total" trong mỗi "Grade Category", ta có thể sử dụng thuật toán sau:

Duyệt qua từng phần tử trong danh sách grades.
Với mỗi phần tử là "Total", tìm các phần tử khác trong cùng "Grade Category" (trừ "Total") và tính tổng trọng số và giá trị trọng số của chúng.
Tính giá trị chính xác cho "Total" bằng cách chia tổng giá trị trọng số cho tổng trọng số.
Cập nhật giá trị mới cho "Total".
 */
class Grade {

    private String gradeCategory;
    private String gradeItem;
    private double weight;
    private double value;

    public Grade(String gradeCategory, String gradeItem, double weight, double value) {
        this.gradeCategory = gradeCategory;
        this.gradeItem = gradeItem;
        this.weight = weight;
        this.value = value;
    }

    public String getGradeCategory() {
        return gradeCategory;
    }

    public void setGradeCategory(String gradeCategory) {
        this.gradeCategory = gradeCategory;
    }

    public String getGradeItem() {
        return gradeItem;
    }

    public void setGradeItem(String gradeItem) {
        this.gradeItem = gradeItem;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
