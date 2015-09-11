package com.test.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectReaderWriter {

    public static void main(String[] args) throws Exception {
        // test1();
        // test2();
        test3();
    }

    private static void test1() throws IOException, FileNotFoundException, ClassNotFoundException {
        File file = new File("person.out");

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        Person person = new Person("John", 18, true, Hobby.DRIVING);
        oout.writeObject(person);
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject(); // 没有强制转换到Person类型
        oin.close();
        System.out.println(newPerson);
    }

    private static void test2() throws IOException, FileNotFoundException, ClassNotFoundException {
        File file = new File("person.out");

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        Person2 person = new Person2("John", 18, true, Hobby.DRIVING);
        oout.writeObject(person);
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject(); // 没有强制转换到Person类型
        oin.close();
        System.out.println(newPerson);
    }

    private static void test3() throws IOException, FileNotFoundException, ClassNotFoundException {
        File file = new File("person.out");

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        Person3 person = new Person3("John", 18, true, Hobby.DRIVING);
        oout.writeObject(person);
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject(); // 没有强制转换到Person类型
        oin.close();
        System.out.println(newPerson);

        System.out.println(Person3.getInstance() == newPerson); // 将获取的对象与Person类中的单例对象进行相等性比较
    }
}
