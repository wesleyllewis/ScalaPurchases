import scala.io.Source
import scala.collection.mutable
import java.io._

object FileIO {
  def main(args: Array[String]): Unit = {
    def prompt(s: String) = {
      println(s)
      io.StdIn.readLine()
    }
    val purchasesList = mutable.MutableList[(String, String, String, String, String)]()

    Source.fromFile("purchases.csv").getLines.drop(1).foreach(line => {
      val Array(customerId, date, creditCard, cvv, category) = line.split(",").map(_.trim)
      val purchase = (customerId, date, creditCard, cvv, category)
      purchasesList += purchase
      ""
    })
    val input = prompt("\nPlease Select A Category:\n" +
      "Furniture\nAlcohol\nToiletries\nShoes\nFood\nJewelry\n")

    input match {
      case _ =>
        val data = purchasesList.filter(_._5.equalsIgnoreCase(input))
          .map(x => s"Customer: ${x._1}, Date: ${x._2.substring(0, 10)}")
        val writer = new FileWriter("filtered_purchases.prn")
        data.foreach(x => writer.write(x + "\n"))
        writer.close()
    }
  }
}


