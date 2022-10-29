package com.djin.myspark

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession}


/**
 * @auther dj
 * @date 2022/9/12
 * @note:
 */
object SparkHiveTest {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "hive")
    System.setProperty("HADOOP_CONF_DIR", "")
    val spark = SparkSession
      .builder()
      .enableHiveSupport()
      .master("local[2]")
//      .master("local[4]")
      .appName("spark_hive_test1")
//      .config("spark.sql.hive.convertMetastoreParquet", false)
//      .config("spark.submit.deployMode", "client")
//      .config("spark.sql.warehouse.dir","/user/hive/warehouse")
//      .enableHiveSupport()
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR") //设置日志输出级别
    import spark.implicits._
    import spark.sql

    sql("show databases").show()
//    sql("create table dbtest.table2 (id int,high int)").show()
//    sql("select * from dbtest.table1").show()
  }
}
