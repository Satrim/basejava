package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.ArrayStorage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Resume r = new Resume();
        Field[] field = r.getClass().getDeclaredFields();
        field[0].setAccessible(true);
        System.out.println(field[0].getName());
        System.out.println(field[0].get(r));
        field[0].set(r, "new_uuid");
        field[0].setAccessible(false);
        //ДЗ
        Method[] methods = r.getClass().getMethods();
        Method method = null;
        for (Method m : methods) {
            if (m.getName().equals("toString")) {
                System.out.println(m.getName() + " " + m);
                method = m;
            }
        }

        Class[] parameterTypes = method.getParameterTypes();
        Class classMethod = Resume.class;
        Method method1 = classMethod.getDeclaredMethod("toString", parameterTypes);
        System.out.println(method1.invoke(r, parameterTypes));

        System.out.println();
        System.out.println(r);

        AbstractArrayStorage storage = new ArrayStorage();
        Class cs = AbstractArrayStorage.class;
        Field[] fields = cs.getDeclaredFields();

        System.out.println("_______________");

        fields[1].setAccessible(true);

    }

}
