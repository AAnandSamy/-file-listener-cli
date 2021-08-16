package com.org.hdfs.file.listener

import com.org.hdfs.file.listener.cmdparser.ParseCmdOp2Json

object FileTest {

  def main(args: Array[String]): Unit = {
    val hdfsPath = "/data/ss/localglobalclusterpop/"
    val commandLineSampleOupt =
      """Found 123 items
        |-rw-r--r--   3 iadetl hadoop          0 2020-06-26 00:21 /data/ss/localglobalclusterpop/_SUCCESS
        |drwxr-xr-x   - iadetl hadoop          0 2020-06-15 10:13 /data/ss/localglobalclusterpop/dt=2020-06-12
        |drwxr-xr-x   - iadetl hadoop          0 2020-06-15 10:00 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14
        |-rw-r--r--   3 iadetl hadoop     499558 2020-06-15 09:59 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14/part-00000-4b866697-9295-4470-9221-2eccc3bb593f.c000.snappy.parquet
        |-rw-r--r--   3 iadetl hadoop     501975 2020-06-15 09:59 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14/part-00001-4b866697-9295-4470-9221-2eccc3bb593f.c000.snappy.parquet
        |-rw-r--r--   3 iadetl hadoop     498568 2020-06-15 09:59 /data/ss/localglobalclusterpop/dt=2020-06-12/slidingWindowInDays=14/part-00002-4b866697-9295-4470-9221-2eccc3bb593f.c000.snappy.parquet
        |""".stripMargin
    val fsl = ParseCmdOp2Json.processCmdOupt2Json(commandLineSampleOupt,hdfsPath)
    println(fsl)

  }

}
