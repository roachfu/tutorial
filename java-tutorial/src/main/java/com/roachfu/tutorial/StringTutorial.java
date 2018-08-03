package com.roachfu.tutorial;

public class StringTutorial {

    public void changeStr(String str) {
        str = "abc";
    }

    public void changeStringBuilder(StringBuilder sb){
        sb = new StringBuilder("abc");
    }

    public void changeStringBuilder2(StringBuilder sb){
        sb.append("abc");
    }

    public static void main(String[] args) {
        StringTutorial tutorial = new StringTutorial();

        String str = "123";
        tutorial.changeStr(str);
        // 123
        System.out.println(str);

        StringBuilder sb = new StringBuilder("123");
        tutorial.changeStringBuilder(sb);
        // 123
        System.out.println(sb);

        sb = new StringBuilder("123");
        tutorial.changeStringBuilder2(sb);
        // 123abc
        System.out.println(sb);


    }

}
