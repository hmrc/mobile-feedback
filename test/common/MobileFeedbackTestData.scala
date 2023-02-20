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

package common

import play.api.libs.json.{JsObject, Json}
import uk.gov.hmrc.mobilefeedback.models.Feedback

trait MobileFeedbackTestData {

  val feedbackModel: Feedback = Feedback(Some(true), Some(5), Some("It was great"), Some(4))
  val emptyFeedbackModel: Feedback = Feedback(None, None, None, None)

  val feedbackJson: JsObject = Json.obj("ableToDo" -> true,
    "howEasyScore" -> 5,
    "whyGiveScore" -> "It was great",
    "howDoYouFeelScore" -> 4)

  val testAuditModel: Map[String, String] = Map("origin" -> "mobile-paye", "ableToDo" -> "true", "howEasyScore" -> "5", "whyGiveScore" -> "It was great", "howDoYouFeelScore" -> "4")
  val testAuditModelNoAnswers: Map[String, String] = Map("origin" -> "mobile-paye", "ableToDo" -> "-", "howEasyScore" -> "-", "whyGiveScore" -> "-", "howDoYouFeelScore" -> "-")

}