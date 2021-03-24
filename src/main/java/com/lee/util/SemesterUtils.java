package com.lee.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemesterUtils {

    public static List<String> getAllSemesters() {
        List<String> list = new ArrayList<>();
        list.add("201701");
        list.add("201702");
        list.add("201801");
        list.add("201802");
        list.add("201901");
        list.add("201902");
        list.add("202001");
        list.add("202002");
        return list;
    }

    public static Map<String, Object> getSemesterMapTextKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("大一上学期", "201701");
        map.put("大一下学期", "201702");
        map.put("大二上学期", "201801");
        map.put("大二下学期", "201802");
        map.put("大三上学期", "201901");
        map.put("大三下学期", "201902");
        map.put("大四上学期", "202001");
        map.put("大四下学期", "202002");
        return map;
    }

    public static Map<String, Object> getSemesterMapNumberKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("201701", "大一上学期");
        map.put("201702", "大一下学期");
        map.put("201801", "大二上学期");
        map.put("201802", "大二下学期");
        map.put("201901", "大三上学期");
        map.put("201902", "大三下学期");
        map.put("202001", "大四上学期");
        map.put("202002", "大四下学期");
        return map;
    }

}
