package com.routesearch.test;

import com.filetool.util.FileUtil;
import com.filetool.util.LogUtil;
import com.routesearch.route.Route;

/**
 * 工具入口
 * 
 * @author
 * @since 2016-3-1
 * @version v1.0
 */
public class Main
{
    public static void main(String[] args)
    {
    	
        /*if (args.length != 3)
        {
            System.err.println("please input args: graphFilePath, conditionFilePath, resultFilePath");
            return;
        }*/

        String conditionFilePath = "D:\\JAVAPro\\codecraft\\test-case\\case1\\demand.csv";
        String resultFilePath = "D:\\JAVAPro\\codecraft\\test-case\\case1\\sample_result.csv";
        String graphFilePath = "D:\\JAVAPro\\codecraft\\test-case\\case1\\topo.csv";

        LogUtil.printLog("Begin");

        // 读取输入文件
        String graphContent = FileUtil.read(graphFilePath, null);
        String conditionContent = FileUtil.read(conditionFilePath, null);

        // 功能实现入口
        String resultStr = Route.searchRoute(graphContent, conditionContent);

        // 写入输出文件
        FileUtil.write(resultFilePath, resultStr, false);

        LogUtil.printLog("End");
    }

}
