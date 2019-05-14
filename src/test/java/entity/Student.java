package entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable{
    private static final long serialVersionUID = 681389949657582160L;
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String by;
}
