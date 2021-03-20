/*
 * @Descripttion: 
 * @version: 
 * @Author: kalice
 * @Date: 2020-07-04 22:14:47
 * @LastEditors: kalice
 * @LastEditTime: 2021-03-20 16:14:23
 */


/*
 * 实现的功能：
 * 1.导入学生成绩信息，如果学生成绩没有错误弹出“成功导入！”，如果学生的成绩是错误的，
 * 会弹出弹窗提示一共有多少个学生的信息是错误的，错误的学生信息不会被导入。
 * 2.实现了对学生按成绩的高低从高到低进行排序，或者从低到高进行排序，排好序的学生信息会输出在
 * 带滚动条的文本域中
 * 3。实现了按ID搜索学生的功能，如果ID匹配会显示该该学生的信息，如果查找失败会提示没有该学生。
 */ 
package FinalWork;
public class TestProg {
    static  MyFrame my=new MyFrame();
    public static void main(String []args){
        StudentList stu=new StudentList();
        my.Init();//导入数据
        my.Listen(stu);// 开始监听      
        return ;
    }
}