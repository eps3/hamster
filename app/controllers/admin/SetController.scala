package controllers.admin

import javax.inject.Inject

import controllers.BaseController
import play.api.mvc.{Action, AnyContent, ControllerComponents, Request}

import scala.concurrent.Future

/**
  * Created by sheep3 on 2018/1/10.
  */
class SetController @Inject()(cc: ControllerComponents) extends BaseController(cc) {

  def setting(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future.successful{
      Ok(views.html.page.admin.setting())
    }
  }
}
