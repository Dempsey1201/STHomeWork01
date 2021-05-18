package com.company.work1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import static com.company.work1.StudentManager.STUDENTS;

public class Main extends JFrame{

    /*类型1：全局变量应该全大写，错误代码1*/
    //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    //StudentManager studentManager = new StudentManager();
    StudentManager STUDENT_MANAGER = new StudentManager();
    public Main(){
        setLayout(new GridLayout(5,1));
        setTitle("stu info");
        setBounds(100,0,450,450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel jLabel = new JLabel();
        JButton button1 = new JButton("插入");
        JButton button2 = new JButton("查找");
        JButton button3 = new JButton("删除");
        JButton button4 = new JButton("输出");
        add(new JLabel("欢迎进入学生信息管理系统！",JLabel.CENTER));
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame();
                frame.setTitle("在这里输入新的学生信息");
                frame.setLayout(new GridLayout(6,1));
                frame.setBounds(100,0,700,200);
                frame.setVisible(true);
                JLabel labelinID = new JLabel("ID");
                JLabel labelinName  = new JLabel("Name");
                JLabel labelinBirDate = new JLabel("birDate(格式为yyyy-mm-dd)");
                JLabel labelinGender = new JLabel("gender");
                JTextField ID = new JTextField(20);
                JTextField Name = new JTextField(10);
                JTextField BirDate = new JTextField(10);
                JTextField Gender = new JTextField(1);
                frame.add(labelinID);
                frame.add(ID);
                frame.add(labelinName);
                frame.add(Name);
                frame.add(labelinBirDate);
                frame.add(BirDate);
                frame.add(labelinGender);
                frame.add(Gender);
                JButton button = new JButton("确认插入");
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Student student = new Student();
                        student.setID(Integer.parseInt(ID.getText()));
                        student.setName(Name.getText());
                        student.setBirDate(BirDate.getText());
                        if(Gender.getText().equals("男")){
                            student.setGender(true);
                        }else {
                            student.setGender(false);
                        }
                        System.out.println(STUDENT_MANAGER.addStudent(student.getID(),student.getName(),
                                student.getBirDate(),student.getGender()));
                        frame.dispose();
                    }
                });
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("输入您想要查找的学生的姓名");
                frame.setLayout(new GridLayout(2,1));
                frame.setBounds(100,0,700,100);
                frame.setVisible(true);
                JTextField name1 = new JTextField();
                JButton button = new JButton("确认搜索");
                frame.add(name1);
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.dispose();
                        String name = name1.getText();
                        Student st = STUDENT_MANAGER.queryStudent(name);
                        JFrame frame = new JFrame();
                        frame.setTitle("这是您查找的学生的信息");
                        frame.setLayout(new GridLayout(1,1));
                        frame.setBounds(100,0,700,100);
                        frame.setVisible(true);
                        String out = "您查找的学生信息：\nID                    姓名       出生日期       性别\n";
                        if(st.getName().equals("NOT FOUND")){
                            out = "没有这个学生哦！";
                        }else {
                            String gender;
                            if(st.getGender()){
                                gender = "男";
                            }else {
                                gender = "女";
                            }
                            out = out + st.getID() + "   " +st.getName() + "   "+
                                    st.getBirDate() + "       " +gender+"\n";
                        }
                        frame.add(new TextArea(out));
                    }
                });
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("输入您想要删除的学生的姓名");
                frame.setLayout(new GridLayout(2,1));
                frame.setBounds(100,0,700,100);
                frame.setVisible(true);
                JTextField name1 = new JTextField();
                JButton button = new JButton("确认搜索");
                frame.add(name1);
                frame.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.dispose();
                        JFrame frame1 = new JFrame("输入您想要删除的学生的姓名");
                        frame1.setLayout(new GridLayout(2,1));
                        frame1.setBounds(100,0,700,100);
                        frame1.setVisible(true);
                        String name = name1.getText();
                        JLabel jLabel1 = new JLabel();
                        if(STUDENT_MANAGER.deleteStudent(name))
                            jLabel1.setText("成功了！");
                        else jLabel1.setText("没有这个人！");
                        frame1.add(jLabel1);
                    }
                });
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                JFrame frame = new JFrame();
                frame.setTitle("这是所有的学生信息");
                frame.setLayout(new GridLayout(1,1));
                frame.setBounds(100,0,700,200);
                frame.setVisible(true);
                STUDENT_MANAGER.sortByID(STUDENTS);
                String out = "ID                    姓名       出生日期       性别\n";
                for (Student value:STUDENTS){
                    String gender;
                    if (value.getGender()){
                        gender = "男";
                    }else {
                        gender = "女";
                    }
                    out = out + value.getID() + "   " +value.getName() + "   "+
                            value.getBirDate() + "       " +gender+"\n";
                }
                frame.add(new TextArea(out));
            }
        });
    }

    public static void main(String[] args) {
	// write your code here
        /**
         * GUI方法
         */
        Main main = new Main();
    }

}



