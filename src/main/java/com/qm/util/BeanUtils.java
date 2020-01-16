package com.qm.util;

import com.qm.entity.Student;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @Description java对象的一些工具
 * @Author qiming
 * @Date 2019/7/12 14:21
 **/
public class BeanUtils {

    /**
    * @Description 给对象的空值赋默认值
    * @Author qiming
    * @Date 15:27 2019/7/12
    **/
    public static void propertyDefault(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String type = field.getType().getSimpleName();
            System.out.println("type is " + type);
            try {
                if (field.get(obj) == null) {
                    switch (type) {
                        case "String" :
                            field.set(obj, "-1000");
                            break;
                        case "BigDecimal" :
                            field.set(obj, new BigDecimal(-1000));
                            break;
                        default :
                            break;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("测试");
        propertyDefault(student);
        System.out.println("id is " + student.getId());
        System.out.println("name is" + student.getName());
        System.out.println("age is " + student.getAge());
        System.out.println("ra is " + student.getRa());
        System.out.println("ss is " + student.getSs());
    }
}
