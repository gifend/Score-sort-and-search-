

package FinalWork;

public class StudentList {
    private Student[] stu=new Student[1000];//数组，只能由该类方法访问
    int studentcount = 0;
    void GetStudentScore(int i,String str){
        stu[i]=new Student();
        stu[i].Init(str);
    }   
    String StudentInfo(int i){//返回第i名学生的数据
        return stu[i].student_info();
    }
    Student get_the_student(int i){//返回该名学生
        return stu[i];
    }
    void StudentSort(int left, int right, Boolean decrease) {//decrease用于判断怎么排序
        if (decrease) {// 递减的排序
            if (left >= right)
                return;// 递归出口
            int i = left, j = right;
            Student base = stu[left];
            while (i < j) {
                while (i < j && stu[j].sum <= base.sum)// 保证左边要大于右边
                {
                    j--;
                }
                while (i < j && stu[i].sum >= base.sum) {
                    i++;
                }
                if (i < j) {
                    Student te = new Student();
                    te = stu[i];
                    stu[i] = stu[j];
                    stu[j] = te;
                }
            }
            stu[left] = stu[i];
            stu[i] = base;
            StudentSort(left, i - 1, decrease);
            StudentSort(i + 1, right, decrease);
        } else {//递增地排序
            if (left >= right)
                return;// 递归出口
            int i = left, j = right;
            Student base = stu[left];
            while (i < j) {
                while (i < j && stu[j].sum >= base.sum)// 保证左边要小于右边
                {
                    j--;
                }
                while (i < j && stu[i].sum <= base.sum) {
                    i++;
                }
                if (i < j) {
                    Student te = new Student();
                    te = stu[i];
                    stu[i] = stu[j];
                    stu[j] = te;
                }
            }
            stu[left] = stu[i];
            stu[i] = base;
            StudentSort(left, i - 1, decrease);
            StudentSort(i + 1, right, decrease);
        }
    }
    int search_student(String ID){
        for(int i=0;i<studentcount;i++){
                if(stu[i].id.equalsIgnoreCase(ID))
                return i;
            }
        return -1;
    }
}