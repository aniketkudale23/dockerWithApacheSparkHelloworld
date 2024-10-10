package com.example;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class SparkFileReader {
    private static final Logger logger = LoggerFactory.getLogger(SparkFileReader.class);

    public static void main(String[] args)throws Exception {
        // Spark Configuration
        SparkConf conf = new SparkConf().setAppName("File Reader").setMaster("local");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());


        try {


            // Use Spark to read the file
            String filePath = "sparkContents.txt";
            JavaRDD<String> lines = sc.textFile(filePath);
        lines.foreach(line -> logger.info("Line from file: {}", line));

        } catch (Exception e) {
            logger.error("An error occurred while processing the resource file.", e);
        } finally {
            sc.stop();
        }
    }
}

