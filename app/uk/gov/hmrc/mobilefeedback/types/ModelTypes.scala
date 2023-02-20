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

package uk.gov.hmrc.mobilefeedback.types

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.string.MatchesRegex

object ModelTypes {

  type Origin = String Refined ValidOrigin

  private type ValidOrigin = MatchesRegex[W.`"(mobile-paye)"`.T]

}
