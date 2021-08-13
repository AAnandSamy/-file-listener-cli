package com.org.hdfs.file.listener


import java.util
import java.util.Date

import org.json.{JSONArray, JSONObject}

import scala.io.Source
import scala.sys.process._

object FileListener extends App {
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")

  val args1 = ""
  var max_dt_ts = ""


  try {
    s"mkdir -p logs" !

    val logsdir =    args1.split("/").toList.last
    s"mkdir -p logs/${logsdir}" !

    val Islast_file_offset_ts = scala.reflect.io.File(s"logs/${logsdir}/last_file_offset_ts.txt").exists
    if (Islast_file_offset_ts) {
      max_dt_ts = Source.fromFile(s"logs/${logsdir}/last_file_offset_ts.txt").getLines.mkString
    } else {

    }
/// after published to
    s"echo 'date time' > last_file_offset_ts.txt" !
    // val op = s"${args.apply(0)}"!!

  }
  catch {
    case exc: Exception =>
      println("Unable to start file events", exc)

  }

  def processCmd2Json(op1: String,path:String): JSONObject = {
    val datRange = new util.ArrayList[Date]()
    val lines = op.lines.toList
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

  def getlastCommittedTs(): Unit = {

  }

}

