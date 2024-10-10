// HelloWorldSpark.java
package com.example;

import org.apache.spark.sql.SparkSession;

public class HelloWorldSpark {
    public static void main(String[] args) {
        // Initialize Spark Session
        SparkSession spark = SparkSession.builder()
                .appName("HelloWorldSpark")
                .master("local[*]") // Remove or modify this when submitting to a cluster
                .getOrCreate();

        // Print "Hello World" using Spark's log
        for(int i = 0; i < 1000; i++){
            spark.sparkContext().log().info("****Hello World from Apache Spark! number:"
                    + String.valueOf(i));
        }


        // Stop Spark Session
       // spark.stop();
    }
}
