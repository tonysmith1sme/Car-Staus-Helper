package com.huawei.carstatushelper.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectHelper {
    private static final Map<String, Class<?>> TYPE_MAP = new HashMap<>();
    private static final String TAG = ReflectHelper.class.getSimpleName();

    static {
        TYPE_MAP.put("short", short.class);
        TYPE_MAP.put("int", int.class);
        TYPE_MAP.put("long", long.class);
        TYPE_MAP.put("float", float.class);
        TYPE_MAP.put("double", double.class);
        TYPE_MAP.put("boolean", boolean.class);

        //  String name21 = short[].class.getName();//[S
        //  String name22 = int[].class.getName();//[I
        //  String name23 = long[].class.getName();//[J
        //  String name24 = float[].class.getName();//[F
        //  String name25 = double[].class.getName();//[D
        //  String name26 = boolean[].class.getName();//[Z
        try {
            TYPE_MAP.put("short[]", Class.forName("[S"));
            TYPE_MAP.put("int[]", Class.forName("[I"));
            TYPE_MAP.put("long[]", Class.forName("[J"));
            TYPE_MAP.put("float[]", Class.forName("[F"));
            TYPE_MAP.put("double[]", Class.forName("[D"));
            TYPE_MAP.put("boolean[]", Class.forName("[Z"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getBasicType(String argTypes) {
        if (TYPE_MAP.containsKey(argTypes)) {
            return TYPE_MAP.get(argTypes);
        }
        //  Class<?> aClass1 = Class.forName("[Ljava.lang.String;");
        if (argTypes.contains("[]")) {
            try {
                return Class.forName("[Ljava.lang." + argTypes.replace("[]", ";"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                return Class.forName("[L" + argTypes.replace("[]", ";"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                return Class.forName("java.lang." + argTypes);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                return Class.forName(argTypes);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * "int"
     * "String"
     * "String,String"
     * "int,int,int,int"
     * "int[]"
     * "int[],String[]"
     * "null"
     *
     * @param argTypes
     * @return
     */
    private static Class<?>[] getMethodArgType(String argTypes) {
        if (argTypes.length() == 0) {
            return null;
        }
        List<Class<?>> list = new ArrayList<>();
        if (argTypes.contains(",")) {
            String[] split = argTypes.split(",");
            for (String s : split) {
                Class<?> type = getBasicType(s);
                if (type != null) {
                    list.add(type);
                }
            }
        } else {
            Class<?> basicType = getBasicType(argTypes);
            if (basicType != null) {
                list.add(basicType);
            }
        }
        Class<?>[] result = new Class[list.size()];
        list.toArray(result);
        return result;
    }

    private static Object[] getMethodArgValue(@Nullable Class<?>[] argTypes, @NonNull String argValues) {
        List<Object> list = new ArrayList<>();
        if (argValues.length() == 0) {
            return null;
        }
        if (argTypes == null) {
            throw new IllegalArgumentException("argType can not be null");
        }
        if (argValues.contains(",")) {
            String[] split = argValues.split(",");
            for (int i = 0; i < argTypes.length; i++) {
                Class<?> type = argTypes[i];
                String typeName = type.getName();
                String value = split[i];
                if ("int".equals(typeName)) {
                    list.add(Integer.parseInt(value));
                } else if (String.class.getName().equals(typeName)) {
                    list.add(value);
                }
            }
        } else {
            String typeName = argTypes[0].getName();
            if ("int".equals(typeName)) {
                //java.lang.IllegalArgumentException: method com.huawei.reflect_demo.BYDAutoDevice.testFun4 argument 1 has type int, got java.lang.String
                list.add(Integer.parseInt(argValues));
            } else if (String.class.getName().equals(typeName)) {
                list.add(argValues);
            }
        }
        Object[] result = new Object[list.size()];
        list.toArray(result);
        return result;
    }

    public static String invoke(Context context, int newInstanceType, String clzName, String methodName, String methodArgType, String methodArgValue) {
        Class<?>[] argType = ReflectHelper.getMethodArgType(methodArgType);
        Object[] argValue = ReflectHelper.getMethodArgValue(argType, methodArgValue);
        if (argType != null && argValue != null) {
            if (argType.length != argValue.length) {
                Log.e(TAG, "error: argType.length != argValue.length");
                return "null";
            }
        }
        try {
            Class<?> clz = Class.forName(clzName);
            //获取实例
            Object obj;
            if (newInstanceType == 1) {
                Constructor<?> declaredConstructor = clz.getDeclaredConstructor(Context.class);
                declaredConstructor.setAccessible(true);
                obj = declaredConstructor.newInstance(context);
            } else {
                Method getInstance = clz.getDeclaredMethod("getInstance", Context.class);
                getInstance.setAccessible(true);
                obj = getInstance.invoke(null, context);
            }
            //获取方法
            Method declaredMethod;
            if (argType == null) {
                declaredMethod = clz.getDeclaredMethod(methodName);
            } else {
                declaredMethod = clz.getDeclaredMethod(methodName, argType);
            }
            declaredMethod.setAccessible(true);
            //填充方法参数并调用
            Object ret;
            if (argValue == null) {
                ret = declaredMethod.invoke(obj);
            } else {
                ret = declaredMethod.invoke(obj, argValue);
            }
            String result;
            if (ret instanceof String) {
                result = (String) ret;
            } else {
                result = String.valueOf(ret);
            }
            return result;
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public static void testHelper(Context context) {
        String clzName = "com.huawei.carstatushelper.util.ReflectHelperTestDevice";

//        String methodName = "getAutoSystemState";
//        String methodArgType = "";
//        String methodArgValue = "";
//        Object expectedResult = 1133;

//        String methodName = "hasFeature";
//        String methodArgType = "String";
//        String methodArgValue = "aabbcc";
//        Object expectedResult = 3322;

//        String methodName = "testFun4";
//        String methodArgType = "int";
//        String methodArgValue = "3";
//        Object expectedResult = 4466;

//        String methodName = "setAcWindLevel";
//        String methodArgType = "int,int";
//        String methodArgValue = "3,5";
//        Object expectedResult = 5566;

//        String methodName = "testFun5";
//        String methodArgType = "int,String";
//        String methodArgValue = "4,abc";
//        Object expectedResult = 4477;

        String methodName = "testFun6";
        String methodArgType = "";
        String methodArgValue = "";
        Object expectedResult = "abc";

        String invoke = ReflectHelper.invoke(context, 0, clzName, methodName, methodArgType, methodArgValue);

        //判断结果与预期是否一致
        if (invoke == expectedResult) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
    }
}
