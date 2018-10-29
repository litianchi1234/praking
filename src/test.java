import java.math.BigDecimal;
import java.sql.SQLOutput;
import  java.util.Scanner;

public class test {
    public static void main(String[] args) {

     Scanner reader = new Scanner(System.in);
     int n = reader.nextInt();
     Scanner[] scan = new Scanner[n];
     long a [] [] =new long[n][n];

     /*   for (int i =1;i<= 9;i++){
            for(int j =1;j<=i;j++)
            {
                System.out.print(j+"*"+i+"="+j*i+"\t");
            }
            System.out.println();
        }*/
       /* int lay =7;
        for(int i =1; i<=lay; i++){
            for(int a=0 ;a<lay-i;a++){
                System.out.print(" ");
            }
            for(int b = 1; b <= i*2-1; b ++)
            {
                if(i == 1||i == lay)//判断是否是第一或最后一行
                {
                    System.out.print("*");
                }
                else
                {
                    if(b == 1||b == i*2-1)//判断是否是本行第一个或最后一个字符
                    {
                        System.out.print("*");
                    }
                    else
                    {
                        System.out.print(" ");
                    }

                }
            }

            System.out.println();
        }*/



      /*  int triangle [] []= new int [10][];

        for ( int i = 0; i<triangle.length ; i++)
        {
            triangle[i] = new int [i+1];

            for(int j = 0; j<i ;j ++)
            {
                if(i==0||j==0||i==j)
                {
                    triangle[i][j] = 1;

                }
                else{
                    triangle[i][j] = triangle[i-1][j]+triangle[i-1][j-1];
                }
                System.out.print(triangle[i][j]+"\t");

            }
            System.out.println();
        }*/





        /*      BigDecimal asd = new BigDecimal(0.0);*/

     /*   //实现一个加密
        Scanner i = new Scanner(System.in);
        String text = i.nextLine();
        char[] arr = text.toCharArray();
        for (int q =0; q < arr.length ; q++)
        {arr[q]= (char)(arr[q]^20000);
            System.out.print(arr[q]);

        }
        System.out.println();
        for (int q =0; q < arr.length ; q++)
        {arr[q]= (char)(arr[q]^20000);
            System.out.print(arr[q]);

        }*/

        /*Scanner asd = new Scanner(System.in);
        String result = (asd.nextInt()%2 == 0)? "偶数":"奇数";
        System.out.println(result);*/


        /*  String a = "asdhdsaHUWHDAKwejdabhNWDK";
        System.out.println(a.toLowerCase());
        System.out.println(a.toUpperCase());*/

    /*    int int_max = java.lang.Integer.MAX_VALUE;
        System.out.println(int_max);
        Integer a = 123;
        System.out.println(a);
        int b = 0xffff;
        System.out.println(b);
*/
 /*       String a = "456ads8e97e87974da4d2";
        String b = a.replaceAll("[0-9]","");

        System.out.println(a.getBytes());
        System.out.println(b);

        System.out.println( a.substring(0,2));

        System.out.println(a.lastIndexOf('d'));
        
        System.out.println();*/
    }
    }
