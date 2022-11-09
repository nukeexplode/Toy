package com.julio;

/**
 * @author Julio
 * @date 2021/1/15-15:57
 **/
public class Test {
    static  Test test = new Test();

    static int a;
    static int b=1;
    Test(){
        a++;
        b++;
    }
    static Test getInstance(){
        return test;
    }

    public static void main(String[] args) {
       Integer i = 1,j =1;
        System.out.println(i==j);
        System.out.println(i.equals(j));

        Integer m =200, n = 200;
        System.out.println(m==n);
        System.out.println(m.equals(n));
    }
}
