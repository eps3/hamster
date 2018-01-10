package controllers.admin

import javax.inject.Inject

import controllers.BaseController
import play.api.mvc.{Action, AnyContent, ControllerComponents, Request}

import scala.concurrent.Future

/**
  * Created by sheep3 on 2018/1/10.
  */
class AdminController @Inject()(cc: ControllerComponents) extends BaseController(cc) {


  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future {
      Ok(views.html.page.admin.index())
    }
  }
}
