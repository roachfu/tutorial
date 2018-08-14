package com.roachfu.tutorial.jpinyin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author roach
 * @date 2018/8/13 22:55
 */
@Slf4j
public class JpinyinTutorial {

    public static void main(String[] args) throws PinyinException {

        // 获取全拼
        String pinyin = PinyinHelper.convertToPinyinString("江西", "", PinyinFormat.WITHOUT_TONE);
        log.info(pinyin);

        // 获取简拼
        String easySpell = PinyinHelper.getShortPinyin("江西");
        log.info(easySpell);
    }
}
