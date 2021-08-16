package com.org.hdfs.file.listener

import com.org.hdfs.file.listener.cmdparser.ParseCmdOp2Json

import scala.io.Source
import scala.sys.process._

/**
 * This
 */
object FileListener extends App {
  var last_committed_max_dt_ts = ""

  try {
    val cmdop = s"hdfs dfs -ls -R -t ${args.apply(0)}" !!

    val FileDeltaJson = ParseCmdOp2Json.processCmdOupt2Json(cmdop, args.apply(0))

    s"mkdir -p logs" !
    val logsdir = args.apply(0).split("/").toList.last
    s"mkdir -p logs/${logsdir}" ! // create logs dir for given path if not exists
    val Islast_file_offset_ts = scala.reflect.io.File(s"logs/${logsdir}/last_file_offset_ts.txt").exists
    if (Islast_file_offset_ts) {
      last_committed_max_dt_ts = Source.fromFile(s"logs/${logsdir}/last_file_offset_ts.txt").getLines.mkString
    }
    // Very first time its will empty, then second time onwards it will get the last committed/published details.
    if (last_committed_max_dt_ts.trim.isEmpty) {
      //  Place holder to invoke ENDS service.
    }
    /// Once event is published to EDNS then update last committed/published date in below path, this ll used for next run.
    s"echo '${FileDeltaJson.getString("last_file_offset_ts")}' > logs/${logsdir}/last_file_offset_ts.txt" !


  }
  catch {
    case exc: Exception =>
      println("Unable to start file events", exc)

  }


}

