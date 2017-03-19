package com.lingchaomin.auth.server.web.base.shiro.velocity.directive;


import com.lingchaomin.auth.server.web.base.shiro.velocity.utils.VelocityUtils;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.Writer;

/**
 * @author: mlc
 * @date: 2016年7月6日
 * @Description: 块命令
 */

public class BlockDirective extends Directive {

	@Override
	public String getName() {
		return "block";
	}

	@Override
	public int getType() {
		return BLOCK;
	}

	/**
	 * 定义block命令
	 */
	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		String name = VelocityUtils.getRequiredArgument(context, node, 0, getName());
		OverrideDirective.OverrideNodeWrapper overrideNode = getOverrideNode(context, name);
		Node topNode = node.jjtGetChild(1);
		if (overrideNode == null) {
			return topNode.render(context, writer);
		} else {
			VelocityUtils.setParentForTop(new OverrideDirective.OverrideNodeWrapper(topNode), overrideNode);
			return overrideNode.render(context, writer);
		}
	}

	/**
	 * 获取需要重写的模块名
	 *
	 * @param context
	 * @param name
	 * @return
	 */
	private OverrideDirective.OverrideNodeWrapper getOverrideNode(InternalContextAdapter context, String name) {
		return (OverrideDirective.OverrideNodeWrapper) context.get(VelocityUtils.getOverrideVariableName(name));
	}

}
