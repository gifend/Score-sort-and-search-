package FinalWork;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class MyFrame extends JFrame {
    JButton b1 = new JButton("导入");
    JButton b2 = new JButton("排序");
    JButton b3 = new JButton("退出");
    JButton b4 = new JButton("查找");

    JRadioButton jb1 = new JRadioButton("正序", true);
    JRadioButton jb2 = new JRadioButton("倒序", false);
    ButtonGroup group = new ButtonGroup();// 单选框

    Container root = this.getContentPane();
    JTextArea jta = new JTextArea("将在这里输出学生信息", 7, 42);
    JScrollPane jsp = new JScrollPane(jta);

    JLabel jl = new JLabel("学号:");
    JLabel titel=new JLabel("BY 吴松林");
    JTextField jtf = new JTextField();

    private boolean filein = false;

    public void Init() {
        // 设置文本域并添加滚动条
        this.setVisible(true);// 可视化
        this.setSize(600, 400);// 设置大小
        this.setLocation(300, 300);// 设置位置
        this.setTitle("学生成绩管理");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// 关闭方式
        root.setLayout(null);     
        jta.setLineWrap(true);// 自动换行
        jta.setBackground(Color.green);
        jsp.setBounds(100, 100, 400, 200);
        this.add(jsp);
        // 设置按钮
        titel.setBounds(250,20,120,30);
        b1.setBounds(40, 60, 60, 30);
        jb1.setBounds(200, 60, 50, 30);
        jb2.setBounds(250, 60, 50, 30);
        b2.setBounds(300, 60, 60, 30);
        b3.setBounds(500, 60, 60, 30);
        jl.setBounds(200, 315, 30, 20);
        jtf.setBounds(230, 315, 140, 20);
        b4.setBounds(375, 310, 60, 30);
        group.add(jb1);
        group.add(jb2);
        this.add(titel);
        this.add(b1);
        this.add(jb1);
        this.add(jb2);
        this.add(b2);
        this.add(b3);
        this.add(jl);
        this.add(jtf);
        this.add(b4);

    }

    public void Listen(StudentList stu) {// 监听
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);// 选择打开方式
                fc.showOpenDialog(null);
                File f = fc.getSelectedFile();
                stu.studentcount = 0;// 每次导入将学生数清零
                if (f != null) {
                    String fileopen = f.getPath().trim();
                    // StringBuffer buf=new StringBuffer();
                    BufferedReader br;
                    try {
                        filein = true;
                        PrintWriter pw = new PrintWriter(new FileWriter("studentTempFile.txt"));
                        br = new BufferedReader(new FileReader(fileopen));
                        String str = null;
                        str = br.readLine();
                        jta.setText(String.format("%-7s", "学号") + String.format("%-7s", "姓名")
                                + String.format("%-7s", "成绩1") + String.format("%-7s", "成绩2")
                                + String.format("%-7s", "成绩3") + String.format("%-7s", "总分"));
                        ScoreException.nonsence_num = 0;

                        while ((str = br.readLine()) != null) {
                            stu.GetStudentScore(stu.studentcount, str);
                            pw.println(str);
                            if (stu.get_the_student(stu.studentcount).nonsence_score()) {// 存在无意义分数
                                ScoreException.nonsence_num++;
                            } else {
                                jta.append("\n" + stu.StudentInfo(stu.studentcount));
                                stu.studentcount++;
                            }

                        }
                        if (ScoreException.nonsence_num > 0) {
                            throw new ScoreException();// 抛出异常
                        }
                        else{
                            JOptionPane.showMessageDialog(TestProg.my.b1, "成功导入全部学生信息！",
                            null, JOptionPane.INFORMATION_MESSAGE);// 调出弹窗
                        }
                        pw.flush();
                        pw.close();
                        filein = true;

                    } catch (ScoreException e1) {
                        JOptionPane.showMessageDialog(TestProg.my.b1, "有" + ScoreException.nonsence_num + "个学生的分数是异常的",
                                null, JOptionPane.INFORMATION_MESSAGE);// 调出弹窗
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }

                }
            }
        });
        // 对按钮二监听实现了导入功能
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (filein) {// 导入了文件
                    if (jb1.isSelected()) {// 正向排序
                        stu.StudentSort(0, stu.studentcount - 1, false);// 正序

                    } else {
                        stu.StudentSort(0, stu.studentcount - 1, true);// 逆序
                    }
                    jta.setText(String.format("%-7s", "学号") + String.format("%-7s", "姓名") + String.format("%-7s", "成绩1")
                            + String.format("%-7s", "成绩2") + String.format("%-7s", "成绩3")
                            + String.format("%-7s", "总分"));
                    for (int i = 0; i < stu.studentcount; i++) {
                        jta.append("\n" + stu.StudentInfo(i));
                    }
                } else {
                    jta.setText("没有导入文件进行排序！");
                }
            }

        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = null;
                str = jtf.getText();
                int i = stu.search_student(str);
                if (i != -1) {
                    jta.setText(String.format("%-7s", "学号") + String.format("%-7s", "姓名") + String.format("%-7s", "成绩1")
                            + String.format("%-7s", "成绩2") + String.format("%-7s", "成绩3")
                            + String.format("%-7s", "总分"));
                    jta.append("\n" + stu.StudentInfo(i));
                } else {
                    jta.setText("查询失败！没有该学号的学生");
                }

            }
        });
    }
}