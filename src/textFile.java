import java.util.Scanner;public class textFile{public static void main(String [] args){System.out.println("Введите ваш IP-адресс");Scanner sc=new Scanner(System.in);String S=sc.nextLine();Ip ip=new Ip();int V=ip.P(S);if(V==-1){System.out.println("IP-адрес некорректный: неверная длинна IP-адресса");}else if(V==0){System.out.println("IP-адрес некорректный: введено неверное значение в IP-адрессс");}else{System.out.println("IP-адрес корректный");}}}class Ip{public static int P(String i){if(i.endsWith(".")){return 0;}String[] ps=i.split("\\.");if(ps.length !=4){return -1;}for(String p : ps){try{int n=Integer.parseInt(p);if(n < 0 || n > 255){return 0;}}catch(NumberFormatException e){return 0;}}return 1;}}