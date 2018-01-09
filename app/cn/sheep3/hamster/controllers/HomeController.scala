package cn.sheep3.hamster.controllers

import javax.inject.Inject

import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by xuxin02 on 2017/10/11.
  */
class HomeController @Inject()(cc: ControllerComponents) extends BaseController(cc) {


  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future {
      log.info("hello boy")
      Ok("It Works!")
    }
  }
}
