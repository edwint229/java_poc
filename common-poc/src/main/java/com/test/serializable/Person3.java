package com.test.serializable;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Person3 implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3363625332685275024L;

    private static class InstanceHolder {

        private static final Person instatnce = new Person("John", 18, true, Hobby.DRIVING);
    }

    private String name;

    private transient Integer age;

    private Boolean gender;

    private Hobby hobby;

    public static Person getInstance() {
        return InstanceHolder.instatnce;
    }

    public Person3() {
        super();
    }

    public Person3(String name, int age, boolean gender, Hobby hobby) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", hobby=" + hobby + "]";
    }

    private Object readResolve() throws ObjectStreamException {
        return InstanceHolder.instatnce;
    }

}
