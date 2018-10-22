package com.drelephant.elephantadmin.business.basedata.util;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import java.util.Collection;
import java.util.List;

/**
 * @author zhou.fan
 * @date 2018/10/19 10:47
 * @description
 */
public final class CollectionUtil {
    private final static Logger log = LoggerFactory.getLogger(CollectionUtil.class);

    /**
     * 集合对象克隆
     **/
    public static <Source, Target> List<Target> copyCollectionAs(Collection<Source> source, Class<Target> targetType) {
        if (CollectionUtils.isEmpty(source)) {
            return Lists.newArrayList();
        }
        List<Target> result = Lists.newArrayListWithExpectedSize(source.size());
        for (Source src : source) {
            Target target;
            try {
                target = targetType.newInstance();
                BeanUtils.copyProperties(src, target);
                result.add(target);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("copy fail   target :{}  ", targetType);
                }
            }

        }
        return result;
    }

}
