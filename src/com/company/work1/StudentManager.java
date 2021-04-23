package com.company.work1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author dongml
 * time 22/4/2021
 * description
 */
public class StudentManager {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    static List<Student> students = new LinkedList<>();

    /***
     * 添加学生的方法
     * @param ID
     * @param name
     * @param birDate
     * @param gender
     * @return 是否添加成功
     */
    public boolean AddStudent(int ID, String name, String birDate,boolean gender){
        Student student = new Student();
        student.setID(ID);
        student.setName(name);
        student.setBirDate(birDate);
        student.setGender(gender);
        return students.add(student);
    }

    /**
     * 获取所有的学生信息展示
     * @return
     */
    public List<Student> ListALL() throws ParseException {
        SortByID(students);

        for (Student value : students) {
            String gender;
            Student student = value;
            if (student.getGender()) {
                gender = "男";
            } else {
                gender = "女";
            }
            System.out.println(student.getID() + " " + student.getName() + " " +
                    simpleDateFormat.parse(student.getBirDate().toString()) + " " + gender);
        }
        return students;
    }
    public void SortByID(List<Student>list){
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getID(), o2.getID());
            }
        });
    }

    /**
     * 按照姓名查找学生
     * @param name
     * @return
     */
    public Student QueryStudent(String name){
        Student student = new Student();
        student.setName("NOT FOUND");
        for (Student value : students) {
            if(value.getName().equals(name)){
                student = value;
            }
        }

        if(student == null) { /*如果搜索出来的信息为空的话，我们就返回一个名字为“NOT FOUND”的新对象*/
            Student studentm = new Student();
            studentm.setName("NOT FOUND");
            return studentm;
        }else return student;
    }

    /**
     * 按照姓名删除学生
     * @param name
     * @return
     */
    public boolean DeleteStudent(String name){
        Student student = QueryStudent(name);
        if (!student.getName().equals("NOT FOUND")){
            return students.remove(student);

        }else {
            return false;
        }
    }

    /**
     * 按照姓名更新学生的出生日期信息
     * @param name
     * @return
     */
    public boolean UpdateStudent(String name,String birDate){
        Student student = new Student();
        student = QueryStudent(name);
        student.setBirDate(birDate);

        DeleteStudent(name);
        return AddStudent(student.getID(), student.getName(),
                student.getBirDate(),student.getGender());

    }

    /**
     * App方法用来实现界面信息
     */
    public void App() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        while(choose != 6){
            System.out.println("请按下数字后回车以选择操作：\n" +
                    "***********************************\n" +
                    "*                           1  插入                                  *\n" +
                    "*                           2  查找                                  *\n" +
                    "*                           3  删除                                  *\n" +
                    "*                           4  修改                                  *\n" +
                    "*                           5  输出                                  *\n" +
                    "*                           6  退出                                  *\n" +
                    "***********************************");
            choose = scanner.nextInt();
            if(choose == 1){
                System.out.println("请按照接下来的信息提示输入新加入学生的信息：\n"+"1.请输入学生的学号ID");
                int ID = scanner.nextInt();
                System.out.println("2.请输入学生的姓名信息：");
                String name = scanner.next();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                @SuppressWarnings("resource")
                Scanner input = new Scanner(System.in);
                System.out.println("3.请输入学生的生日信息：（注意格式为年-月-日 YYYY-MM-DD）");
                String date = null;
                int flag = 0;
                while(flag == 0){
                    try {
                        date = simpleDateFormat.parse(input.nextLine()).toString();
                        System.out.println(date);
                        flag = 1;
                    }catch (ParseException e){
                        System.out.println("注意您的输入格式！");
                    }
                }
                System.out.println("4.请输入学生的性别信息：(注意男生输入1即可，女生输入0即可)");
                int in = scanner.nextInt();
                while(!(in == 0 || in == 1)){
                    System.out.println("注意1表示男生，0表示女生。其他数字没有用");
                    in = scanner.nextInt();
                }
                boolean gender = false;
                if(in == 1){
                    gender = true;
                }
                AddStudent(ID,name,date,gender);
            }
            if(choose == 2){
                System.out.println("接下来请输入你需要查找的学生的姓名:");
                String name = scanner.next();
                Student student = QueryStudent(name);
                if(student.getName() == "NOT FOUND"){
                    System.out.println(student.getName());
                }else {
                    System.out.println(student);
                }
            }if(choose == 3){
                System.out.println("接下来请输入你需要删除的学生的姓名:");
                String name = scanner.next();
                if(DeleteStudent(name)){
                    System.out.println("删除成功！");
                }else {
                    System.out.println("删除失败，请核实您输入的名字是否合法");
                }
            }if(choose == 4){
                System.out.println("请输入你需要修改出生日期的学生的姓名");
                String name = scanner.next();

                @SuppressWarnings("resource")
                Scanner input = new Scanner(System.in);
                System.out.println("请输入学生的生日信息：（注意格式为年-月-日 YYYY-MM-DD）");
                String date = null;
                int flag = 0;
                while(flag == 0){
                    try {
                        date = simpleDateFormat.parse(input.nextLine()).toString();
                        flag = 1;
                    }catch (ParseException e){
                        System.out.println("注意您的输入格式！");
                    }
                }
                if(UpdateStudent(name, date)){
                    System.out.println("修改成功！\n");
                }else {
                    System.out.println("修改失败，请核实您输入的名字与出生日期是否符合要求");
                }
            }if(choose == 5){
                ListALL();
            }if(choose == 6){
                return;
            }
        }

    }
}
