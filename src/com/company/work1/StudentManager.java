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
    SimpleDateFormat SIMPLE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");

    //static List<Student> students = new LinkedList<>();
    /*编号1.常量必须为全大写，错误编号1*/
    static List<Student> STUDENTS = new LinkedList<>();
    //类型7：该STUDENTS以全局变量的形式出现，这样就可以在程序运行之前就创建，我们可以直接向里面数据进行操作。好比数据库。
    /***
     * 添加学生的方法
     * @param ID
     * @param name
     * @param birDate
     * @param gender
     * @return 是否添加成功
     */
    //public boolean AddStudent(int ID, String name, String birDate,boolean gender){
    /*问题1.方法名命名错误*/

    public boolean addStudent(int ID, String name, String birDate,boolean gender){
        Student student = new Student();
        student.setID(ID);
        student.setName(name);
        student.setBirDate(birDate);
        student.setGender(gender);
        return STUDENTS.add(student);
    }

    /**
     * 获取所有的学生信息展示
     * @return
     */

    //public List<Student> ListALL() throws ParseException {
    /*问题1.方法名命名错误*/
    public List<Student> listALL() throws ParseException {
        sortByID(STUDENTS);
        /*类型5：不断地通过遍历选择students里面的值进行判断*/
        for (Student value : STUDENTS) {
            /*类型7：这里为了实现对性别男女与Boolean类型的转换就new了一个gender的String数据*/
            String gender;
            Student student = value;
            if (student.getGender()) {
                gender = "男";
            } else {
                gender = "女";
            }
            System.out.println(student.getID() + " " + student.getName() + " " +
                    SIMPLE_FORMAT.format(SIMPLE_FORMAT.parse(student.getBirDate())) + " " + gender);
        }
        return STUDENTS;
    }

    //public void SortByID(List<Student>list){
    /*问题1.方法名命名错误*/
    public void sortByID(List<Student>list){
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

    //public Student QueryStudent(String name){
    /*问题1.方法名命名错误*/
    public Student queryStudent(String name){
        Student student = new Student();
        student.setName("NOT FOUND");
        for (Student value : STUDENTS) {
            if(value.getName().equals(name)){
                student = value;
            }
        }

        if(student == null) {
            /*类型5：如果搜索出来的信息为空的话，我们就返回一个名字为“NOT FOUND”的新对象*/
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

    //public boolean DeleteStudent(String name){
    /*问题1.方法名命名错误*/
    public boolean deleteStudent(String name){
        Student student = queryStudent(name);
        if (!student.getName().equals("NOT FOUND")){
            return STUDENTS.remove(student);

        }else {
            return false;
        }
    }

    /**
     * 按照姓名更新学生的出生日期信息
     * @param name
     * @return
     */

    //public boolean UpdateStudent(String name,String birDate){
    /*问题1.方法名命名错误*/
    public boolean updateStudent(String name,String birDate){
        Student student = new Student();
        student = queryStudent(name);
        student.setBirDate(birDate);

        deleteStudent(name);
        return addStudent(student.getID(), student.getName(),
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
                /*类型4：输入1表示他选择插入数据*/
                System.out.println("请按照接下来的信息提示输入新加入学生的信息：\n"+"1.请输入学生的学号ID");
                int ID = scanner.nextInt();
                System.out.println("2.请输入学生的姓名信息：");
                String name = scanner.next();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                @SuppressWarnings("resource")
                Scanner input = new Scanner(System.in);
                System.out.println("3.请输入学生的生日信息：（注意格式为年-月-日 YYYY-MM-DD）");
                String date = null;
                /*类型7：flag的作用是表征数据当前是否已经选择到了退出，结束的按钮*/
                int flag = 0;
                while(flag == 0){
                    /*类型4:flag为0表示目前他还没有选择退出*/
                    try {
                        date = simpleDateFormat.format(simpleDateFormat.parse(input.nextLine()));

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
                addStudent(ID,name,date,gender);
            }

            if(choose == 2){
                /*类型4：输入1表示他选择查找数据*/
                System.out.println("接下来请输入你需要查找的学生的姓名:");
                String name = scanner.next();
                Student student = queryStudent(name);
                if(student.getName() == "NOT FOUND"){
                    System.out.println(student.getName());
                }else {
                    String gender;
                    if(student.getGender()){
                        gender = "男";
                    }else {
                        gender = "女";
                    }
                    System.out.println("您查找的学生信息如下：");
                    System.out.println("ID："+student.getID() + "  姓名：" + student.getName() + "  出生日期：" +
                            SIMPLE_FORMAT.format(SIMPLE_FORMAT.parse(student.getBirDate())) + " 性别：" + gender);
                }

            }

            if(choose == 3){
                /*类型4：输入1表示他选择删除数据*/
                System.out.println("接下来请输入你需要删除的学生的姓名:");
                String name = scanner.next();
                if(deleteStudent(name)){
                    System.out.println("删除成功！");
                }else {
                    System.out.println("删除失败，请核实您输入的名字是否合法");
                }
            }

            if(choose == 4){
                /*类型4：输入1表示他选择修改对应学生数据*/
                System.out.println("请输入你需要修改出生日期的学生的姓名");
                String name = scanner.next();

                @SuppressWarnings("resource")
                Scanner input = new Scanner(System.in);
                System.out.println("请输入学生的生日信息：（注意格式为年-月-日 YYYY-MM-DD）");
                String date = null;
                int flag = 0;
                while(flag == 0){
                    try {
                        date = SIMPLE_FORMAT.format(SIMPLE_FORMAT.parse(input.nextLine()));
                        flag = 1;
                    }catch (ParseException e){
                        System.out.println("注意您的输入格式！");
                    }
                }
                if(updateStudent(name, date)){
                    System.out.println("修改成功！\n");
                }else {
                    System.out.println("修改失败，请核实您输入的名字与出生日期是否符合要求");
                }
            }

            if(choose == 5){
                listALL();
            }

            if(choose == 6){
                return;
            }
        }//while循环的结束符号
    }//类型5：app函数的结束分号
}//类型6：整个大类结束符号
