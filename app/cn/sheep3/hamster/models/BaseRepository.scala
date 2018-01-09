package cn.sheep3.hamster.models

import java.sql.Connection

import anorm.{RowParser, SQL, SqlParser}

import scala.concurrent.Future

/**
  * Created by sheep3 on 2017/10/30.
  */
abstract class BaseRepository[T] {

  def TABLE_NAME: String

  def PK_ROW: String = "id"

  def simple: RowParser[T]


  def findAll()(implicit connection: Connection) = {
    SQL(s"SELECT * FROM $TABLE_NAME").as(simple *)
  }

  def findById(id: Long)(implicit connection: Connection) = {
    SQL(s"SELECT * FROM $TABLE_NAME WHERE $PK_ROW={id} AND deleted={deleted}")
      .on('id -> id).as(simple.singleOpt)
  }

  def delete(id: Long)(implicit connection: Connection) = {
    SQL(s"DELETE FROM $TABLE_NAME WHERE id={id}")
      .on('id -> id).executeUpdate()
  }

}
