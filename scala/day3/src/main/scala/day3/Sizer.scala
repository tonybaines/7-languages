package day3

import scala.io._
import scala.actors._
import Actor._

trait Sizer {
  // START:loader
  object PageLoader {
    def getPageSize(url: String) = Source.fromURL(url).mkString.length
    def linkCounter(url: String) = {
      val pageText = Source.fromURL(url).mkString
      // Trying to do an XML parse failed with undeclared entities
      val linksPattern = """\<a""".r
      val links = pageText.lines.filter{ line: String => 
        linksPattern.findFirstIn(line) match {
          case Some(s) => true
          case None => false
        } 
      }
      links.length
    }
  }
  // END:loader

  val urls = List(
    "http://www.twitter.com/",
    "http://www.google.co.uk/",
    "http://java.sun.com/")

  // START:time
  def timeMethod(method: () => Unit) = {
    val start = System.nanoTime
    method()
    val end = System.nanoTime
    val duration = (end - start) / 1000000000.0
    println("Method took " + duration + " seconds.")
    duration
  }
  // END:time

  // START:sequential
  def getPageSizeSequentially() = {
    for (url <- urls) {
      println("Size for " + url + ": " + PageLoader.linkCounter(url))
    }
  }
  // END:sequential

  // START:concurrent
  def getPageSizeConcurrently() = {
    val caller = self

    for (url <- urls) {
      actor { caller ! (url, PageLoader.linkCounter(url)) }
    }

    for (i <- 1 to urls.size) {
      receive {
        case (url, size) =>
          println("Size for " + url + ": " + size)
      }
    }
  }
  // END:concurrent

}