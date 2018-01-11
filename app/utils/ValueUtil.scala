package utils

/**
  * Created by sheep3 on 2018/1/12.
  */
object ValueUtil {
  def defaultV(key: String): String = {
    key match {
      case "title" => "Hamster!"
      case "message" => "Welcome to hamster ~"
      case "header_img" => "/file/img/me.jpg"
      case "post_img" => "/file/img/default_bg.jpg"
      case "tag_img" => "/file/img/tag_bg.jpg"
      case _ => s"$key have not any default value"
    }
  }
}
