import java.io.{BufferedWriter, FileWriter, PrintWriter}

import scala.io.Source

object NewLineTypeChanger {
  val crlf = "\r\n"
  val lf = "\n"

  def main(args: Array[String]): Unit = {
    val files = args.tail
    args.head match {
      case "unix" => {
        changeNewLineType(files, lf)
      }
      case "windows" => {
        changeNewLineType(files, crlf)
      }
      case _ => {
        println("The new line type is not set for the first argument.")
        println("Set \"unix\" for converting CRLF to LF, or set \"windows\" for converting LF to CRLF.")
      }
    }
  }

  private def changeNewLineType(files: Array[String], newLineType: String): Unit = {
    for (file <- files) {
      val source = Source.fromFile(file)
      val lines = source.getLines().map(line => line + newLineType).toList
      val pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))
      lines.foreach(line => pw.write(line))
      pw.close()
      source.close()
    }
  }
}
