package models

import java.sql.Connection
import java.util.Date
import javax.inject.{Inject, Singleton}

import anorm.SqlParser.get
import anorm.{SQL, ~}
import play.api.libs.json.Json

/**
  * Created by sheep3 on 2018/1/11.
  */
case class Setting(
                k: String,
                v: String
              )

object Setting {
  implicit val format = Json.format[Setting]
}

@Singleton
class SettingRepository @Inject() extends BaseRepository[Setting] {
  override def TABLE_NAME = "setting"


  override def PK_ROW = "k"

  override def simple = {
      get[String](s"$TABLE_NAME.title") ~
      get[String](s"$TABLE_NAME.content") map {
      case k ~ v =>
        Setting(k, v)
    }
  }

  def findByKey(key: String)(implicit connection: Connection): Option[Setting] = {
    SQL(s"SELECT * FROM $TABLE_NAME WHERE k={k}").on('k -> key).as(simple.singleOpt)
  }

  def upsert(setting: Setting)(implicit connection: Connection) = {
    SQL(
      s"""
         INSERT INTO $TABLE_NAME (`k`, `v`) VALUES ({key}, {value})
          ON DUPLICATE KEY UPDATE `v`={value}
       """)
      .on(
        'key -> setting.k,
        'value -> setting.v
      )
      .executeInsert()
  }

}
