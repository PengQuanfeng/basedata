package com.drelephant.elephantadmin.business.basedata.util;

public final class Constans {
	private Constans() {
    }
	public static final String AUTIT_ORGNATURE="company";//机构性质 --公司
	public static final String AUTIT_HOSPITAL="HOSPITAL";//机构性质 --医院
	/**
	 * 三级甲等
	 */
	public static final String HOSPITALLEVEL_A="A";//医院等级
	/**
	 * 三级乙等
	 */
	public static final String HOSPITALLEVEL_B="B";//医院等级
	/**
	 * 三级丙等
	 */
	public static final String HOSPITALLEVEL_C="C";//医院等级
	/**
	 * 三级丙等
	 */
	public static final String HOSPITALLEVEL_D="D";//医院等级

	/**
     * 有效.
     */
    public static final String ACTIVE = "ACT";
    /**
     * 链接开启状态
     */
    public static final String OPENLINK="1";
    /**
     * 链接关闭状态
     */
    public static final String NOTOPENLINK="0";
    /**
     * 删除
     */
    public static final String DELETED = "DEL";

    /**
     * 禁用
     */
    public static final String INVALID = "INV";
   
    /**
     * 病例模板类型 科室
     */
    public static final String TEMPTYPE = "DEPT";
    
    /**
     * 病例模板类型 个人
     */
    public static final String PERSONAL = "PER";
}
