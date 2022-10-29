package com.djin.myspark

import org.apache.spark.sql.SparkSession

/**
 * @auther dj
 * @date 2022/10/29
 * @note:
 */
object ExecuteSql {

  def main(args: Array[String]): Unit = {
    //    KerberosAuthenticator.startK()

    val all = new AnalysisArgs(args)
    val sqlText = all.getSqlText()
    val jobName = all.getJobName()

    val spark = SparkSession.builder()
      .appName(jobName)
      .master("local[2]")
      .enableHiveSupport()
      .getOrCreate()



    import spark.sql
    all.sqlPrint()
    sql(sqlText).show(20)
    spark.stop()

  }
}
