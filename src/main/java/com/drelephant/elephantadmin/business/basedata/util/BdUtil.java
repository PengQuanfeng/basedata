package com.drelephant.elephantadmin.business.basedata.util;

import org.apache.commons.lang.StringUtils;

import javax.annotation.Nullable;

public class BdUtil {


    public static boolean isAnyBlank(@Nullable String... strings) {
        if (strings == null) {
            return true;
        }
        for (String string : strings) {
            if (StringUtils.isBlank(string)) {
                return true;
            }
        }
        return false;

    }

}
