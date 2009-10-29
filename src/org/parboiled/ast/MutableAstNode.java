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

package org.parboiled.ast;

import org.parboiled.utils.MutableTreeNode;

/**
 * A node in an abstract syntax tree (AST).
 *
 * @param <T> The type of the nodes type field
 * @param <N> The actual implementation type of this AST node
 */
public interface MutableAstNode<T, N extends MutableAstNode<T, N>> extends AstNode<T, N>, MutableTreeNode<N> {

    void setType(T type);

}