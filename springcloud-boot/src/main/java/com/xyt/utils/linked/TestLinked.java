package com.xyt.utils.linked;

import java.util.Random;

/**
 * @Author: abby
 * @Date: 2020/12/11 15:51
 */
public class TestLinked {

    public static void main(String[] args) {
        DoubleLinked linked = new DoubleLinked();
        String credit = "";
        String name = "";
        int age = 0;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            credit = String.valueOf(random.nextInt(5));
            age = random.nextInt(5) + 18;
            for (int j = 0; j < 4; j++) {
                name += random.nextInt(11) + 97;
            }
            Student stu = new Student(name, age, credit);
            System.out.println("第" + i + "位学生加入！--->" + stu.toString());
            linked.add(stu);
        }

        System.out.println("Size of linked: " + linked.size());

        System.out.println("----------------------------------");

        Object o = linked.get(3);
        System.out.println("随机取出链表中对象：" + o.toString());

        System.out.println("----------------------------------");

        Object remove = linked.remove(4);
        System.out.println("删除对象：" + remove.toString());
        System.out.println("删除后Size of linked：" + linked.size());

    }
}
