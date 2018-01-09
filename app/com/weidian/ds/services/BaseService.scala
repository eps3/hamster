package com.weidian.ds.services

import com.weidian.ds.filters.{DSLog, LogUtil}
import com.weidian.ds.models.DatabaseExecutionContext
import play.api.db.DBApi
import play.api.mvc.RequestHeader

/**
  * Created by sheep3 on 2017/10/30.
  */
abstract class BaseService(dBApi: DBApi)(implicit ec: DatabaseExecutionContext) {
  def log(implicit request: RequestHeader): DSLog = LogUtil.log(request)
  val db = dBApi.database("default")
}
