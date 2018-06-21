/*
 * Copyright 2018 Expedia Group, Inc.
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
package com.expedia.adaptivealerting.anomvalidate.filter;

import com.expedia.adaptivealerting.core.anomaly.AnomalyLevel;
import com.expedia.adaptivealerting.core.anomaly.AnomalyResult;
import com.expedia.adaptivealerting.core.anomaly.InvestigationResult;

import java.util.Objects;

public class PostInvestigationFilter  implements InvestigationFilter {

    // TODO: make configurable
    @Override
    public boolean keep(AnomalyResult anomalyResult) {
        if (anomalyResult == null) {
            return false;
        }
        if (AnomalyLevel.WEAK == anomalyResult.getAnomalyLevel()
                && anomalyResult.getInvestigationResults() != null) {
            return anomalyResult.getInvestigationResults().stream()
                    .filter(Objects::nonNull)
                    .anyMatch(InvestigationResult::isResult);
        }
        return AnomalyLevel.STRONG.equals(anomalyResult.getAnomalyLevel());
    }
}