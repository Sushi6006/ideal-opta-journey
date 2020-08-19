/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.persistence.jpa.api.score.buildin.simplebigdecimal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.optaplanner.core.api.score.buildin.simplebigdecimal.SimpleBigDecimalScore;

@Converter
public class SimpleBigDecimalScoreConverter implements AttributeConverter<SimpleBigDecimalScore, String> {

    @Override
    public String convertToDatabaseColumn(SimpleBigDecimalScore score) {
        if (score == null) {
            return null;
        }

        return score.toString();
    }

    @Override
    public SimpleBigDecimalScore convertToEntityAttribute(String scoreString) {
        if (scoreString == null) {
            return null;
        }

        return SimpleBigDecimalScore.parseScore(scoreString);
    }
}
