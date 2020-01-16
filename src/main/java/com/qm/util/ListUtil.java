package com.qm.util;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by qiming on 2019/4/8.
 */
@Slf4j
public class ListUtil {

    /**
    * @author qiming
    * @Description 复制 List
    * @Date 19:20 2019/4/8
    **/
    public static<T> void copyList(Object source, List<T> targetList, Class<T> targetObj) {
        if ((!Objects.isNull(source)) && (!Objects.isNull(targetList))) {
            List list1 = (List) source;
            list1.forEach(item -> {
                try {
                    T data = targetObj.newInstance();
                    BeanUtils.copyProperties(item, data);
                    targetList.add(data);
                } catch (InstantiationException e) {
                    log.error("实例化异常：", e);
                } catch (IllegalAccessException e) {
                    log.error("安全权限异常：", e);
                }
            });
        }
    }

    public static void main(String[] args) {

        @Data
        class St1 {
            private String name1;
            private String name2;
        }

        @Data
        class St2 {
            private String name1;
            private String name3;
        }

        List<St1> t1 = Lists.newArrayList();
        List<St2> t2 = Lists.newArrayList();

        St1 s1 = new St1();
        s1.setName1("name1");
        s1.setName2("name2");

        St1 s2 = new St1();
        s2.setName1("name1-1");
        s2.setName2("name2-1");

        t1.add(s1);
        t1.add(s2);

        copyList(t1, t2, St2.class);
        t2.forEach(item -> {
            log.info("name1 is {}", item.getName1());
            log.info("name3 is {}", item.getName3());
            log.info("-----------------------------");
        });
        log.info("OVER...");
    }
}
