package com.weidian.ds.controllers

import javax.inject.Inject

import com.weidian.ds.utils.JsonUtil
import play.api.mvc._

import scala.concurrent.Future


class HealthCheckController @Inject()(cc: ControllerComponents) extends BaseController(cc) {

  /**
    * 深度健康检查
    * @return
    */
  def index(): Action[AnyContent] = Action.async{ implicit request: Request[AnyContent] =>
    Future {
      Ok(JsonUtil.returnSuccess(status_reason = "success"))
    }

  }
}
