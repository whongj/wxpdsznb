package com.wxpdsznb.wx.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Tyranitarx on 2017/5/2.
 */
@Repository
public interface FirstBookPinyinMapper {
    @Select("select bname from books where get_first_pinyin_char(bname) = '#{Pinyin}'")
    public String getFirstPinyin(@Param("Pinyin") String Pinyin);
}
