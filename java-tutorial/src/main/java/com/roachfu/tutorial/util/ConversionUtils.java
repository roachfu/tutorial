package com.roachfu.tutorial.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.*;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 对象转换工具类
 *
 * @author roach
 * @date 2018/11/9
 */

@Slf4j
public class ConversionUtils {

    private static final char UNDERSCORE = '_';

    private ConversionUtils() {

    }

    /**
     * 将源对象转换为目标对象
     *
     * @param source      源对象
     * @param targetClass 目标对象Class
     * @param <T>         目标对象类型
     * @param <Q>         源对象类型
     * @return 目标对象
     */
    public static <T, Q> T of(Q source, Class<T> targetClass) {
        Assert.notNull(source, "source must not be null");

        T target = BeanUtils.instantiate(targetClass);
        BeanUtils.copyProperties(source, target);

        return target;
    }

    /**
     * 对象转Map
     *
     * <p>
     * 1. 针对实体里所有属性
     * 2. 包括继承的属性
     * 3. 空属性不会转换
     * </p>
     *
     * @param source 需要转换的对象
     * @param <T>    源对象类型
     */
    public static <T> Map<String, Object> ofMap(T source) {
        Map<String, Object> targetMap = new HashMap<>(16, 1);
        objectToMap(source, targetMap, false);

        return targetMap;
    }

    /**
     * 对象转Map
     *
     * <p>
     * 1. 针对实体里所有属性
     * 2. 包括继承的属性
     * 3. 空属性不会转换
     * </p>
     *
     * @param source    被转换对象
     * @param targetMap 目标Map
     * @param <Q>       源对象类型
     */
    public static <Q> void objectToMap(Q source, Map<String, Object> targetMap) {
        objectToMap(source, targetMap, false);
    }

    /**
     * 反射实现对象转Map <br>
     * 只针对实体里所有属性, 包括继承的属性 <br>
     * 空属性不会转换 <br>
     *
     * @param source            需要转换的对象
     * @param camelToUnderscore 是否驼峰转下划线
     * @param <Q>               源对象类型
     */
    public static <Q> void objectToMap(Q source, Map<String, Object> targetMap, boolean camelToUnderscore) {
        Assert.notNull(source, "source must not be null");
        Assert.notNull(targetMap, "targetMap must not be null");

        convertProperties(source, targetMap, camelToUnderscore);
    }

    /**
     * 将map对象转化成目标对象类型
     *
     * @param sourceMap   待转化的对象
     * @param targetClass 目录对象类型Class
     * @param <T>         目标对象类型
     * @return 目标对象类型
     */
    public static <T> T ofObject(Map<String, Object> sourceMap, Class<T> targetClass) {
        if (Objects.isNull(sourceMap) || sourceMap.isEmpty()) {
            return null;
        }

        T target = BeanUtils.instantiate(targetClass);
        mapToObject(sourceMap, target);

        return target;
    }

    /**
     * 将map对象转化成目标对象类型
     *
     * @param sourceMap 源Map集合
     * @param target    目标对象
     * @param <T>       目标对象类型
     */
    public static <T> void mapToObject(Map<String, Object> sourceMap, T target) {
        Assert.notNull(sourceMap, "sourceMap must be not null");
        Assert.notNull(target, "target must be not null");

        BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        PropertyValues propertyValues = new MutablePropertyValues(sourceMap);
        beanWrapper.setPropertyValues(propertyValues, true, true);
    }


    /**
     * 将源对象列表转化成目标对象列表
     *
     * <p>
     * 相较于<br> listToList </br>，一个有返回值，一个没有返回值
     * </p>
     *
     * @param sources     待转化的对象列表
     * @param targetClass 目录对象类型Class
     * @param <T>         目标对象类型
     * @param <Q>         待转化的对象类型
     * @return 目标对象类型列表
     */
    public static <T, Q> List<T> toList(List<Q> sources, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sources)) {
            return new ArrayList<>();
        }

        List<T> targets = new ArrayList<>();
        listToList(sources, targets, targetClass);

        return targets;
    }

    /**
     * 将源对象列表转化成目标对象列表
     *
     * @param sources     源对象列表
     * @param targets     目标对象列表
     * @param targetClass 目标对象类型Class
     * @param <T>         目标对象类型
     * @param <Q>         源对象类型
     */
    public static <T, Q> void listToList(List<Q> sources, List<T> targets, Class<T> targetClass) {
        Assert.notNull(sources, "sources must be not null");
        Assert.notNull(targets, "targets must be not null");

        for (Q source : sources) {
            T target = BeanUtils.instantiate(targetClass);
            BeanUtils.copyProperties(source, target);
            targets.add(target);
        }
    }

    /**
     * map列表转换成对象列表
     *
     * <p>
     * 相比较于 <br> mapListToList </br>，一个有返回值，一个无返回值
     * </p>
     *
     * @param sources     待转化的map列表
     * @param targetClass 目录对象类型Class
     * @param <T>         目标对象类型
     * @return 目标对象列表
     */
    public static <T> List<T> ofList(List<Map<String, Object>> sources, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sources)) {
            return new ArrayList<>();
        }

        List<T> targets = new ArrayList<>();
        mapListToList(sources, targets, targetClass);

        return targets;
    }

    /**
     * 源Map列表转换为目标对象列表
     *
     * @param sources     源Map列表
     * @param targets     目标对象列表
     * @param targetClass 目标对象类型
     * @param <T>         目标对象
     */
    public static <T> void mapListToList(List<Map<String, Object>> sources, List<T> targets, Class<T> targetClass) {
        Assert.notNull(sources, "sources must not be null !");
        Assert.notNull(targets, "targets must not be null !");

        for (Map<String, Object> map : sources) {
            T target = ofObject(map, targetClass);
            targets.add(target);
        }
    }

    /**
     * 源对象列表转化为目标Map列表
     *
     * @param sources 源对象列表
     * @param <Q>     源对象类型
     * @return 目标Map列表
     */
    public static <Q> List<Map<String, Object>> ofMapList(List<Q> sources) {
        List<Map<String, Object>> targets = new ArrayList<>();
        listToMapList(sources, targets);

        return targets;
    }

    /**
     * 源对象列表转化为目标Map列表
     *
     * @param sources 源对象列表
     * @param targets 目标Map列表
     * @param <Q>     源对象类型
     */
    public static <Q> void listToMapList(List<Q> sources, List<Map<String, Object>> targets) {
        Assert.notNull(sources, "sources must not be null !");
        Assert.notNull(targets, "targets must not be null !");

        for (Q source : sources) {
            Map<String, Object> targetMap = ofMap(source);
            targets.add(targetMap);
        }
    }

    /**
     * 属性转换
     *
     * <p>
     * 对象中的所有属性，包括父类属性
     * 排除静态属性
     * </p>
     *
     * @param source            源对象
     * @param targetMap         目标Map
     * @param camelToUnderscore 是否驼峰转下划线
     * @param <Q>               源对象类型
     */
    private static <Q> void convertProperties(Q source, Map<String, Object> targetMap, boolean camelToUnderscore) {
        Field[] fields = FieldUtils.getAllFields(source.getClass());
        try {
            for (Field field : fields) {
                // 过滤静态属性
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                if (field.get(source) != null) {
                    if (camelToUnderscore) {
                        // key为下划线风格
                        targetMap.put(camelToUnderscore(field.getName()), field.get(source));
                    } else {
                        targetMap.put(field.getName(), field.get(source));
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            log.error("反射获取转换对象异常", e);
        }
    }

    /**
     * 字符串由驼峰格式转换为下划线格式
     *
     * @param camel 驼峰格式字符串
     * @return 下划线风格字符串
     */
    private static String camelToUnderscore(String camel) {
        if (camel == null || "".equals(camel.trim())) {
            return "";
        }

        int len = camel.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = camel.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERSCORE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
