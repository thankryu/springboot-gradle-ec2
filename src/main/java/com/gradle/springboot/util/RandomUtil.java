package com.gradle.springboot.util;

import com.gradle.springboot.util.vo.RandomVo;

import java.util.Random;

public class RandomUtil {

    /**
     * 최초 지정된 카운트 부터 총합 이내의 랜덤 함수 반환
     * @return int
     */
    public int makeRandomInt(RandomVo RandomVo) {
        Random rd = new Random();
        return rd.nextInt(RandomVo.getTotal_cnt())+RandomVo.getStart_seq();
    }
}