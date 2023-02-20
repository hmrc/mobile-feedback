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

import org.scalamock.handlers.CallHandler3
import org.scalamock.scalatest.MockFactory
import org.scalatest.OneInstancePerTest
import play.api.http.Status
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Helpers}
import utils.BaseSpec
import uk.gov.hmrc.mobilefeedback.services.FeedbackService
import play.api.mvc.Headers
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.mobilefeedback.models.Feedback
import common.MobileFeedbackTestData
import uk.gov.hmrc.mobilefeedback.types.ModelTypes.Origin
import eu.timepit.refined.auto._

import scala.concurrent.Future

class FeedbackControllerSpec extends BaseSpec with MockFactory with OneInstancePerTest with MobileFeedbackTestData {

  val mockService: FeedbackService = mock[FeedbackService]

  val origin : Origin = "mobile-paye"
  def mockSendAudit(f: Future[Unit]): CallHandler3[Origin, Feedback, HeaderCarrier, Future[Unit]] =
    (mockService.sendAudit(_: Origin, _: Feedback)(_: HeaderCarrier)).expects(*, *, *).returning(f)

  private val fakeRequest = FakeRequest("POST", "/feedback/mobile-paye", Headers((CONTENT_TYPE, JSON)), feedbackJson)
  private val controller = new FeedbackController(Helpers.stubControllerComponents(), mockService)


  "POST /feedback" should {
    "return 204" in {
      mockSendAudit(Future.successful(()))
      val result = controller.feedback(origin)(fakeRequest)

      status(result) shouldBe Status.NO_CONTENT
    }
  }






}
