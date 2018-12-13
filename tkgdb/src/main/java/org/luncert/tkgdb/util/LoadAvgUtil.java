package org.luncert.tkgdb.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import lombok.Data;

/**
 * 获取平均负载
 */
public class LoadAvgUtil {
    
    @Data
    public static class LoadAvg {
        private float loadAvg1; // 1s平均负载
        private float loadAvg5; // 5s
        private float loadAvg15; // 15s
        int runningTaskNum; // 正在运行的作业数
        LoadAvg(float loadAvg1, float loadAvg5, float loadAvg15, int runningTaskNum) {
            this.loadAvg1 = loadAvg1;
            this.loadAvg5 = loadAvg5;
            this.loadAvg15 = loadAvg15;
            this.runningTaskNum = runningTaskNum;
        }
    }


    public static LoadAvg refresh() throws IOException {
        Reader in = new InputStreamReader(new FileInputStream("/proc/loadavg"));
        try (BufferedReader reader = new BufferedReader(in)) {
            String[] items = reader.readLine().split(" ");
            LoadAvg loadAvg = new LoadAvg(
                Float.valueOf(items[0]),
                Float.valueOf(items[1]),
                Float.valueOf(items[2]),
                Integer.valueOf(items[4]));
            return loadAvg;
        }
    }

}