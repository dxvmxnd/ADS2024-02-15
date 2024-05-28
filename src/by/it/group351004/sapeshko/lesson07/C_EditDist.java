package by.it.group351004.sapeshko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/
Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.
Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки
    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,
    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,
    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,
    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int m = one.length();
        int n = two.length();

        int[][] d = new int[m + 1][n + 1];

        for(int i = 0; i<=m;i++){
            for(int j = 0; j<=n;j++){
                d[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int j = 0; j<=n;j++) {
            d[0][j] = j;
        }
        for(int j = 0; j<=m;j++){
            d[j][0] = j;
        }

        for(int i = 1; i<=m;i++){
            for(int j = 1; j<=n;j++){
                if (one.charAt(i-1)==two.charAt(j-1)){
                    d[i][j] = d[i-1][j-1];
                } else {
                    d[i][j] = Integer.min(d[i - 1][j] + 1, Integer.min(d[i][j - 1] + 1, d[i - 1][j - 1] + 1));
                }

            }
        }
        int i = m;
        int j = n;
        //("+" вставка, "-" удаление, "~" замена, "#" копирование)
        String result = "";
        while(i>0 && j>0){
            if (d[i][j]==d[i-1][j]+1){
                result = "-"+one.charAt(i-1)+","+result;
                i--;
            } else if (d[i][j]==d[i][j-1]+1){
                result = "+"+two.charAt(j-1)+","+result;
                j--;
            } else {
                if (one.charAt(i-1)==two.charAt(j-1)){
                    result = "#,"+result;
                } else {
                    result = "~"+two.charAt(j-1)+","+result;
                }
                j--;
                i--;
            }


        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}