package com.djin.myspark

import org.apache.spark.sql.SparkSession

import java.util.Random
import scala.math.{exp, random}

/**
 * @auther dj
 * @date 2022/9/20
 * @note:
 */
object SparkTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("Spark Pi")
      .enableHiveSupport()
      .getOrCreate()
    val slices = if (args.length > 0) args(0).toInt else 2
    val n = math.min(10L * slices, Int.MaxValue).toInt // avoid overflow
    val count = spark.sparkContext.parallelize(1 until n, slices).map { i =>
      val x = random * 2 - 1
      val y = random * 2 - 1
      if (x*x + y*y <= 1) 1 else 0
    }.reduce(_ + _)
    println(s"Pi is roughly ${4.0 * count / (n - 1)}")
    spark.stop()
  }
}
