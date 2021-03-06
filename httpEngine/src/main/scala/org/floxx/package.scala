package org

import cats.effect.IO
import org.http4s.Response
import org.http4s.dsl.io._
import org.slf4j.{Logger, LoggerFactory}

package object floxx {

  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  case class UserInfo(userId: String, firstname: String, isAdmin: Boolean)

  object UserInfo {
    import cats.effect.IO
    import org.http4s.circe.jsonOf
    import io.circe.generic.auto._
    implicit val format = jsonOf[IO, UserInfo]

    def empty: UserInfo = UserInfo("-","-",isAdmin = false)
  }

  sealed trait FloxxError {
    val code: String
    val message: String

    override def toString: String = s"$code - $message"

  }

  type IOVal[T] = Either[FloxxError, T]


  case class InvalidError(message: String) extends FloxxError {
    val code: String = "000"
  }

  case class AuthentificationError(message: String) extends FloxxError {
    val code: String = "001"
  }

  case class IllegalStateError(message: String) extends FloxxError {
    val code: String = "003"
  }

  def handleError(error: FloxxError): IO[Response[IO]] = {
    logger.error(s"An error has been detected : ${error.message}")
    error match {
      case e: InvalidError => internaltHandle(e)
      case e: IllegalStateError => internaltHandle(e)
      case e: AuthentificationError => authHandle(e)
    }
  }

  private def internaltHandle(message: FloxxError): IO[Response[IO]] = InternalServerError(message.toString)
  private def authHandle(message: FloxxError): IO[Response[IO]]      = Forbidden(message.toString)

}
