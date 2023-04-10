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
//----------------------------
//        courseRepository.getCourseAndGroup("HE171073")
//                .stream()
//                .map(ob -> ob[0] + ", " + ob[1] + ", " + ob[2] + ", " + ob[3] + ", " + ob[4])
//                .forEach(System.out::println);
//        HashMap<Object, Object> hm = new HashMap<>();
//
//        courseRepository.getCourseAndGroup("HE171073")
//                .stream()
//                .forEach(ob -> hm.put(ob[0], new Object[]{ob[0], ob[1], ob[2], ob[3], ob[4], ob[4]}));
//        System.out.println(hm.get("PRF192"));
//        for (Object key : hm.keySet()) {
//            Object[] values = (Object[]) hm.get(key);
//            System.out.println("Key: " + key);
//            System.out.println("Values: " + Arrays.toString(values));
//
//            for (int i = 0; i < values.length; i++) {
//                if (i == values.length - 1) {
//                    values[5] = 10;
//                }
//                System.out.println(values[i]);
//            }
//        }
//----------------------------------

//        gradeRepository.getGradeTotalByStudentId("HE171073").forEach(
//                g -> System.out.println(g.getGradeId() + " " + g.getGradeValue()));
//---------------------------------
        HashMap<Object, Object> hm = new HashMap<>();

        courseRepository.getCourseAndGroup("HE171073")
                .stream()
                .forEach(ob -> hm.put(ob[0], new Object[]{ob[0], ob[1], ob[2], ob[3], ob[4], ob[3], ob[4]}));

        for (Object key : hm.keySet()) {
            List<com.fpt.gradesystem.model.Grade> grade2;

            Object[] values = (Object[]) hm.get(key);
            double sum = 0;
            double feValue = -1;
            List<com.fpt.gradesystem.model.Grade> grades = gradeRepository.getGradeTotalByStudentIdCourseId("HE171073", key.toString());
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
            grade2 = grades;
            System.out.println(key);
            for (com.fpt.gradesystem.model.Grade grade : grade2) {
                System.out.println(grade.getGradeCategory().getGradeCategoryName() + grade.getGradeCategory().getGradeItemName() + " " + grade.getGradeValue());
            }
        }

//        for (Object key : hm.keySet()) {
//            Object[] values = (Object[]) hm.get(key);
//            List<com.fpt.gradesystem.model.Grade> grades = gradeRepository.getGradeTotalByStudentIdCourseId("HE171073", key.toString());
//            for (com.fpt.gradesystem.model.Grade g : grades) {
//                boolean isZezo = false;
//                double finalValue = 0;
//                if (g.getGradeCategory().getGradeItemName().equals("Total")) {
//
//                }
//
//            }
//
//        }

        for (Object key : hm.keySet()) {
            Object[] values = (Object[]) hm.get(key);
            System.out.println("Key: " + key);
            System.out.println("Values: " + Arrays.toString(values));
//            for (int i = 0; i < values.length; i++) {
//                System.out.println(values[i]);
//            }
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

 /*
PRF192
final: 0.0 0.0 Not Passed false
PRF192
final: 0.0 0.5 Not Passed false
PRF192
final: 0.0 0.5 Not Passed false
PRF192
final: 0.0 0.5 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.25 Not Passed false
PRF192
final: 0.0 1.8 Not Passed false
PRF192
final: 0.0 1.8 Not Passed false
PRF192
final: 0.0 3.4 Not Passed false
PRF192
final: 0.0 3.4 Not Passed false
PRF192
final: 2.0 4.0 Not Passed false
PRF192
final: 0.0 4.0 Not Passed false
PRF192
final: 10.0 6.4 Passed  false
DBI202
final: 0.0 0.0 Not Passed false
DBI202
final: 0.0 0.0 Not Passed false
DBI202
final: 0.0 0.0 Not Passed false
DBI202
final: 0.0 0.0 Not Passed false
DBI202
final: 0.0 0.0 Not Passed false
DBI202
final: 0.0 0.4 Not Passed false
DBI202
final: 0.0 0.4 Not Passed false
DBI202
final: 0.0 0.4 Not Passed false
DBI202
final: 0.0 1.2 Not Passed false
DBI202
final: 0.0 1.2 Not Passed false
DBI202
final: 0.0 2.4 Not Passed false
DBI202
final: 0.0 2.4 Not Passed false
DBI202
final: 0.0 3.9 Not Passed false
DBI202
final: 0.0 3.9 Not Passed false
DBI202
final: 2.0 4.5 Not Passed false
DBI202
final: 0.0 4.5 Not Passed false
DBI202
final: 9.0 6.6 Passed  false
PRJ301
final: 0.0 0.0 Not Passed false
PRJ301
final: 0.0 0.1 Not Passed true
PRJ301
final: 0.0 0.1 Not Passed false
PRJ301
final: 0.0 0.55 Not Passed true
PRJ301
final: 0.0 1.05 Not Passed true
PRJ301
final: 0.0 1.05 Not Passed false
PRJ301
final: 0.0 1.05 Not Passed true
PRJ301
final: 0.0 1.05 Not Passed false
PRJ301
final: 0.0 1.05 Not Passed false
PRJ301
final: 0.0 1.45 Not Passed true
PRJ301
final: 0.0 1.45 Not Passed false
PRJ301
final: 0.0 5.45 Not Passed true
PRJ301
final: 0.0 5.45 Not Passed false
PRJ301
final: 8.0 7.05 Not Passed true
PRJ301
final: 0.0 7.05 Not Passed false
PRJ301
final: 10.0 7.45 Not Passed true
CEA201
final: 0.0 0.0 Not Passed false
CEA201
final: 0.0 0.0 Not Passed false
CEA201
final: 0.0 1.65 Not Passed false
CEA201
final: 0.0 1.65 Not Passed false
CEA201
final: 0.0 1.65 Not Passed false
CEA201
final: 0.0 1.65 Not Passed false
CEA201
final: 0.0 1.65 Not Passed false
CEA201
final: 0.0 3.45 Not Passed false
CEA201
final: 0.0 3.45 Not Passed false
CEA201
final: 3.0 4.65 Not Passed false
CEA201
final: 0.0 4.65 Not Passed false
CEA201
final: 3.0 4.65 Not Passed false
NWC203c
final: 0.0 0.0 Not Passed false
NWC203c
final: 0.0 0.0 Not Passed false
NWC203c
final: 0.0 0.0 Not Passed false
NWC203c
final: 0.0 0.0 Not Passed false
NWC203c
final: 6.5 6.5 Passed  false
NWC203c
final: 4.0 4.0 Not Passed false
SWE201c
final: 0.0 0.0 Not Passed false
SWE201c
final: 0.0 1.8 Not Passed false
SWE201c
final: 0.0 1.8 Not Passed false
SWE201c
final: 0.0 3.6 Not Passed false
SWE201c
final: 0.0 3.6 Not Passed false
SWE201c
final: -1.0 3.2 Not Passed false
SWE201c
final: 0.0 3.2 Not Passed false
SWE201c
final: 0.0 2.8 Not Passed false
SSL101c
final: 0.0 0.0 Not Passed false
SSL101c
final: 0.0 0.0 Not Passed false
SSL101c
final: 0.0 0.0 Not Passed false
SSL101c
final: 0.0 0.0 Not Passed false
SSL101c
final: 6.0 6.0 Passed  false
SSL101c
final: 7.0 7.0 Passed  false
OSG202
final: 0.0 0.0 Not Passed false
OSG202
final: 0.0 0.0 Not Passed false
OSG202
final: 0.0 0.0 Not Passed false
OSG202
final: 0.0 0.0 Not Passed false
OSG202
final: 0.0 1.1 Not Passed false
OSG202
final: 0.0 1.1 Not Passed false
OSG202
final: 0.0 2.1 Not Passed false
OSG202
final: 0.0 2.1 Not Passed false
OSG202
final: 0.0 2.1 Not Passed false
OSG202
final: 0.0 3.9 Not Passed false
OSG202
final: 0.0 3.9 Not Passed false
OSG202
final: 10.0 7.9 Passed  false
OSG202
final: 0.0 7.9 Not Passed false
OSG202
final: 5.0 5.9 Passed  false
IOT102
final: 0.0 0.0 Not Passed false
IOT102
final: 0.0 0.4 Not Passed false
IOT102
final: 0.0 0.4 Not Passed false
IOT102
final: 0.0 0.4 Not Passed false
IOT102
final: 0.0 1.0 Not Passed false
IOT102
final: 0.0 1.0 Not Passed false
IOT102
final: 0.0 2.8 Not Passed false
IOT102
final: 0.0 2.8 Not Passed false
IOT102
final: 0.0 3.8 Not Passed false
IOT102
final: 0.0 3.8 Not Passed false
IOT102
final: 6.0 6.2 Passed  false
IOT102
final: 0.0 6.2 Not Passed false
IOT102
final: 9.0 7.4 Passed  false
MAS291
final: 0.0 0.0 Not Passed false
MAS291
final: 0.0 1.2 Not Passed true
MAS291
final: 0.0 1.2 Not Passed false
MAS291
final: 0.0 1.2 Not Passed false
MAS291
final: 0.0 2.2 Not Passed true
MAS291
final: 0.0 2.2 Not Passed false
MAS291
final: 0.0 2.2 Not Passed false
MAS291
final: 0.0 2.2 Not Passed false
MAS291
final: 0.0 4.3 Not Passed true
MAS291
final: 0.0 4.3 Not Passed false
MAS291
final: 4.0 5.7 Not Passed true
MAS291
final: 0.0 5.7 Not Passed false
MAS291
final: 0.0 5.7 Not Passed true
CSI104
final: 0.0 0.0 Not Passed false
CSI104
final: 0.0 0.0 Not Passed false
CSI104
final: 0.0 0.95 Not Passed false
CSI104
final: 0.0 0.95 Not Passed false
CSI104
final: 0.0 0.95 Not Passed false
CSI104
final: 0.0 1.55 Not Passed false
CSI104
final: 0.0 1.55 Not Passed false
CSI104
final: 0.0 1.55 Not Passed false
CSI104
final: 0.0 2.6 Not Passed false
CSI104
final: 0.0 2.6 Not Passed false
CSI104
final: 7.0 5.4 Passed  false
CSI104
final: 0.0 5.4 Not Passed false
CSI104
final: 10.0 6.6 Passed  false
VOV134
final: 0.0 0.0 Not Passed false
VOV134
final: 2.0 2.0 Not Passed false
VOV134
final: 0.0 2.0 Not Passed false
VOV134
final: 9.0 9.0 Passed  false
WED201c
final: 0.0 0.0 Not Passed false
WED201c
final: 0.0 0.0 Not Passed false
WED201c
final: 0.0 0.0 Not Passed false
WED201c
final: 0.0 0.0 Not Passed false
WED201c
final: 9.0 9.0 Passed  false
WED201c
final: 6.5 6.5 Passed  false
PRO192
final: 0.0 0.0 Not Passed false
PRO192
final: 0.0 0.0 Not Passed false
PRO192
final: 0.0 0.0 Not Passed false
PRO192
final: 0.0 0.0 Not Passed false
PRO192
final: 0.0 0.0 Not Passed false
PRO192
final: 0.0 0.0 Not Passed false
PRO192
final: 0.0 0.75 Not Passed false
PRO192
final: 0.0 0.75 Not Passed false
PRO192
final: 0.0 0.75 Not Passed false
PRO192
final: 0.0 1.5 Not Passed false
PRO192
final: 0.0 1.5 Not Passed false
PRO192
final: 0.0 2.9 Not Passed false
PRO192
final: 0.0 2.9 Not Passed false
PRO192
final: 0.0 5.0 Not Passed false
PRO192
final: 0.0 5.0 Not Passed false
PRO192
final: 2.0 5.6 Not Passed false
PRO192
final: 0.0 5.6 Not Passed false
PRO192
final: 8.0 7.4 Passed  false
VOV124
final: 0.0 0.0 Not Passed false
VOV124
final: 5.0 5.0 Passed  false
VOV124
final: 0.0 5.0 Not Passed false
VOV124
final: 3.0 3.0 Not Passed false
MAE101
final: 0.0 0.0 Not Passed false
MAE101
final: 0.0 0.0 Not Passed false
MAE101
final: 0.0 0.0 Not Passed false
MAE101
final: 0.0 1.41 Not Passed false
MAE101
final: 0.0 1.41 Not Passed false
MAE101
final: 0.0 1.41 Not Passed false
MAE101
final: 0.0 1.41 Not Passed false
MAE101
final: 0.0 3.42 Not Passed false
MAE101
final: 0.0 3.42 Not Passed false
MAE101
final: 2.0 4.22 Not Passed false
MAE101
final: 0.0 4.22 Not Passed false
MAE101
final: 9.0 7.02 Passed  false
JPD113
final: 0.0 0.0 Not Passed false
JPD113
final: 0.0 0.4 Not Passed false
JPD113
final: 0.0 0.4 Not Passed false
JPD113
final: 0.0 0.4 Not Passed false
JPD113
final: 0.0 1.4 Not Passed false
JPD113
final: 0.0 1.4 Not Passed false
JPD113
final: 0.0 3.8 Not Passed false
JPD113
final: 0.0 3.8 Not Passed false
JPD113
final: 0.0 3.8 Not Passed false
JPD113
final: 5.25 5.92 Passed  false
JPD113
final: 0.0 5.92 Not Passed false
JPD113
final: 0.0 5.92 Not Passed false
JPD113
final: 3.75 5.32 Not Passed false
JPD123
final: 0.0 0.0 Not Passed false
JPD123
final: 0.0 0.3 Not Passed false
JPD123
final: 0.0 0.3 Not Passed false
JPD123
final: 0.0 0.3 Not Passed false
JPD123
final: 0.0 1.5 Not Passed false
JPD123
final: 0.0 1.5 Not Passed false
JPD123
final: 0.0 4.2 Not Passed false
JPD123
final: 0.0 4.2 Not Passed false
JPD123
final: 0.0 4.2 Not Passed false
JPD123
final: 6.75 6.92 Passed  false
JPD123
final: 0.0 6.92 Not Passed false
JPD123
final: 0.0 6.92 Not Passed false
JPD123
final: 8.25 7.52 Passed  false
MAD101
final: 0.0 0.0 Not Passed false
MAD101
final: 0.0 0.0 Not Passed false
MAD101
final: 0.0 0.0 Not Passed false
MAD101
final: 0.0 2.31 Not Passed false
MAD101
final: 0.0 2.31 Not Passed false
MAD101
final: 0.0 2.31 Not Passed false
MAD101
final: 0.0 2.31 Not Passed false
MAD101
final: 0.0 4.41 Not Passed false
MAD101
final: 0.0 4.41 Not Passed false
MAD101
final: 10.0 8.41 Passed  false
MAD101
final: 0.0 8.41 Not Passed false
MAD101
final: 2.0 5.21 Not Passed false
SSG104
final: 0.0 0.0 Not Passed false
SSG104
final: 0.0 0.3 Not Passed true
SSG104
final: 0.0 0.3 Not Passed false
SSG104
final: 0.0 1.1 Not Passed true
SSG104
final: 0.0 1.1 Not Passed false
SSG104
final: 0.0 1.1 Not Passed false
SSG104
final: 0.0 1.1 Not Passed false
SSG104
final: 0.0 1.55 Not Passed true
SSG104
final: 0.0 1.55 Not Passed false
SSG104
final: 0.0 1.55 Not Passed false
SSG104
final: 0.0 2.55 Not Passed true
SSG104
final: 0.0 2.55 Not Passed false
SSG104
final: 0.0 2.55 Not Passed false
SSG104
final: 0.0 2.55 Not Passed false
SSG104
final: 0.0 3.96 Not Passed true
SSG104
final: 0.0 3.96 Not Passed false
SSG104
final: 2.0 4.36 Not Passed true
SSG104
final: 0.0 4.36 Not Passed false
SSG104
final: 8.0 5.56 Not Passed true
CSD201
final: 0.0 0.0 Not Passed false
CSD201
final: 0.0 0.0 Not Passed false
CSD201
final: 0.0 1.1 Not Passed false
CSD201
final: 0.0 1.1 Not Passed false
CSD201
final: 0.0 1.1 Not Passed false
CSD201
final: 0.0 2.8 Not Passed false
CSD201
final: 0.0 2.8 Not Passed false
CSD201
final: 0.0 4.3 Not Passed false
CSD201
final: 0.0 4.3 Not Passed false
CSD201
final: 3.0 5.2 Not Passed false
CSD201
final: 0.0 5.2 Not Passed false
CSD201
final: 8.0 6.7 Passed  false
 */
