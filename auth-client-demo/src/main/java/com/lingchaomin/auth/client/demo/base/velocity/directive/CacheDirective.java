package com.lingchaomin.auth.client.demo.base.velocity.directive;


import com.lingchaomin.auth.client.demo.base.velocity.constant.CacheConstant;
import com.lingchaomin.auth.client.demo.base.velocity.dataprovider.IDataProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/21 下午7:54
 * @description 页面片段缓存
 */
public class CacheDirective extends Directive {

    private static final Logger LOG= LoggerFactory.getLogger(CacheDirective.class);
    @Override
    public String getName() {
        return "cache";
    }

    @Override
    public int getType() {
        return BLOCK;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {

        long start=System.currentTimeMillis();
        Node keyNode = node.jjtGetChild(0);

        String cacheKey = (String) keyNode.value(context);
        LOG.warn("cacheKey:{}",cacheKey);
        String cacheHtml  = CacheConstant.HTML_CACHE.getIfPresent(cacheKey);
        if (StringUtils.isBlank(cacheHtml)) {
           // LOG.warn("cacheHtml:{}",cacheHtml);

            Node dataProviderNode = node.jjtGetChild(1);
            IDataProvider dataProvider = (IDataProvider) dataProviderNode.value(context);
            Map<String, Object> map = dataProvider.load();

            LOG.warn("map:{}",map.toString());
            for (String key : map.keySet()) {
                context.put(key, map.get(key));
            }

            Node bodyNode = node.jjtGetChild(2);
            Writer tempWriter = new StringWriter();
            bodyNode.render(context, tempWriter);
            cacheHtml = tempWriter.toString();
            //LOG.warn("cacheHtml:{}",cacheHtml);
            CacheConstant.HTML_CACHE.put(cacheKey, cacheHtml);
        }
        writer.write(cacheHtml);
        long end=System.currentTimeMillis();

        LOG.warn("cost:{}ms",(end-start));
        return true;
    }
}
