/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.runner;

import com.fpt.gradesystem.repository.AccountRepository;
import com.fpt.gradesystem.repository.CourseRepository;
import com.fpt.gradesystem.repository.CurriculumRepository;
import com.fpt.gradesystem.repository.GradeRepository;
import com.fpt.gradesystem.repository.SemesterRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SemesterRepository semesterRepository;

    @Override
    public void run(String... args) throws Exception {
//        for (Account allAccount : accountRepository.getAllAccounts()) {
//            System.out.println(allAccount.getStudent());
//        }
//----------------------------
//        curriculumRepository.findAll().forEach(System.out::println);
//        gradeRepository.findAll().forEach(g -> System.out.println(
//                g.getGradeCategory().getGradeCategoryName() + " " + g.getGradeCategory().getGradeItemName() + " " + g.getGradeValue())
//        );
//-----------------------------
//        courseRepository.getCourseByStudentId("HE171073").forEach(c -> {
//            System.out.println(c.getCourseId() + " " + c.getCourseName() + " " + c.getNoCredit() + " " + c.getTermNo());        
//        });
//-----------------------------
//        curriculumRepository.getCurriculumByStudentId("HE171073").getCurriculumId();
//        gradeRepository.getGradeByStudentIdCourseIdSemesterId("HE171073", "PRJ301", "SP2023").forEach(g -> System.out.println(
//                g.getGradeCategory().getGradeCategoryName() + " " + g.getGradeCategory().getGradeItemName() + " " + g.getGradeValue())
//        );
//-----------------------------
//        semesterRepository.findAll().forEach(s -> System.out.println(s.getSemesterId()));
//-----------------------------
//        courseRepository.getCourseByStudentIdSemesterId("HE171073", "SP2023").forEach(c -> {
//            System.out.println("CourseId: " + c.getCourseId() + " CourseName: " + c.getCourseName());
//        });

//----------------------------------

//        gradeRepository.getGradeTotalByStudentId("HE171073").forEach(
//                g -> System.out.println(g.getGradeId() + " " + g.getGradeValue()));
//---------------------------------
        HashMap<Object, Object> hm = new HashMap<>();

        courseRepository.getCourseAndGroup("HE171073")
                .stream()
                .forEach(ob -> hm.put(ob[0], new Object[]{ob[0], ob[1], ob[2], ob[3], ob[4], ob[3], ob[4]}));

        for (Object key : hm.keySet()) {

            Object[] values = (Object[]) hm.get(key);
            double sum = 0;
            double feValue = -1;
            List<com.fpt.gradesystem.model.Grade> grades = gradeRepository.getGradeTotalByStudentIdCourseId("HE171073", key.toString());
            if (!grades.isEmpty()) {
                for (com.fpt.gradesystem.model.Grade g : grades) {
                    if (g.getGradeCategory().getGradeItemName().equals("Total")) {

                        double totalWeight = 0;
                        double totalValue = 0;
                        String category = g.getGradeCategory().getGradeCategoryName();
                        for (com.fpt.gradesystem.model.Grade subGrade : grades) {
                            if (subGrade.getGradeCategory().getGradeCategoryName().equals(category) && !subGrade.getGradeCategory().getGradeItemName().equals("Total")) {
                                totalWeight += subGrade.getGradeCategory().getWeight();
                                totalValue += subGrade.getGradeCategory().getWeight() * subGrade.getGradeValue();
                            }

                        }
                        double accurateValue = totalValue / totalWeight;
                        g.setGradeValue(Math.round(accurateValue * 10.0) / 10.0);

                        sum += g.getGradeValue() * g.getGradeCategory().getWeight();

                        //nếu thi lại 
                        if (g.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam")) {
                            feValue = g.getGradeValue() * g.getGradeCategory().getWeight();

                        }
                        if (g.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam Resit")
                                && g.getGradeValue() > 0) {
                            sum = sum - feValue;

                        }
                    }
                    values[values.length - 2] = sum / 100;
                }
                System.out.println("-----------------------------");

                System.out.println(key);

                boolean isZero = false;
                double finalGrade = 0;

                for (com.fpt.gradesystem.model.Grade grade : grades) {
//                boolean isZero = false;
                    System.out.println(grade.getGradeCategory().getGradeCategoryName() + grade.getGradeCategory().getGradeItemName() + " " + grade.getGradeValue());
                    if (grade.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam") && grade.getGradeCategory().getGradeItemName().equalsIgnoreCase("Total")) {
                        finalGrade = grade.getGradeValue();
                    }
                    if (grade.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam Resit") && grade.getGradeCategory().getGradeItemName().equalsIgnoreCase("Total")
                            && grade.getGradeValue() > 0) {
                        finalGrade = grade.getGradeValue();

                    }

                    if ((!grade.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam Resit") || !grade.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam"))
                            && grade.getGradeValue() == 0) {
                        isZero = true;
                    }

                }
                System.out.println("finalGrade" + finalGrade);
                System.out.print(isZero);
                System.out.println(" sum: " + values[values.length - 2]);
                values[values.length - 1] = (finalGrade>=4 && !isZero && Double.parseDouble(values[values.length - 2].toString())>0?"Passed":"Not passed");
                System.out.println(values[values.length - 1]);
            }else if(key.equals("LAB211")){
                values[values.length - 1] = "Passed";
            }
        }

        for (Object key : hm.keySet()) {
            Object[] values = (Object[]) hm.get(key);
            System.out.println("Key: " + key);
            System.out.println("Values: " + Arrays.toString(values));
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + " ");
            }
        }

    }

}

/*
Key: PRF192
Values: [PRF192, Programming Fundamentals, 3, 1, Spring2022, 6.4, Passed ]
Key: DBI202
Values: [DBI202, Introduction to Databases, 3, 3, Fall2022, 6.6, Passed ]
Key: PRJ301
Values: [PRJ301, Java Web Application Development, 3, 4, Spring2023, 7.45, Not Passed]
Key: LAB211
Values: [LAB211, OOP with Java Lab, 3, 3, Fall2022, 3, Fall2022]
Key: CEA201
Values: [CEA201, Computer Organization and Architecture, 3, 1, Spring2022, 4.65, Not Passed]
Key: NWC203c
Values: [NWC203c, Computer Networking, 3, 2, Summer2022, 4.0, Not Passed]
Key: SWE201c
Values: [SWE201c, Introduction to Software Engineering, 3, 4, Spring2023, 2.8, Not Passed]
Key: SSL101c
Values: [SSL101c, Academic Skills for University Success, 3, 1, Spring2022, 7.0, Passed ]
Key: OSG202
Values: [OSG202, Operating Systems, 3, 2, Summer2022, 5.9, Passed ]
Key: IOT102
Values: [IOT102, Internet of Things, 3, 4, Spring2023, 7.4, Passed ]
Key: MAS291
Values: [MAS291, Statistics and Probability, 3, 4, Spring2023, 5.7, Not Passed]
Key: CSI104
Values: [CSI104, Introduction to Computer Science, 3, 1, Spring2022, 6.6, Passed ]
Key: VOV134
Values: [VOV134, Vovinam 3, 3, 0, Summer2022, 9.0, Passed ]
Key: WED201c
Values: [WED201c, Web Design, 3, 3, Fall2022, 6.5, Passed ]
Key: PRO192
Values: [PRO192, Object-Oriented Programming, 3, 2, Summer2022, 7.4, Passed ]
Key: VOV124
Values: [VOV124, Vovinam 2, 3, 0, Spring2022, 3.0, Not Passed]
Key: MAE101
Values: [MAE101, Mathematics for Engineering, 3, 1, Spring2022, 7.02, Passed ]
Key: JPD113
Values: [JPD113, Elementary Japanese 1-A1.1, 3, 3, Fall2022, 5.32, Not Passed]
Key: JPD123
Values: [JPD123, Elementary Japanese 1-A1.2, 3, 4, Spring2023, 7.52, Passed ]
Key: MAD101
Values: [MAD101, Discrete mathematics, 3, 2, Summer2022, 5.21, Not Passed]
Key: SSG104
Values: [SSG104, Communication and In-Group Working Skills, 3, 2, Summer2022, 5.56, Not Passed]
Key: CSD201
Values: [CSD201, Data Structures and Algorithms, 3, 3, Fall2022, 6.7, Passed ]

 */
