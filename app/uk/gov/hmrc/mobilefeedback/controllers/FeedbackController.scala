/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.mobilefeedback.controllers

import play.api.libs.json.JsValue
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController
import play.api.mvc.{Action, ControllerComponents}
import uk.gov.hmrc.http.HeaderCarrier

import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.mobilefeedback.models.Feedback
import uk.gov.hmrc.mobilefeedback.services.FeedbackService
import uk.gov.hmrc.mobilefeedback.types.ModelTypes.appOrigin
import uk.gov.hmrc.play.http.HeaderCarrierConverter.fromRequest

import scala.concurrent.ExecutionContext

@Singleton()
class FeedbackController @Inject()(cc: ControllerComponents, services: FeedbackService)(implicit ex: ExecutionContext)
    extends BackendController(cc) {

  def feedback(origin : appOrigin): Action[JsValue] =
    Action.async(parse.json) { implicit request =>
      implicit val hc: HeaderCarrier =
        fromRequest(request)
        withJsonBody[Feedback] { feedbackModel =>
         services.sendAudit(origin, feedbackModel).map{ _ =>
            NoContent
          }
          }
        }
}
