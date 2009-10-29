/*
 * Copyright (C) 2009 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.examples.calculator2;

import org.parboiled.BaseParser;
import org.parboiled.Rule;

public class CalculatorParser extends BaseParser<CalcNode, CalculatorActions> {

    public CalculatorParser(CalculatorActions actions) {
        super(actions);
    }

    public Rule inputLine() {
        return enforcedSequence(
                expression(),
                actions.setValue(value("expression")),
                eoi()
        );
    }

    public Rule expression() {
        return sequence(
                term(),
                optional(enforcedSequence(firstOf('+', '-'), expression())),
                actions.createAstNode(ch("o/e/firstOf"), value("term"), value("o/e/expression"), "+-")
        );
    }

    public Rule term() {
        return sequence(
                factor(),
                optional(enforcedSequence(firstOf('*', '/'), term())),
                actions.createAstNode(ch("o/e/firstOf"), value("factor"), value("o/e/term"), "*/")
        );
    }

    public Rule factor() {
        return sequence(
                firstOf(
                        number(),
                        enforcedSequence('(', expression(), ')').label("parens")
                ),
                actions.setValue(firstValue("firstOf"))
        );
    }

    public Rule number() {
        return sequence(
                oneOrMore(digit()),
                actions.createAstNode(convertToInteger(text("oneOrMore")))
        );
    }

    public Rule digit() {
        return charRange('0', '9');
    }

}