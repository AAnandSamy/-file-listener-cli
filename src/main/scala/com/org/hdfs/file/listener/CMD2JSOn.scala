package com.org.hdfs.file.listener

import java.util
import java.util.Date

import org.json.{JSONArray, JSONObject}

import scala.collection.JavaConverters._
//import scala.sys.process._


object CMD2JSOn  {
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")

  val op3 =
    """Found 123 items
      |-rw-r--r--   3 iadetl hadoop          0 2020-06-26 00:21 /data/ss/localglobalclusterpop/_SUCCESS
      |drwxr-xr-x   - iadetl hadoop          0 2020-06-15 10:13 /data/ss/localglobalclusterpop/dt=2020-06-12
      |drwxr-xr-x   - iadetl hadoop          0 2020-06-15 10:00 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14
      |-rw-r--r--   3 iadetl hadoop     499558 2020-06-15 09:59 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14/part-00000-4b866697-9295-4470-9221-2eccc3bb593f.c000.snappy.parquet
      |-rw-r--r--   3 iadetl hadoop     501975 2020-06-15 09:59 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14/part-00001-4b866697-9295-4470-9221-2eccc3bb593f.c000.snappy.parquet
      |-rw-r--r--   3 iadetl hadoop     498568 2020-06-15 09:59 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14/part-00002-4b866697-9295-4470-9221-2eccc3bb593f.c000.snappy.parquet
      |""".stripMargin

  def main(args: Array[String]): Unit = {

    println(processCmd2Json(op3,"/data/ss/localglobalclusterpop/"))


  }




  def processCmd2Json(op1: String,path:String): JSONObject = {
    val datRange = new util.ArrayList[Date]()
    val lines = op1.lines.toList
    val dt = new JSONObject()
    dt.put("path",path)
    val jfiles = new JSONArray()
    for (line <- lines) {
      val lineop = line.split(" ")
      if (line.contains(path)) {
        jfiles.put(lineop.apply(lineop.length-1))
        datRange.add(format.parse(s"${lineop.apply(lineop.length-3)} ${lineop.apply(lineop.length-2)}"))
      }
      dt.put("files",jfiles)
    }
    val scalaList = datRange.asScala
    val maxDate = scalaList.sorted
    dt.put("last_file_offset_ts",maxDate.last)
    dt
  }

}









