/**
 * XBeans implementation.
 * Author: David Bau
 * Date: Oct 1, 2003
 */
package org.apache.xmlbeans.impl.binding;

public class ParticleProperty extends BindingProperty
{
    public ParticleProperty()
    {
        super();
    }

    public ParticleProperty(org.apache.xmlbeans.x2003.x09.bindingConfig.BindingProperty node)
    {
        super(node);
    }
    
    public XmlName getXmlName()
    {
        return this.tXml;
    }
}
