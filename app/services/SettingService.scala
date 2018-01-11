package services

import javax.inject._

import models.{DatabaseExecutionContext, Setting, SettingRepository}
import play.api.db.DBApi
import utils.ValueUtil

import scala.concurrent.Future

/**
  * Created by sheep3 on 2018/1/12.
  */
@Singleton
class SettingService @Inject()(dBApi: DBApi, settingRepository: SettingRepository)
                              (implicit ec: DatabaseExecutionContext) extends BaseService(dBApi) {




  def getByKey(key: String) = Future {
    db.withConnection { implicit connection =>
      settingRepository.findByKey(key).getOrElse(ValueUtil.defaultV(key))
    }
  }

  def upsertSetting(setting: Setting)= Future {
    db.withConnection { implicit connection =>
      settingRepository.upsert(setting)
    }
  }


}
