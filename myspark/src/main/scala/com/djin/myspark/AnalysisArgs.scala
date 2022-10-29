package com.djin.myspark

import java.io.FileNotFoundException
import scala.io.Source

/**
 * @auther dj
 * @date 2022/10/29
 * @note:
 */
class AnalysisArgs(argsArr: Array[String]) {

  private var fileName = "./data/test.sql" //默认路径
  private var sqlText = "show databases" //默认sql
  private var jobName = "job_test" //默认job
  private var para: Map[String, String] = Map()
  private val argsStr = argsArr.mkString(" ")
  println("+---------- 传入参数: " + argsStr)

  if (argsArr.length == 0) {
    System.err.println("+---------- 未传递参数 ----------+")
    System.err.println("+---------- 传参配置如下: ----------+")
    System.err.println(
      """|  方式1:
         |  -sql="select * from table"    :直接传递sql字符串
         |
         |  方式2:
         |  -f[F,file]=./test.sql   :指定sql文件
         |  -[自定义参数名]=自定义参数值   :自定义参数可以多个
         |  示例: -f=./data/test.sql -start_time=\"2022-09-05\"
      """.stripMargin)
    println("+---------- 使用默认配置执行测试 ----------+")

  } else if (argsStr.substring(0, 5) == "-sql=") { //直接读取sql
    sqlText = argsStr.substring(5)
  } else {
    argsArr.foreach { arg =>
      val kv = arg.split("=")
      if (kv.length == 2 && kv(0).substring(0, 1) == "-") {
        val k = kv(0).substring(1) //参数名
        val v = kv(1) //参数值
        if (k == "f" || k == "file" || k == "F") { //读取sql文件
          fileName = v
          try {
            println("+---------- 读取sql文件: %1$s ----------+".format(fileName))
            sqlText = Source.fromFile(fileName).mkString //读取sql文件
          } catch {
            case ex: FileNotFoundException =>
              System.err.println("+---------- 指定的sql文件不存在 ----------+")
          }
        } else {
          para += (k -> v) //获取参数
        }
      } else {
        System.err.println("+---------- 参数格式不正确,忽略此配置:%s ----------+".format(arg))
      }
    }
  }
  para.keys.foreach { k =>
    val v = para(key = k)
    sqlText = sqlText.replaceAll("\\$\\{%s\\}".format(k), v)
  }


  def getSqlText(): String = {
    this.sqlText
  }

  def getFileName(): String = {
    this.fileName
  }

  def getJobName(): String = {
    val pattern = "job_name=\\w+".r
    this.jobName = (pattern findFirstIn sqlText).mkString
    val jobNameArr = this.jobName.split("=")
    if (jobNameArr.length == 2) {
      this.jobName = jobNameArr(1)
    } else {
      this.jobName = "job_please_fill_job_name_correctly"
    }
    this.jobName
  }

  def sqlPrint(): Unit = {
    println("\n" + "+" * 10 + "-" * 102 + "+" * 10)
    println("+" * 10 + "-" * 46 + " 执行Sql: " + "-" * 46 + "+" * 10)
    println("+" * 10 + "-" * 102 + "+" * 10)
    println(this.sqlText)
    println("+" * 10 + "-" * 103 + "+" * 10)
    println("+" * 10 + "-" * 46 + " Sql_end + " + "-" * 46 + "+" * 10)
    println("+" * 10 + "-" * 103 + "+" * 10 + "\n")
  }
}
