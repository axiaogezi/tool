package bean;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class BeanUtils {
    /**
     * 使用get方法将Bean转换为Map
     *
     * @param object
     * @return
     */
    public static Map<String, Object> beanToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor p : propertyDescriptors) {
                if (!p.getName().equals("class")) {
                    Method readMethod = p.getReadMethod();
                    Object invoke = readMethod.invoke(object) == null ? "" : readMethod.invoke(object);
                    map.put(p.getName(), invoke);
                }
            }
        } catch (Exception e) {
            log.error("bean to map error");
            e.printStackTrace();
        }
        return map;
    }

    public static List<Map<String, Object>> beanToMap(List<?> list) {
        return list.stream().map(object -> beanToMap(object)).collect(Collectors.toList());
    }

    /**
     * 使用set方法将map转换为Bean
     * @param map
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, final Class<T> t) {
        if (null == map) {
            return null;
        }
        T t1 = null;
        try {
            t1 = t.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(t);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor p : propertyDescriptors){
                if(map.containsKey(p.getName())){
                    p.getWriteMethod().invoke(t1,map.get(p.getName()));
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t1;
    }
    public static <T> List<T> mapToBean(List<Map<String,Object>> mapList,final Class<T> t){
        return mapList.stream().map(m -> mapToBean(m, t)).collect(Collectors.toList());
    }

    /**
     * 使用get，set方法复制类的值
     * @param object
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T convert(Object object, final Class<T> t){
        T t1 = null;
        try {
            t1 = t.newInstance();
            BeanInfo resultInfo = Introspector.getBeanInfo(t);
            PropertyDescriptor[] resultPropertys = resultInfo.getPropertyDescriptors();
            for (PropertyDescriptor rp : resultPropertys){
                PropertyDescriptor op = getPropertyDescriptor(object.getClass(), rp.getName());
                if(null != op && !rp.getName().equals("class")){
                    Object invoke = op.getReadMethod().invoke(object);
                    rp.getWriteMethod().invoke(t1,invoke);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t1;
    }
    public static <T> List<T> convert(List<?> list,final Class<T> t){
        return list.stream().map(object -> convert(object, t)).collect(Collectors.toList());
    }

    /**
     * 验证是否为空
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Boolean beanIsNull(T t){
        if(null == t){
            return true;
        }
        if(t instanceof String && StringUtils.isBlank(t.toString())){
            return true;
        }
        if(t instanceof Collection && ((Collection<?>) t).isEmpty()){
            return true;
        }
        if(t instanceof Map && ((Map<?,?>) t).isEmpty()){
            return true;
        }
        if(t instanceof Object[] && ((Object[]) t).length < 1){
            return true;
        }
        return false;
    }

    /**
     * 检查类中是否存在指定的全部属性（成员）
     * @param clazz
     * @param fields
     * @return
     */
    public static Boolean containsFields(Class clazz, String... fields){
        if(fields.length <= 0){
            return false;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (String f : fields){
                Boolean inB = false;
                for (PropertyDescriptor p : propertyDescriptors){
                    if(f.equals(p.getName())){
                        inB = true;
                        break;
                    }
                }
                if(!inB){
                    return false;
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static PropertyDescriptor getPropertyDescriptor(Class clazz, String field){
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor p : propertyDescriptors){
                if(p.getName().equals(field)){
                    return p;
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
