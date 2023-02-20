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

package services

import org.scalamock.handlers.CallHandler4
import org.scalamock.scalatest.MockFactory
import org.scalatest.OneInstancePerTest
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.mobilefeedback.services.FeedbackService
import uk.gov.hmrc.play.audit.http.connector.AuditConnector
import utils.BaseSpec
import common.MobileFeedbackTestData
import eu.timepit.refined.auto._
import uk.gov.hmrc.mobilefeedback.types.ModelTypes.Origin

import scala.concurrent.ExecutionContext

class FeedbackServiceSpec extends BaseSpec with MobileFeedbackTestData with MockFactory with OneInstancePerTest {

  lazy val auditConnector: AuditConnector = mock[AuditConnector]
  lazy val feedbackService : FeedbackService = new FeedbackService(auditConnector)

  def mockSendExtendedAudit(f: Unit): CallHandler4[String, Map[String, String], HeaderCarrier, ExecutionContext, Unit] =
    (auditConnector.sendExplicitAudit(_: String, _: Map[String, String])(_: HeaderCarrier, _: ExecutionContext)).expects(*,*,*,*).returning(f)

  val origin: Origin = "mobile-paye"

  "Calling the .buildAuditModel" should {

    "return an auditMap with an origin and feedback model that contains answers" in {

      val result = feedbackService.buildAuditMap(origin, feedbackModel)

      val expected = testAuditModel

      result shouldEqual expected

    }

    "return an audit map with an origin and feedback model that contains no answers" in {

      val result = feedbackService.buildAuditMap(origin, emptyFeedbackModel)

      val expected = testAuditModelNoAnswers

      result shouldEqual expected

    }


  }


}
