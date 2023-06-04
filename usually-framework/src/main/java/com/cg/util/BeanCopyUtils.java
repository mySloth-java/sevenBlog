package com.cg.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cgJavaAfter
 * @date 2023-04-24 21:23
 */
//拷贝bean工具类
public class BeanCopyUtils {
    public BeanCopyUtils() {
    }

    //拷贝单个对象
    public static <V> V copyBean(Object source,Class<V> clazz){
        V result = null;
        try {
            //创建目标对象
            result = clazz.newInstance();
            //实现对象的拷贝
            BeanUtils.copyProperties(source,result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //最后返回拷贝的对象
        return result;
    }

    //拷贝集合对象
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}
