package bean;

import entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BeanUtilsTest {

    @Test
    public void beanToMap() {
        Student student = new Student();
        student.setAge(17);
        student.setId(1);
        student.setName("wujian");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student);
        Map<String, Object> map = BeanUtils.beanToMap(student);
        System.out.println(map);
    }

    @Test
    public void beanToMap1() {
    }

    @Test
    public void mapToBean() {
    }

    @Test
    public void mapToBean1() {
    }

    @Test
    public void convert() {
    }

    @Test
    public void convert1() {
    }

    @Test
    public void beanIsNull() {
        Student student = new Student();
        Boolean aBoolean = BeanUtils.beanIsNull(student);
        System.out.println(aBoolean);
        Student student1 = null;
        Boolean aBoolean1 = BeanUtils.beanIsNull(student1);
        System.out.println(aBoolean1);
        List<Student> studentList = new ArrayList<>();
        Boolean aBoolean2 = BeanUtils.beanIsNull(studentList);
        System.out.println(aBoolean2);
        Boolean aBoolean3 = BeanUtils.beanIsNull(" ");
        System.out.println(aBoolean3);
    }
}