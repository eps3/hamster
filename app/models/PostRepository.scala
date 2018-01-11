package models

import java.sql.Connection
import java.util.Date
import javax.inject.{Inject, Singleton}

import anorm.SqlParser.get
import anorm.{SQL, SqlParser, ~}
import play.api.db.DBApi
import play.api.libs.json.Json

/**
  * Created by sheep3 on 2018/1/11.
  */
case class Post(
                 id: Option[Long],
                 user_id: Long,
                 title: String,
                 content: String,
                 simple_content: String,
                 img_url: String,
                 create_time: Option[Date],
                 update_time: Option[Date]
               )

object Post {
  implicit val format = Json.format[Post]
}

@Singleton
class PostRepository @Inject() extends BaseRepository[Post] {
  override def TABLE_NAME = "post"

  override def simple = {
    get[Option[Long]](s"$TABLE_NAME.id") ~
      get[Long](s"$TABLE_NAME.user_id") ~
      get[String](s"$TABLE_NAME.title") ~
      get[String](s"$TABLE_NAME.content") ~
      get[String](s"$TABLE_NAME.simple_content") ~
      get[String](s"$TABLE_NAME.img_url") ~
      get[Option[Date]](s"$TABLE_NAME.create_time") ~
      get[Option[Date]](s"$TABLE_NAME.update_time") map {
      case id ~ user_id ~ title ~ content ~ simple_content ~ img_url ~ create_time ~ update_time =>
        Post(id, user_id, title, content, simple_content, img_url, create_time, update_time)
    }
  }

  def save(post: Post)(implicit connection: Connection): Option[Long] = {
    SQL(
      s"""
          insert into $TABLE_NAME (
            user_id, title, content, simple_content, img_url
          ) values (
            {user_id}, {title}, {content}, {simple_content}, {img_url}
          )
        """)
      .on(
        'user_id -> post.user_id,
        'title -> post.title,
        'content -> post.content,
        'simple_content -> post.simple_content,
        'img_url -> post.img_url
      ).executeInsert()
  }

}
