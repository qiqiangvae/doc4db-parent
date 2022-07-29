package com.qingcha.tech.doc4db.core.method;

import com.qingcha.tech.doc4db.core.StringUtil;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;

import java.util.List;

/**
 * @author qiqiang
 */
public class UnderlineToHumpMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) {
        SimpleScalar arg0 = (SimpleScalar) list.get(0);
        String str = arg0.toString();
        return StringUtil.underlineToHump(str);
    }
}
