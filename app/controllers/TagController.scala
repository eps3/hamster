package controllers

import javax.inject.Inject

import play.api.mvc.{Action, AnyContent, ControllerComponents, Request}

import scala.concurrent.Future

/**
  * Created by sheep3 on 2018/1/10.
  */
class TagController @Inject()(cc: ControllerComponents) extends BaseController(cc) {


  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future {
      Ok(views.html.page.tags("啦啦啦"))
    }
  }
}

