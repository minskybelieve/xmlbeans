/*
* The Apache Software License, Version 1.1
*
*
* Copyright (c) 2003 The Apache Software Foundation.  All rights
* reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*
* 1. Redistributions of source code must retain the above copyright
*    notice, this list of conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright
*    notice, this list of conditions and the following disclaimer in
*    the documentation and/or other materials provided with the
*    distribution.
*
* 3. The end-user documentation included with the redistribution,
*    if any, must include the following acknowledgment:
*       "This product includes software developed by the
*        Apache Software Foundation (http://www.apache.org/)."
*    Alternately, this acknowledgment may appear in the software itself,
*    if and wherever such third-party acknowledgments normally appear.
*
* 4. The names "Apache" and "Apache Software Foundation" must
*    not be used to endorse or promote products derived from this
*    software without prior written permission. For written
*    permission, please contact apache@apache.org.
*
* 5. Products derived from this software may not be called "Apache
*    XMLBeans", nor may "Apache" appear in their name, without prior
*    written permission of the Apache Software Foundation.
*
* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
* ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
* SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
* LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
* USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
* OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
* SUCH DAMAGE.
* ====================================================================
*
* This software consists of voluntary contributions made by many
* individuals on behalf of the Apache Software Foundation and was
* originally based on software copyright (c) 2003 BEA Systems
* Inc., <http://www.bea.com/>. For more information on the Apache Software
* Foundation, please see <http://www.apache.org/>.
*/

package org.apache.xmlbeans.impl.jam.internal.javadoc;

import org.apache.xmlbeans.impl.jam.JElement;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.apache.xmlbeans.impl.jam.internal.BaseJAnnotation;

/**
 * Javadoc-backed implementation of org.apache.xmlbeans.impl.jam.Annotation
 *
 * @author Patrick Calahan <pcal@bea.com>
 */

/*package*/ final class JDAnnotation extends BaseJAnnotation {

  // ========================================================================
  // Variables

  private com.sun.javadoc.Tag mTag;

  // ========================================================================
  // Constructors

  public JDAnnotation(JElement parent, com.sun.javadoc.Tag tag) {
    this(parent, tag, tag.name(), tag.text());
  }

  public JDAnnotation(JElement parent, com.sun.javadoc.ParamTag tag) {
    this(parent, tag, tag.name(), tag.parameterComment()); //REVIEW?
  }

  private JDAnnotation(JElement parent,
                       com.sun.javadoc.Tag tag,
                       String name,
                       String value) {
    super(parent, trimAtSign(name), value);
    mTag = tag;
  }

  // ========================================================================
  // JElement impl

  // we want to lazily instantiate these since they probably won't get
  // used often.

  public JSourcePosition getSourcePosition() {
    return new JDSourcePosition(mTag.position());
    //FIXME but still want lazy instantiation
  }

  // ========================================================================
  // Private methods

  private static String trimAtSign(String name) {
    if (name.charAt(0) == '@' && name.length() > 1) {
      return name.substring(1).trim();
    } else {
      return name;
    }
  }

}