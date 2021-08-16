package com.org.hdfs.file.listener.cmdparser

import java.util
import java.util.Date

import org.json.{JSONArray, JSONObject}

import scala.collection.JavaConverters._


/**
 * This class is used for command line outs to JSON format.
 * @author
 * @note Primary use for END service, but again this friendly components it can used for any Hadoop native clients
 */
object ParseCmdOp2Json {
  /* Server date time for command line out format*/
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")

  /**
   * This method is used parse the HDFS command line outputs to JSON format and also finding the latest file offset date time
   *
   * @param op1
   * @param path
   * @return JSON
   */
  def processCmdOupt2Json(op1: String, path: String): JSONObject = {
    val datRange = new util.ArrayList[Date]()
    val lines = op1.lines.toList
    val dt = new JSONObject()
    dt.put("path", path)
    val jfiles = new JSONArray()
    for (line <- lines) {
      val lineop = line.split(" ")
      if (line.contains(path)) {
        jfiles.put(lineop.apply(lineop.length - 1))
        datRange.add(format.parse(s"${lineop.apply(lineop.length - 3)} ${lineop.apply(lineop.length - 2)}"))
      }
      dt.put("files", jfiles)
    }
    val scalaList = datRange.asScala
    val maxDate = scalaList.sorted
    dt.put("last_file_offset_ts", maxDate.last)
    dt
  }

}









