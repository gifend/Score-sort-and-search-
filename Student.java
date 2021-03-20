
package FinalWork;
import java.util.*;
public class Student {
        String name,id;
        int[] score=new int[3];
        int sum=0;
        void Init(String str){
            StringTokenizer st=new StringTokenizer(str," /t");
            id=st.nextToken();
            name=st.nextToken();
            for(int i=0;i<3;i++){
                score[i]=Integer.parseInt(st.nextToken());
                sum+=score[i];
            }
        }
        String student_info() {
            return String.format("%-8s",id)+String.format("%-7s",name)+String.format("%-11s",score[0])+String.format("%-11s",score[1])
            +String.format("%-11s",score[2])+String.format("%-11s",sum);
        }
        boolean nonsence_score(){
            for(int i=0;i<3;i++){
                if(score[i]<0||score[i]>100)return true;//存在无意义分数
            }
            return false;
        }
    
}